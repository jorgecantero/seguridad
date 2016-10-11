
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dovdo
 */
public class SAX {

    ManejadorSAX sh;
    File ficheroXML;
    SAXParser parser;

    public String recorrerSAX() {
        try {
            parser.parse(ficheroXML, sh);
            return sh.cadena_resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        }
    }

    public int abrir_XML_SAX(File fichero) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //Se crea un objeto SAXParser para interpretar el documento XML.
            parser = factory.newSAXParser();
            //Se crea una instancia del manejador que será el que recorra
            //el documento XML secuencialmente
            sh = new ManejadorSAX();
            ficheroXML = fichero;
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    private static class nPublicado {

        public nPublicado() {
        }
    }
}

class ManejadorSAX extends DefaultHandler {

    File fXML;
    ManejadorSAX sh;
    SAXParser parser;
    int ultimoelement;
    String cadena_resultado = "";

    public ManejadorSAX() {
        ultimoelement = 0;
        Attributes atts;
    }

    public void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
        if (qName.equals("Libros")) {
            cadena_resultado = cadena_resultado + "\nse van a mostrar libros ";
            cadena_resultado = cadena_resultado + "\n----------------------- ";

        } else if (qName.equals("Libro")) {
                cadena_resultado = cadena_resultado + "\nPublicado en: " + atts.getValue(atts.getQName(0)) + "\n ";
                ultimoelement = 1;
            
        } else if (qName.equals("Titulo")) {
            ultimoelement = 2;
            cadena_resultado = cadena_resultado + "\nEl título es: ";
        } else if (qName.equals("Autor")) {
            ultimoelement = 3;
            cadena_resultado = cadena_resultado + "\nEl autor es: ";
        } else if (qName.equals("Editorial")) {
            ultimoelement = 4;
            cadena_resultado = cadena_resultado + "\nLa  editorial es: ";
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equals("Libro")) {
            System.out.println("He encontrado el final de un elemento.");
            cadena_resultado = cadena_resultado + "\n -------------------";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws
            SAXException {
       
         if (ultimoelement == 1) {
            for (int i = start; i < length + start; i++) {
                cadena_resultado = cadena_resultado + ch[i];
            }
        
         } else if (ultimoelement == 2) {
            for (int i = start; i < length + start; i++) {
                cadena_resultado = cadena_resultado + ch[i];
            }
        } else if (ultimoelement == 3) {
            for (int i = start; i < length + start; i++) {
                cadena_resultado = cadena_resultado + ch[i];
            }
        } else if (ultimoelement == 4) {
            for (int i = start; i < length + start; i++) {
                cadena_resultado = cadena_resultado + ch[i];
            }
        }
    }

    public String recorrerSAX(File fXML, ManejadorSAX sh, SAXParser parser) {
        try {
            parser.parse(fXML, sh);
            return sh.cadena_resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        }
    }
}
