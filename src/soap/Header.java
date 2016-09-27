package soap;

import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.dom4j.Element;
import org.xml.sax.SAXException;
import soap.crypto.Crypto;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Created by Olcha on 01.05.2016.
 */
public class Header {
    String certificate;
    String hash;
    public Header(String c, String h){
        certificate = c;
        hash = h;
    }

    public Element createHeader(Element root) throws IOException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, CertificateException, ParserConfigurationException, NoSuchProviderException, SAXException, InvalidCanonicalizerException, SignatureException, KeyStoreException, CanonicalizationException {
        Element header = root.addElement("soap:Header");
        Element security = header.addElement("wsse:Security")
                .addAttribute("soap:actor", "http://smev.gosuslugi.ru/actors/smev");
        Element binarySecurityToken = security.addElement("wsse:BinarySecurityToken")
                .addAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary")
                .addAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3")
                .addAttribute("wsu:Id", "Cert")
                .addText(certificate);
        Element signature = security.addElement("ds:Signature")
                .addAttribute("Id", "Signature-933");
        Element signedInfo = signature.addElement("ds:SignedInfo");
        Element canonicalizationMethod = signedInfo.addElement("ds:CanonicalizationMethod")
                .addAttribute("Algorithm", "http://www.w3.org/2001/10/xml-exc-c14n#");
        Element signatureMethod = signedInfo.addElement("ds:SignatureMethod")
                .addAttribute("Algorithm", "http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411");
        Element reference = signedInfo.addElement("ds:Reference")
                .addAttribute("URI", "#body");
        Element transforms = reference.addElement("ds:Transforms");
        Element transform = transforms.addElement("ds:Transform")
                .addAttribute("Algorithm", "http://www.w3.org/2001/10/xml-exc-c14n#");
        Element digestMethod = reference.addElement("ds:DigestMethod")
                .addAttribute("Algorithm", "http://www.w3.org/2001/04/xmldsig-more#gostr3411");
        Element digestValue = reference.addElement("ds:DigestValue")
                .addText(hash);
        Crypto crypto = new Crypto();
        crypto.init();
        String sign = crypto.getSignature(signedInfo);
        Element signatureValue = signature.addElement("ds:SignatureValue")
                .addText(sign);
        Element keyInfo = signature.addElement("ds:KeyInfo");
        Element securityTokenReference = keyInfo.addElement("wsse:SecurityTokenReference");
        Element wReference = securityTokenReference.addElement("wsse:Reference")
                .addAttribute("URI", "#Cert")
                .addAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        return header;
    }
}
