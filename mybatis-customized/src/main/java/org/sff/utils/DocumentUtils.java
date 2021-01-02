package org.sff.utils;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class DocumentUtils {

    /**
     * 利用 dom4j 从流中解析xml文件
     */
    public static Document readDocument(InputStream inputStream) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        return document;
    }
}
