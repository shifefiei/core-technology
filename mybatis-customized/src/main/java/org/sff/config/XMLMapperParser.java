package org.sff.config;

import org.dom4j.Element;

import java.util.List;

public class XMLMapperParser {

    private Configuration configuration;

    public XMLMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(Element rootElement) {
        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectElement = rootElement.elements("select");
        for (Element element : selectElement) {

            XMLStatementParser statementParser = new XMLStatementParser(configuration);
            statementParser.parseStatement(element, namespace);


        }

    }
}
