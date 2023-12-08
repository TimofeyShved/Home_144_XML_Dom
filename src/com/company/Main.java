package com.company;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
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
        printElement(nodeList);
    }

    static void printElement(NodeList nodeList){

        for (int i =0; i<nodeList.getLength(); i++){
            if (nodeList.item(i) instanceof Element){
                System.out.print(((Element) nodeList.item(i)).getTagName());
                if(((Element) nodeList.item(i)).hasAttribute("category")){
                    System.out.println(" = "+((Element) nodeList.item(i)).getAttribute("category"));
                }else{
                    System.out.println(" = "+((Element) nodeList.item(i)).getTextContent());
                    /** либо
                    String value = "";
                    if(!nodeList.item(i).getTextContent().trim().isEmpty()
                            && !((Text)nodeList.item(i).getFirstChild()).getData().trim().isEmpty()
                            && !((Text)nodeList.item(i).getFirstChild()).getData().trim().equals("\n")){
                        Text text = (Text) ((Element) nodeList.item(i)).getFirstChild();
                        value += " = " + text.getData().trim();
                    }
                     **/
                }
                if (nodeList.item(i).hasChildNodes()){
                    printElement(nodeList.item(i).getChildNodes());
                }
            }
        }

    }
}
