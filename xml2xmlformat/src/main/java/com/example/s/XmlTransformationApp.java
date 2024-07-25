package com.example.s;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XmlTransformationApp {
    public static void main(String[] args) {
        try {
            Document inputDoc = XmlParser.parseXml("C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task\\src\\main\\resources\\input.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document outputDoc = docBuilder.newDocument();

            XmlTransformer.transformXml(inputDoc, outputDoc);
            XmlWriter.writeXml(outputDoc, "C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task\\src\\main\\resources\\output1.xml");

            System.out.println("XML transformation completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
