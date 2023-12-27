package com.example;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class MainTest {
    
    @Test
    public void testVerifyTableCellText() throws Exception {
        Main verifier = new Main();
        String xmlFilePath = "src/main/resources/data.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(xmlFilePath));
        Node tableElement = document.getElementsByTagName("myTable").item(0);
        verifier.verifyTableCellText(tableElement, "Company", "Ernst Handel", 1, "Roland Mendel");
    }

}
