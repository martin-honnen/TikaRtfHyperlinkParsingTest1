package org.example;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;


public class Main {
    public static void main(String[] args) throws TikaException, IOException, SAXException, TransformerConfigurationException {
        String resultHtml = parseToHTML2("hyperlink.rtf");
        System.out.println(resultHtml);

    }
    public static String parseToHTML2(String file) throws IOException, SAXException, TikaException, TransformerConfigurationException {
        TransformerHandler handler = ((SAXTransformerFactory)TransformerFactory.newInstance()).newTransformerHandler();//new ToXMLContentHandler();

        StringWriter sw = new StringWriter();
        handler.setResult(new StreamResult(sw));

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = new FileInputStream(file)) {
            parser.parse(stream, handler, metadata);
            return sw.toString();
        }
    }
    public static String parseToHTML(String file) throws IOException, SAXException, TikaException {
        ContentHandler handler = new ToXMLContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = new FileInputStream(file)) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }
}