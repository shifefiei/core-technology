package org.sff.config;

import org.dom4j.Element;

import java.util.List;

public class XMLMapperParser {

    private MyConfiguration configuration;

    public XMLMapperParser(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    public void parse(Element rootElement) {
        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectElement = rootElement.elements("select");
        for (Element element : selectElement) {
            XMLSqlScriptParser sqlScriptParser = new XMLSqlScriptParser(configuration);
            sqlScriptParser.parseSqlScript(element);

        }

    }
}
