package org.aion.rpcgenerator;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.aion.rpcgenerator.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "print", mixinStandardHelpOptions = true, description = "Creates the necessary rpc spec files.")
public class PrintSpec implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(PrintSpec.class);
    private final String errorsXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
        + "<!DOCTYPE errors [\n"
        + "    <!ELEMENT errors (error)*>\n"
        + "    <!ELEMENT error (comment)*>\n"
        + "    <!ATTLIST error\n"
        + "        code CDATA #REQUIRED\n"
        + "        error_class CDATA #REQUIRED\n"
        + "        message CDATA #REQUIRED\n"
        + "        modifiable CDATA #REQUIRED>\n"
        + "    <!ELEMENT comment (#PCDATA)>\n"
        + "    ]>\n"
        + "<errors>\n"
        + "    <error error_class=\"InvalidRequest\" code=\"-32600\" message=\"Invalid Request\" modifiable=\"false\"/>\n"
        + "    <error error_class=\"ParseError\" code=\"-32700\" message=\"Parse error\" modifiable=\"false\"/>\n"
        + "    <error error_class=\"MethodNotFound\" code=\"-32601\" message=\"Method not found\" modifiable=\"true\"/>\n"
        + "    <error error_class=\"InvalidParams\" code=\"-32602\" message=\"Invalid params\"  modifiable=\"false\"/>\n"
        + "    <error error_class=\"InternalError\" code=\"-32603\" message=\"Internal error\"  modifiable=\"true\"/>\n"
        + "</errors>";
    private final String typesXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
        + "<!DOCTYPE types [\n"
        + "    <!ELEMENT types (encode-error|decode-error|regexes|union|composite|array|constrained|enum|param|primitives)*>\n"
        + "    <!ELEMENT encode-error (#PCDATA)>\n"
        + "    <!ATTLIST encode-error\n"
        + "        error_class CDATA #REQUIRED>\n"
        + "    <!ELEMENT decode-error (#PCDATA)>\n"
        + "    <!ATTLIST decode-error\n"
        + "        error_class CDATA #REQUIRED>\n"
        + "    <!ELEMENT regexes (regex)*>\n"
        + "    <!ELEMENT regex (#PCDATA)>\n"
        + "    <!ATTLIST regex\n"
        + "        name CDATA #REQUIRED\n"
        + "        value CDATA #REQUIRED\n"
        + "        comment CDATA #REQUIRED>\n"
        + "    <!ELEMENT union (type-union)*>\n"
        + "    <!ELEMENT type-union (comment|union-element)*>\n"
        + "    <!ATTLIST type-union\n"
        + "        nullable CDATA #REQUIRED\n"
        + "        typeName CDATA #REQUIRED>\n"
        + "    <!ELEMENT comment (#PCDATA)>\n"
        + "    <!ELEMENT union-element (comment)*>\n"
        + "    <!ATTLIST union-element\n"
        + "        name CDATA #REQUIRED\n"
        + "        type CDATA #REQUIRED>\n"
        + "    <!ELEMENT composite (type-composite)*>\n"
        + "    <!ELEMENT type-composite (comment|field)*>\n"
        + "    <!ATTLIST type-composite\n"
        + "        typeName CDATA #REQUIRED>\n"
        + "    <!ELEMENT field (comment)*>\n"
        + "    <!ATTLIST field\n"
        + "        fieldName CDATA #REQUIRED\n"
        + "        index CDATA #IMPLIED\n"
        + "        required CDATA #REQUIRED\n"
        + "        type CDATA #REQUIRED>\n"
        + "    <!ELEMENT array (type-list)*>\n"
        + "    <!ELEMENT type-list (#PCDATA)>\n"
        + "    <!ATTLIST type-list\n"
        + "        nestedType CDATA #REQUIRED\n"
        + "        typeName CDATA #REQUIRED>\n"
        + "    <!ELEMENT constrained (type-constrained)*>\n"
        + "    <!ELEMENT type-constrained (#PCDATA)>\n"
        + "    <!ATTLIST type-constrained\n"
        + "        baseType CDATA #REQUIRED\n"
        + "        max CDATA #REQUIRED\n"
        + "        min CDATA #REQUIRED\n"
        + "        regex CDATA #REQUIRED\n"
        + "        typeName CDATA #REQUIRED>\n"
        + "    <!ELEMENT enum (type-enum)*>\n"
        + "    <!ELEMENT type-enum (value)*>\n"
        + "    <!ATTLIST type-enum\n"
        + "        internalType CDATA #REQUIRED\n"
        + "        typeName CDATA #REQUIRED>\n"
        + "    <!ELEMENT value (#PCDATA)>\n"
        + "    <!ATTLIST value\n"
        + "        name CDATA #REQUIRED\n"
        + "        var CDATA #REQUIRED>\n"
        + "    <!ELEMENT param (type-params-wrapper)*>\n"
        + "    <!ELEMENT type-params-wrapper (field)*>\n"
        + "    <!ATTLIST type-params-wrapper\n"
        + "        typeName CDATA #REQUIRED>\n"
        + "    <!ELEMENT primitives (type-primitive)*>\n"
        + "    <!ELEMENT type-primitive (#PCDATA)>\n"
        + "    <!ATTLIST type-primitive\n"
        + "        typeName CDATA #REQUIRED>\n"
        + "    ]>\n"
        + "<types>\n"
        + "    <encode-error error_class=\"ParseError\"/>\n"
        + "    <decode-error error_class=\"ParseError\"/>\n"
        + "    <regexes>\n"
        + "        <regex name=\"hexPattern\" value=\"^0x[0-9a-fA-F]+$\" comment=\"Validates that a hex string.\"/>\n"
        + "        <regex name=\"unsignedDecPattern\" value=\"^[0-9]+$\" comment=\"Validates an unsigned decimal string.\"/>\n"
        + "        <regex name=\"unsignedHexPattern\" value=\"^0x([0-9a-fA-F]{2})+$\" comment=\"Validates that a number is encoded as a hex string. This is different from hex pattern since this validates that the string correctly encodes a byte array.\"/>\n"
        + "        <regex name=\"decPattern\" value=\"^[-+]?[0-9]+$\" comment=\"Validates a signed decimal string.\"/>\n"
        + "        <regex name=\"booleanPattern\" value=\"^([Tt]rue|[Ff]alse)$\" comment=\"Validates a boolean string.\"/>\n"
        + "        <regex name=\"byteArrayPattern\" value=\"^0x([0-9a-fA-F]{2})*$\" comment=\"Validates a byte hex string. This is different from unsignedHexPattern since empty byte arrays are allowed here.\"/>\n"
        + "        <regex name=\"aionAddressPattern\" value=\"^0x([Aa]0[a-fA-F0-9]{62}|0{64}|0{61}200)$\"\n"
        + "            comment=\"Validates that an aion address is valid\"/>\n"
        + "    </regexes>\n"
        + "    <union>\n"
        + "        <!--Unions are to be used anywhere multiple types are valid but only one should be used.-->\n"
        + "        <!--They are included in the RPC spec to ensure that all types are checked on compile time\n"
        + "        and so we avoid using language specific features like overloading or universal types. -->\n"
        + "    </union>\n"
        + "    <composite>\n"
        + "        <!--Composite types are used to define struct like data structures-->\n"
        + "        <type-composite typeName=\"request\">\n"
        + "            <comment>This is the standard request body for a JSON RPC Request</comment>\n"
        + "            <field fieldName=\"id\" required=\"false\" type=\"int\"/>\n"
        + "            <field fieldName=\"method\" required=\"true\" type=\"string\"/>\n"
        + "            <field fieldName=\"params\" required=\"false\" type=\"any\"/>\n"
        + "            <field fieldName=\"jsonrpc\" required=\"false\" type=\"version_string\"/>\n"
        + "        </type-composite>\n"
        + "        <type-composite typeName=\"response\">\n"
        + "            <comment>This is the standard response body for a JSON RPC Request</comment>\n"
        + "            <field fieldName=\"id\" required=\"false\" type=\"int\"/>\n"
        + "            <field fieldName=\"result\" required=\"false\" type=\"any\"/>\n"
        + "            <field fieldName=\"error\" required=\"false\" type=\"error\"/>\n"
        + "            <field fieldName=\"jsonrpc\" required=\"true\" type=\"version_string\"/>\n"
        + "        </type-composite>\n"
        + "        <type-composite typeName=\"error\">\n"
        + "            <comment>Contains the error messages for failed JSON RPC Requests</comment>\n"
        + "            <field fieldName=\"code\" required=\"true\" type=\"int\"/>\n"
        + "            <field fieldName=\"message\" required=\"true\" type=\"string\" />\n"
        + "        </type-composite>\n"
        + "    </composite>\n"
        + "    <array>\n"
        + "        <!--Array types are self explanatory. They can nest a composite type, constrained type, primitive type or param type.-->\n"
        + "        <!--TODO explicitly prevent nesting of arrays-->\n"
        + "        <type-list typeName=\"request_array\" nestedType=\"request\"/>\n"
        + "        <type-list typeName=\"response_array\" nestedType=\"response\"/>\n"
        + "    </array>\n"
        + "    <constrained>\n"
        + "        <!--Constrained types define a maximum and minimum size, and a regex to be used for validating the type-->\n"
        + "        <!--They all rely on a base type-->\n"
        + "        <!--The regex constraint for decimal string was implemented above to minimize differences between languages.\n"
        + "        The java compiler complains with any string that contains a / that is not used to escape a literal.-->\n"
        + "    </constrained>\n"
        + "    <enum>\n"
        + "        <!--Enum types are used in the case of a string or value with a restricted set of values-->\n"
        + "        <type-enum typeName=\"version_string\" internalType = \"string\">\n"
        + "            <value name=\"Version2\" var=\"2.0\"/>\n"
        + "        </type-enum>\n"
        + "    </enum>\n"
        + "    <param>\n"
        + "        <!-- param types specific to each method-->\n"
        + "    </param>\n"
        + "    <primitives>\n"
        + "        <!--Base types-->\n"
        + "        <type-primitive typeName=\"any\"/><!--TODO remove-->\n"
        + "        <type-primitive typeName=\"bool\"/>\n"
        + "        <type-primitive typeName=\"string\"/>\n"
        + "        <type-primitive typeName=\"long\"/>\n"
        + "        <type-primitive typeName=\"int\"/>\n"
        + "        <type-primitive typeName=\"byte\"/>\n"
        + "        <type-primitive typeName=\"bigint\"/>\n"
        + "        <type-primitive typeName=\"byte-array\"/>\n"
        + "        <type-primitive typeName=\"address\"/>\n"
        + "        <type-primitive typeName=\"uint16\"/>\n"
        + "        <type-primitive typeName=\"uint32\"/>\n"
        + "        <type-primitive typeName=\"uint64\"/>\n"
        + "        <type-primitive typeName=\"uint128\"/>\n"
        + "        <type-primitive typeName=\"uint256\"/>\n"
        + "    </primitives>\n"
        + "</types>";
    private final String rpcXML = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n"
        + "<!DOCTYPE rpc [\n"
        + "    <!ELEMENT rpc (comments|methods)*>\n"
        + "    <!ELEMENT comments (comment)*>\n"
        + "    <!ELEMENT comment (#PCDATA)>\n"
        + "    <!ELEMENT methods (method)*>\n"
        + "    <!ELEMENT method (comment|errors)*>\n"
        + "    <!ATTLIST method\n"
        + "        name CDATA #REQUIRED\n"
        + "        param CDATA #REQUIRED\n"
        + "        returnType CDATA #REQUIRED\n"
        + "        namespace CDATA #REQUIRED>\n"
        + "    <!ELEMENT errors (error)*>\n"
        + "    <!ELEMENT error (#PCDATA)>\n"
        + "    <!ATTLIST error\n"
        + "        value CDATA #REQUIRED>\n"
        + "    ]>\n"
        + "<rpc>\n"
        + "    <comments>\n"
        + "    </comments>\n"
        + "    <methods>\n"
        + "    </methods>\n"
        + "</rpc>";
    @Option(names = {"-f",
        "--file"}, description = "output spec directory", defaultValue = "./spec")
    public String specFilePath;
    @Option(names = "--verbose")
    boolean verbose;

    private static void exitWithError() {
        logger.error("Generator failed exiting.");
        System.exit(ExitCodes.Error.code);
    }

    private static void exitWithSuccess() {
        logger.info("Done.");
        System.exit(ExitCodes.Normal.code);
    }

    @Override
    public void run() {
        setLogLevel();
        logger.info("Printing rpc spec...");
        Path path = checkPath(Paths.get(specFilePath));
        try {
            writeFile(errorsXML, "errors.xml", path);
            writeFile(rpcXML, "rpc.xml", path);
            writeFile(typesXML, "types.xml", path);
        } catch (Exception e) {
            logger.error("Failed to print spec.");
            logger.debug("Encountered error: ", e);
        }
        exitWithSuccess();
    }

    private Path checkPath(final Path path) {
        if (Files.exists(path)) { // check that the directory is empty to avoid overwriting files
            if (Files.isDirectory(path) && !isDirectoryEmpty(path)) {
                logger.error("Output directory is not empty.");
                exitWithError();
            } else if (Files.exists(path) && Files.isRegularFile(path)) {
                logger.error("Path is not a directory.");
                exitWithError();
            }
            return path;
        } else {
            try {
                return Files.createDirectories(path);// create the directory
            } catch (IOException e) {
                logger.error("Unable to create output directory.");
                logger.debug("Failed with exception: ", e);
                exitWithError();
                return null;// we never get here
            }
        }
    }

    private void writeFile(String data, String name, Path specFilePath) throws IOException {
        Path outputPath = Paths.get(
            Utils.appendToPath(specFilePath.toAbsolutePath().toString(), name));
        logger.info("Creating: {}", outputPath.toString());
        Files.createFile(outputPath);
        Files.write(outputPath, Arrays.asList(data.split("\\n")));// print out the new spec file
    }

    private boolean isDirectoryEmpty(Path path) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            return directoryStream.iterator().hasNext();
        } catch (IOException e) {
            return false;
        }
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
}
