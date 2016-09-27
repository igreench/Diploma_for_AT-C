package soap.status;

import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xml.sax.SAXException;
import soap.crypto.Crypto;
import soap.Header;
import soap.Message;
import soap.MessageData;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.UUID;

/**
 * Created by Olcha on 12.05.2016.
 */
public class Status {
    UUID guid;
    public Status(UUID guid){
        this.guid = guid;
    }

    public Element generateStatus() throws Exception {
        Crypto crypto = new Crypto();
        crypto.init();
        String cert = crypto.getCertification();

        Document xml = DocumentHelper.createDocument();
        Element root = createRoot(xml);
        //Element a = Header.createHeader(root);
        Element b = createBody();
        String hash = crypto.getHash(b);

        Header h = new Header(cert, hash);
        h.createHeader(root);
        root.add(b);
        return root;
    }

    public Element createRoot(Document xml){
        Element root = xml.addElement("soap:Envelope")
                .addNamespace("soap", "http://schemas.xmlsoap.org/soap/envelope/")
                .addNamespace("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd")
                .addNamespace("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
                .addNamespace("ds", "http://www.w3.org/2000/09/xmldsig#");
        return root;
    }

    public Element createBody() throws Exception {
        Element body = DocumentHelper.createElement("soap:Body")
                .addNamespace("soap", "http://schemas.xmlsoap.org/soap/envelope/")
                .addNamespace("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
                .addNamespace("ns4", "http://portal.fccland.ru/rt/")
                .addNamespace("ns2", "http://portal.fccland.ru/types/")
                .addNamespace("ns3", "http://portal.fccland.ru/rt/")
                .addNamespace("smev", "http://smev.gosuslugi.ru/rev120315")
                .addAttribute("wsu:Id","body");
        Element getStatusRequest = body.addElement("ns3:getStatusRequest")
                .addNamespace("", "http://smev.gosuslugi.ru/rev111111");
        Message message = new Message();
        message.createMessage(getStatusRequest, "status", guid);
        MessageData messageData = new MessageData();
        messageData.createMessageData(getStatusRequest, "status", null, guid);
        return body;
    }
}
