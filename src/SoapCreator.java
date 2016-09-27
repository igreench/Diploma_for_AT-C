import org.apache.commons.codec.binary.Base64;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import soap.crypto.Crypto;
import soap.request.Request;
import soap.status.Status;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.UUID;

/**
 * Created by Olcha on 01.05.2016.
 */
public class SoapCreator {
    public SoapCreator(){

    }
    static UUID guid = UUID.randomUUID();
    public static void main(String[] args) throws Exception {
//        createRequest();
        //getStatus();
        //Crypto cr = new Crypto();
        //cr.init();


    }

    public static void createRequest() throws Exception {
        Request s = new Request(guid);
        String text = s.generateRequest().asXML();
        System.out.println("REQUEST: " + text);
        sendPost(text);
    }

    public static void getStatus() throws Exception {
        Status s = new Status(guid);
        String text = s.generateStatus().asXML();
        System.out.println("STATUS: " + text);
        sendPost(text);
    }

    private static void sendPost(String send) throws Exception {
        SOAPMessage message = MessageFactory.newInstance().createMessage(new MimeHeaders(), new ByteArrayInputStream(send.getBytes()));
        System.out.println("soap: " + message.toString());
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://smevrec.thprom.ru/smev2/smevService2";
            SOAPMessage soapResponse = soapConnection.call(message, url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }

    }

    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);

    }
}
