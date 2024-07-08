/*
 * Created on 22/12/2004
 *
 */
package util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Transforms a XML element into a string.
 * 
 * @author Adolfo Gustavo Serra Seca Neto
 * 
 */
public class XMLViewer {

    /**
     * Transforms a XML element into a string.
     * 
     * @param el -
     *            a XML element
     * @return the string representing the XML element.
     */
    public static String getXMLElementAsString(Element el) {
        Document doc = new Document();

        doc.setRootElement(el);

        try {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            return outputter.outputString(doc);
        } catch (Exception e) {
            System.err.println(e);
            System.exit(0);
        }

        return "";
    }
    
}