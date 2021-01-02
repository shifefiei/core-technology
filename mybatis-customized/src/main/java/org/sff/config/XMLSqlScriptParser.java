package org.sff.config;

import org.dom4j.Element;

/**
 * 解析 mapper.xml 的sql脚本
 */
public class XMLSqlScriptParser {

    private MyConfiguration configuration;

    public XMLSqlScriptParser(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    public void parseSqlScript(Element element) {
    }
}
