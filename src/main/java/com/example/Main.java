package com.example;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Main {

    public boolean verifyTableCellText(Node table, String searchColumn, String searchText, int returnColumn, String expectedText) {
        try {
            Node actualText = getTableCellTextByXpath(table, searchColumn, searchText, returnColumn);
            if(actualText == null)
               return false;
            String a = actualText.getTextContent();
            boolean result = a.equals(expectedText);
            if (result) {
                System.out.println("Success: Expected : " + expectedText + ", Actual : " + actualText.getTextContent());
            } else {
                System.out.println("Failed: Expected : " + expectedText + ", Actual : " + actualText.getTextContent());
            }
            return result;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return false;

        }
    }

    public Node getTableCellTextByXpath(Node table, String searchColumn, String searchText, int returnColumn) throws XPathExpressionException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        String xpathExpression = String.format(". //row/cell[' %s  '][text()='%s']/following-sibling::cell[position() = '%s']", searchColumn, searchText, returnColumn);
        XPathExpression expr = xpath.compile(xpathExpression);
        return (Node) expr.evaluate(table, XPathConstants.NODE);
    }

    public static void main(String[] args) throws Exception {
        Main verifier = new Main();
        String xmlFilePath = "src/main/resources/data.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(xmlFilePath));
        Node tableElement = document.getElementsByTagName("myTable").item(0);
        verifier.verifyTableCellText(tableElement, "Company", "Ernst Handel", 1, "Roland Mendel");

    }
}
