package org.aion.rpcgenerator.data;

import java.util.Map;
import org.aion.rpcgenerator.Mappable;
import org.aion.rpcgenerator.util.XMLUtils;
import org.w3c.dom.Element;

public class Pattern implements Mappable {

    private final String name;
    private final String regex;
    private final String comment;
    public Pattern(Element element) {
        name = XMLUtils.valueFromAttribute(element, "name");
        regex = XMLUtils.valueFromAttribute(element, "value");
        comment = XMLUtils.valueFromAttribute(element, "comment");
    }

    @Override
    public Map<String, Object> toMap() {
        return Map.ofEntries(Map.entry("name", name), Map.entry("regex", regex), Map.entry("comment", comment));
    }
}
