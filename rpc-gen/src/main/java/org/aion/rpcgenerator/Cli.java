package org.aion.rpcgenerator;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.parsers.ParserConfigurationException;
import org.aion.rpcgenerator.data.TypeSchema;
import org.aion.rpcgenerator.error.ErrorSchema;
import org.aion.rpcgenerator.rpc.RPCSchema;
import org.aion.rpcgenerator.util.Utils;
import org.aion.rpcgenerator.util.XMLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

//TODO move the template and spec filenames to an external file/configuration step
@Command(name = "generator", mixinStandardHelpOptions = true, subcommands = {PrintSpec.class})
public class Cli implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Cli.class);
    @Option(names = {"--verbose"}, defaultValue = "false")
    private boolean verbose;//indicates whether the debug level should be used
    @Option(names = {"--templates",
        "-t"}, arity = "1", description = "The template root directory.")
    private String templatePath;//the list of templates that should be used
    private String[] templates;
    @Option(names = {"--spec",
        "-s"}, arity = "1", defaultValue = "definitions/spec", description = "The root directory for the xml specification files.")
    private String spec;//the root directory of all the spec files
    @Option(names = {"--out",
        "-o"}, defaultValue = "output", description = "The output output directory for the generated files.")
    private String output;// the output directory
    @Option(names = "--config", required = true)
    private String configPath;//the configuration path
    private Config config;

    private Cli() {
    }

    Cli(boolean verbose, String[] templates, String spec, String output) {
        this.verbose = verbose;
        this.templates = templates;
        this.spec = spec;
        this.output = output;
    }

    public static void main(String[] args) {
        CommandLine cli = new CommandLine(new Cli());
        cli.setCaseInsensitiveEnumValuesAllowed(true);
        cli.execute(args);
    }

    /**
     * Runs the code generator with the supplied commands
     */
    @Override
    public void run() {
        try {
            initConfig();
            setLogLevel();//change the log level
            checkTemplates();// set the default template location
            checkSpec();//validate the spec path is valid
            logger.info("Starting RPC generator...");
            Configuration configuration = new Configuration();
            logger.debug("Creating templates");
            Path specPath = Paths.get(spec);
            if (specPath.toFile().exists() && specPath.toFile().isDirectory()) {
                logger.debug("Attempting to read spec...");
                File errors = Paths.get(Utils.appendToPath(spec,config.getErrorSpecFileName())).toFile();//get the error spec file
                logger.debug("Reading error template files.");
                //TODO replace this list of errors with a single object
                List<ErrorSchema> errorSchemas = ErrorSchema
                    .fromDocument(XMLUtils.fromFile(errors));// convert the file to a list of errors
                File types = Paths.get(Utils.appendToPath(spec, config.getTypeSpecFileName())).toFile();
                TypeSchema typeSchema = new TypeSchema(XMLUtils.fromFile(types));//convert the types to a schema object
                typeSchema.setErrors(errorSchemas); // set the errors in the type schema
                logger.debug("Reading rpc template files.");
                //noinspection ConstantConditions
                //Read the rpc spec into the application
                RPCSchema rpcSchema = new RPCSchema(XMLUtils.fromFile(Utils.appendToPath(spec, config.getRPCSpecFileName())), typeSchema, errorSchemas);

                //create the output path
                File errorsOutputFile = new File(Utils.appendToPath(output, "errors"));
                File rpcOutputFile = new File(Utils.appendToPath(output, "rpc"));
                File typesOutputFile = new File(Utils.appendToPath(output, "types"));

                processAllErrors(errorsOutputFile, errorSchemas, templatePath, configuration);
                processAllRPCTemplates(rpcOutputFile, rpcSchema, templatePath, configuration);
                processAllTypes(typesOutputFile, typeSchema, templatePath, configuration);

                logger.info("Successfully autogenerated files for:\n\t{}", String.join( "\n\t", templates));
            } else {
                logger.warn("Could not open xml specification directory. Check if it exists");
            }
        } catch (RuntimeException e) {
            logger.warn("Failed unexpectedly, check the application arguments and config at: {}", configPath);
            logException(e);
            CommandLine.usage(this, System.out);//print out the help info if we
            // encountered a runtime exception
            System.exit(ExitCodes.Error.code);
        } catch (IOException e) {
            logger.warn("Failed due to io.");
            logException(e);
            System.exit(ExitCodes.Error.code);
        } catch (SAXException | ParserConfigurationException e) {
            logger.warn("Could not read the xml file.", e);
            logException(e);
            System.exit(ExitCodes.Error.code);
        } catch (TemplateException e) {
            logger.warn("Failed to create file from template.", e);
            logException(e);
            System.exit(ExitCodes.Error.code);
        }
        System.exit(ExitCodes.Normal.code);
    }

    private void logException(Exception e) {
        logger.debug("Failed due to exception.", e);
    }


     void processAllErrors(File outputFile, List<ErrorSchema> errorSchemas,
        String templatePath, Configuration configuration)
        throws IOException, TemplateException {
        try {
            createDir(outputFile);// create the output directory that will store the error
            //noinspection ConstantConditions
            List<String> errorTemplates = (listFiles(templatePath, config.getErrorsTemplateDirectory())).filter(
                    p -> p.endsWith("ftl"))
                .collect(Collectors.toUnmodifiableList());// get all the error
            // templates which match the expected names
            Map<String, Object> errors = Map.ofEntries(// create the error map
                Map.entry("date", ZonedDateTime.now().toLocalDate()),
                Map.entry("errors", Utils.toListOfMaps(errorSchemas)));

            for (String templateFile : errorTemplates) {
                String outputFileName;
                outputFileName = config.errorFileName(getFileName(templateFile));

                if (outputFileName == null) {
                    throw new NullPointerException();
                }
                File temp = new File(
                    Utils.appendToPath(outputFile.getAbsolutePath(), outputFileName));
                if (createOutputFile(temp)) {// create the file to write to
                    try(PrintWriter printWriter=new PrintWriter(temp)) {
                        process(configuration, templateFile, printWriter, errors);
                    }
                    logProcessedTemplate(outputFileName, templateFile);
                }
            }
        } catch (Exception e) {
            logger.warn("Failed to process error templates");
            throw e;
        }
    }

    /**
     * Logs a message for issue processed file
     * @param outputFile the output file name
     * @param templateFile the template file processed
     */
    private void logProcessedTemplate(String outputFile, String templateFile) {
        logger.info("Wrote {} file for template: {}", outputFile, templateFile);
    }


    //Processes RPC templates
    void processAllRPCTemplates(File outputFile, RPCSchema rpcSchema, String templatePath,
        Configuration configuration)
        throws IOException, TemplateException {
        try {
            createDir(outputFile);
            //noinspection ConstantConditions
            List<String> errorTemplates = (listFiles(templatePath, config.getRpcTemplateDirectory())).filter(
                    p -> p.endsWith("ftl"))
                .collect(Collectors.toUnmodifiableList());//get all the supported template files
            for (String templateFile : errorTemplates) {
                String outputFileName = config.rpcFileName(getFileName(templateFile));
                if (outputFileName == null) {
                    throw new NullPointerException();
                }

                File temp = new File(
                    Utils.appendToPath(outputFile.getAbsolutePath(), outputFileName));
                //create the output file
                if (createOutputFile(temp)) {
                    try (PrintWriter printWriter = new PrintWriter(temp)) {
                        process(configuration, templateFile, printWriter,
                            rpcSchema.toMap());
                    }
                    logProcessedTemplate(outputFileName, templateFile);
                }
            }
        } catch (Exception e) {
            logger.warn("Failed to process rpc templates");
            throw e;
        }
    }

    void processAllTypes(File outputFile, TypeSchema typeSchema, String templatePath, Configuration configuration)
        throws IOException, TemplateException {
        try{
            createDir(outputFile);
            List<String> typesTemplates = (
                listFiles(templatePath, config.getTypesTemplateDirectory()))
                .filter(file-> file.endsWith("ftl"))//get all the matching files
                .collect(Collectors.toUnmodifiableList());

            for (String templateFile: typesTemplates){
                String outputFileName = config.typeFileName(getFileName(templateFile));
                if (outputFileName == null) {
                    throw new NullPointerException();
                }
                logger.debug("Processing: {}", templateFile);

                File temp = new File(
                    Utils.appendToPath(outputFile.getAbsolutePath(), outputFileName));
                if (createOutputFile(temp)) {// process the template
                    try (Writer printWriter = new PrintWriter(temp)) {
                        process(configuration, templateFile, printWriter,
                            typeSchema.toMap());
                    }
                    logProcessedTemplate(outputFileName, templateFile);
                }
            }
        }catch (Exception e){
            logger.warn("Failed to process type templates");
            throw e;
        }
    }

    private String getFileName(String templateFile) {
        return Paths.get(templateFile).getFileName().toString();
    }

    private Stream<String> listFiles(String templatePath, String rpcTemplateDirectory)
        throws IOException {
        Path path = Paths.get(Utils.appendToPath(templatePath, rpcTemplateDirectory));
        if (Files.exists(path) && Files.isDirectory(path)) {
            try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)){
                Iterator<Path> pathIterator = directoryStream.iterator();
                ArrayList<String> files = new ArrayList<>();
                while (pathIterator.hasNext()){
                    Path temp = pathIterator.next();
                    files.add(temp.toAbsolutePath().toString());
                }
                return files.stream();
            }
        } else {
            throw new IllegalArgumentException("Supplied path is not a directory: " + path.toAbsolutePath());
        }
    }

    /**
     * Creates an instance of a freemarker template processor
     * @param configuration the freemarker configuration
     * @param ftlFile the freemarker template
     * @param writer the writer to be used to output this file
     * @param map the map to be processed
     */
    void process(Configuration configuration, String ftlFile, Writer writer, Map map)
        throws IOException, TemplateException {
        File templateFile = new File(ftlFile);
        File workingFileDir=new File(System.getProperty("user.dir"));
        FileTemplateLoader templateLoader = new FileTemplateLoader(workingFileDir.getAbsoluteFile());
        configuration.setTemplateLoader(templateLoader);
        logger.debug("Processing template with object map: {}", map);
        String path = getRelativePath(templateFile, workingFileDir);
        logger.debug("Using file: {}", path);
        Template template = configuration.getTemplate(path);
        template.process(map, writer);
    }

    private String getRelativePath(File file, File base) {
        return base.toURI().relativize(file.toURI()).getPath();
    }

    /**
     * Sets the log level based on whether verbose logging is enabled
     */
    private void setLogLevel() {
        if (verbose) {
            Utils.debug(logger);
        } else {
            Utils.info(logger);
        }
    }

    private boolean createOutputFile(File rpcFile) throws IOException {
        logger.debug("Creating file: {}", rpcFile);
        return rpcFile.exists() || rpcFile.createNewFile();
    }

    private boolean createDir(File dir){
        logger.debug("Creating directory: {}", dir);
        return dir.exists() || dir.mkdirs();
    }

    void checkTemplates() {
        if (templatePath == null) {
            templatePath = "definitions/templates/";// default template paths
        }
        logger.debug("Template path: [{}]", templatePath);
        if (config == null){
            throw new IllegalStateException("Config not initialized.");
        }
        List<Path> paths = List.of(Paths.get(Utils.appendToPath(templatePath, config.getRpcTemplateDirectory())),
            Paths.get(Utils.appendToPath(templatePath, config.getTypesTemplateDirectory())),
            Paths.get(Utils.appendToPath(templatePath, config.getRpcTemplateDirectory())));
        for (Path path: paths){
            if (!Files.exists(path)) throw new IllegalArgumentException("Path does not exist: "+ path.toString());
            if (!Files.isDirectory(path)) throw new IllegalArgumentException("Path is not a directory: "+ path.toString());
        }

        templates = paths.stream().map(Path::toAbsolutePath).map(Objects::toString).toArray(String[]::new);
    }

    void checkSpec() { // chect that the spec files exist
        File file = new File(spec);
        if (!file.isDirectory()) {
            logger.warn("The spec directory is not a folder.");
            throw new IllegalStateException("The spec directory is not a folder");
        } else if (file.list().length ==0) {
            logger.warn("The spec directory is empty.");
            throw new IllegalStateException("The spec directory is empty");
        }
        //noinspection ConstantConditions
        List<String> files = List.of("errors.xml", "types.xml");
        Pattern regex = Pattern.compile("rpc");
        //Check that the required files exist
        boolean containsXMLSpecFiles = Arrays.stream(file.list()).filter(path -> path
            .endsWith(".xml")).allMatch(f-> files.contains(f) || regex.matcher(f).find());
        if (!containsXMLSpecFiles) {
            logger.warn("Could not find any template files in the path.");
            throw new IllegalStateException("The spec directory is not a folder");
        }
    }

    private void initConfig() throws ParserConfigurationException, SAXException, IOException {
        config = new Config(Paths.get(configPath));
    }
}
