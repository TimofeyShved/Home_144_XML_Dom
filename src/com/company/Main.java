package com.company;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // Что бы прочитать XML файл, нужно создать сборщик
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // читаем файл
        Document document = builder.parse(new File("text.xml"));

        // выбрать главный элемент и вывести его
        Element element = document.getDocumentElement();
        System.out.println(element.getTagName());

        // вывести дочерние элементы
        NodeList nodeList = element.getChildNodes();
        for (int i =0; i<nodeList.getLength(); i++){
            if (nodeList.item(i) instanceof Element){
                System.out.println((Element) nodeList.item(i));
                // либо
                System.out.println(((Element) nodeList.item(i)).getTagName() +" = "+((Element) nodeList.item(i)).getTextContent());
            }
        }
    }
}
