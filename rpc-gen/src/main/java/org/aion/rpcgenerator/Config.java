package org.aion.rpcgenerator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.aion.rpcgenerator.util.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Config {

    private final Map<DefinitionTypes, Map<String, String>> specToTemplateToFileName;
    private final Map<DefinitionTypes, String> definitionToFileName;
    private final String typesTemplateDirectory;
    private final String errorsTemplateDirectory;
    private final String rpcTemplateDirectory;
    public Config(Path configFile) throws IOException, ParserConfigurationException, SAXException {
        Document configXml = XMLUtils.fromFile(configFile.toFile());
        Element rootNode = configXml.getDocumentElement();
        Element definitionsElement = XMLUtils.elementFromTag(rootNode, "spec").orElseThrow();
        DefinitionTypes[] definitionTypes = DefinitionTypes.values();
        EnumMap<DefinitionTypes, String> tempMap1 = new EnumMap<>(DefinitionTypes.class);
        EnumMap<DefinitionTypes, Map<String, String>> tempMap = new EnumMap<>(
            DefinitionTypes.class);

        for (DefinitionTypes definitionTypes1 : definitionTypes) {
            tempMap.put(definitionTypes1, getValue(definitionTypes1.templateNodeName, rootNode));
            tempMap1.put(definitionTypes1, getFileName(definitionsElement, definitionTypes1.value));
        }

        specToTemplateToFileName = Collections.unmodifiableMap(tempMap);
        definitionToFileName = Collections.unmodifiableMap(tempMap1);

        typesTemplateDirectory = getDirFromConfig(rootNode, DefinitionTypes.TYPE);
        rpcTemplateDirectory = getDirFromConfig(rootNode, DefinitionTypes.RPC);
        errorsTemplateDirectory = getDirFromConfig(rootNode, DefinitionTypes.ERROR);

    }

    private String getDirFromConfig(Element rootNode, DefinitionTypes type) {
        return XMLUtils.optionalValueFromAttribute(
            XMLUtils.elementFromTag(rootNode, type.templateNodeName).orElseThrow(), "dir")
            .orElse(type.value);
    }

    private String getFileName(Element definitionsElement, String nodeName) {
        return XMLUtils.elementFromTag(definitionsElement, nodeName)
            .map(element -> XMLUtils.valueFromAttribute(element, "specfile")).orElseThrow();
    }

    private Map<String, String> getValue(String nodeName, Element rootNode) {
        Map<String, String> errors = new HashMap<>();
        Element errorTemplatesElement = XMLUtils.elementFromTag(rootNode, nodeName).orElseThrow();
        for (Element element : XMLUtils.elements(errorTemplatesElement.getChildNodes())) {
            errors.put(XMLUtils.valueFromAttribute(element, "templatefile"),
                XMLUtils.valueFromAttribute(element, "outputfile"));
        }
        return Collections.unmodifiableMap(errors);
    }

    /**
     * Returns the output file names for errors
     *
     * @param templateName the template being processed
     * @return the output file name
     */
    public String errorFileName(String templateName) {
        return specToTemplateToFileName.get(DefinitionTypes.ERROR).get(templateName);
    }

    /**
     * Returns the output file names for errors
     *
     * @param templateName the template being processed
     * @return the output file name
     */
    public String rpcFileName(String templateName) {
        return specToTemplateToFileName.get(DefinitionTypes.RPC).get(templateName);
    }

    /**
     * Returns the output file names for error definitions
     *
     * @param templateName the template being processed
     * @return the output file name
     */
    public String typeFileName(String templateName) {
        return specToTemplateToFileName.get(DefinitionTypes.TYPE).get(templateName);
    }

    /**
     *
     * @return the rpc spec file name
     */
    public String getRPCSpecFileName() {
        return definitionToFileName.get(DefinitionTypes.RPC);
    }

    /**
     *
     * @return the type spec file name
     */
    public String getTypeSpecFileName() {
        return definitionToFileName.get(DefinitionTypes.TYPE);
    }

    /**
     *
     * @return the error spec file name
     */
    public String getErrorSpecFileName() {
        return definitionToFileName.get(DefinitionTypes.ERROR);
    }

    /**
     *
     * @return the types directory
     */
    public String getTypesTemplateDirectory() {
        return typesTemplateDirectory;
    }

    /**
     *
     * @return the errors directory
     */
    public String getErrorsTemplateDirectory() {
        return errorsTemplateDirectory;
    }

    /**
     *
     * @return the rpc directory
     */
    public String getRpcTemplateDirectory() {
        return rpcTemplateDirectory;
    }

    private enum DefinitionTypes {
        ERROR("error", "error-templates"), RPC("rpc", "rpc-templates"), TYPE("type",
            "type-templates");
        public final String value;
        public final String templateNodeName;

        DefinitionTypes(String value, String templateNodeName) {

            this.value = value;
            this.templateNodeName = templateNodeName;
        }
    }
}
