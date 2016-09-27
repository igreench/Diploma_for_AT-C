package soap;

import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.dom4j.Element;
import org.xml.sax.SAXException;
import soap.crypto.AttachSign;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.UUID;

/**
 * Created by Olcha on 01.05.2016.
 */
public class MessageData {

    public Element createMessageData(Element body, String type, String reqNumber, UUID guid) throws Exception {
        Element messageData = body.addElement("smev:MessageData");
        Element appData = messageData.addElement("smev:AppData");
        if (type.equals("request")) {
            Element createRequestBean = appData.addElement("ns2:createRequestBean");
            Element okato = createRequestBean.addElement("ns2:okato")
                    .addText("29202808001");
            Element requestType = createRequestBean.addElement("ns2:requestType")
                    .addText("558101010000");
            Element appDocuments = messageData.addElement("smev:AppDocument");

            Element requestCode = appDocuments.addElement("smev:RequestCode")
                    .addText(guid.toString());
            AttachSign as = new AttachSign(guid, null);

            String attachment = as.createAttachment();

            Element binaryData = appDocuments.addElement("smev:BinaryData")
                    .addText(attachment);
        }else {
            Element requestNumber = appData.addElement("ns2:requestNumber")
                    .addText(reqNumber);
        }
        return null;
    }
}
