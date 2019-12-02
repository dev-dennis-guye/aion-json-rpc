package org.aion.rpcgenerator.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.aion.rpcgenerator.Mappable;
import org.aion.rpcgenerator.util.SchemaUtils;
import org.aion.rpcgenerator.util.XMLUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CompositeType extends Type {

    private List<Field> fieldList;

    CompositeType(Element node) {
        super(node);
        NodeList nodes = node.getChildNodes();

        fieldList = new ArrayList<>();
        for (Element fieldNode : XMLUtils.elements(nodes)) {
            if (fieldNode.getNodeName().equals("field")) {
                fieldList.add(new Field(
                    XMLUtils.valueFromAttribute(fieldNode, "fieldName"),
                    XMLUtils.valueFromAttribute(fieldNode, "type"),
                    XMLUtils.valueFromAttribute(fieldNode, "required"),
                    SchemaUtils.getComments(fieldNode.getChildNodes()),
                    XMLUtils.optionalValueFromAttribute(fieldNode, "defaultValue").orElse("")));
            }
        }
    }

    CompositeType(String name, List<String> comments, List<Field> fields) {
        super(name, comments);
        fieldList = fields;
    }

    public boolean setFieldTypeDef(List<Type> types) {
        boolean result = true;
        for (Field field : fieldList) {
            result &= field.setTypeDef(types);
        }
        return result;
    }

    @Override
    protected Map<String, Object> toMutableMap() {
        Map<String, Object> superMap = super.toMutableMap();
        List<Map<String, Object>> mapList = fieldList.stream().map(Field::toMap)
            .collect(Collectors.toUnmodifiableList());
        superMap.put("fields", mapList);
        return superMap;
    }

    public static class Field implements Mappable {

        private String fieldName;
        private String typeName;
        private String required;
        private Type type;
        private List<String> comments;
        private String defaultValue;

        public Field(String fieldName, String typeName, String required,
            List<String> comment, String defaultValue) {
            this.fieldName = fieldName;
            this.typeName = typeName;
            this.required = required;
            this.comments = comment;
            this.defaultValue = defaultValue;
        }

        public Map<String, Object> toMap() {
            if (defaultValue.equalsIgnoreCase("")) {
                return Map.ofEntries(
                    Map.entry("fieldName", fieldName),
                    Map.entry("type", type.toMap()),
                    Map.entry("required", required),
                    Map.entry("comments", comments)
                );
            }
            else {
                return Map.ofEntries(
                    Map.entry("fieldName", fieldName),
                    Map.entry("type", type.toMap()),
                    Map.entry("required", required),
                    Map.entry("comments", comments),
                    Map.entry("defaultValue", defaultValue)
                );
            }
        }

        public boolean setTypeDef(List<Type> types) {
            for (var type : types) {
                if (type.name.equals(this.typeName)) {
                    this.type = type;
                    return true;
                }
            }
            System.out.println("Failed on :"+this.typeName);
            throw new NoSuchElementException();
        }

    }
}
