package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadFile {

    public Properties getProperties(String propFileName) {

        InputStream inputStream = null;
        Properties prop = new Properties();

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return prop;
    }

    public Document loadXML(String fileName) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File(fileName));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public Map<String, LocatorLivat> readXMLLocatorData(Document doc) {
        LocatorLivat locator = null;
        HashMap<String, LocatorLivat> map = new HashMap<>();
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("ElementInfo");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element eElement = (Element) node;
            locator = new LocatorLivat(eElement.getAttribute("Name"));
            locator.setDescription(eElement.getAttribute("Description"));
            locator.setType(eElement.getAttribute("Type"));
            locator.setValue(eElement.getTextContent().replace("\n", "").trim());
            map.put(eElement.getAttribute("Name"), locator);
        }
        return map;
    }


}
