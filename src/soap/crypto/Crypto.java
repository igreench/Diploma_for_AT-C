package soap.crypto;

import org.apache.commons.codec.binary.Base64;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.dom4j.Element;
import org.xml.sax.SAXException;
import ru.CryptoPro.JCP.JCP;
import ru.CryptoPro.JCP.Sign.CryptoProSign;
import ru.CryptoPro.JCP.tools.Array;
import ru.CryptoPro.JCPxml.xmldsig.JCPXMLDSigInit;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.security.*;
import java.util.UUID;

/**
 * Created by Olcha on 25.04.2016.
 */
public class Crypto {
    final char[] PASSWORD = "1".toCharArray();
    String ALIAS = "";
    KeyStore keyStore;
    MessageDigest digestDriver;

    public void init() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException, NoSuchProviderException {
        com.sun.org.apache.xml.internal.security.Init.init();
        // Инициализация сервис-провайдера.
        if(!JCPXMLDSigInit.isInitialized()) {
            JCPXMLDSigInit.init();
        }
        //Provider provider = new ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI();
//        System.out.println(provider);
        digestDriver = MessageDigest.getInstance(JCP.GOST_DIGEST_NAME);
        keyStore = KeyStore.getInstance(JCP.HD_STORE_NAME);
        keyStore.load(null, null);
        Enumeration<String> aliases = keyStore.aliases();

        while (aliases.hasMoreElements()){
            ALIAS = aliases.nextElement();
            System.out.println("ALIAS: " + ALIAS);
        }
        //PrivateKey privateKey = (PrivateKey)ks.getKey(ALIAS,PASSWORD);
        //X509Certificate cert = (X509Certificate)ks.getCertificate(ALIAS);
    }

    public String getHash(Element xmlElement) throws IOException, ParserConfigurationException, SAXException, CanonicalizationException, InvalidCanonicalizerException {
        String canonXML = toCanonicalize(xmlElement);
        digestDriver.update(canonXML.getBytes("UTF-8"));
        byte[] digestValue = digestDriver.digest();
        String hash = DatatypeConverter.printBase64Binary(digestValue);
        return hash;
    }

    public String getSignature(Element signedInfo) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateEncodingException, NoSuchProviderException, InvalidKeyException, SignatureException, IOException, ParserConfigurationException, SAXException, CanonicalizationException, InvalidCanonicalizerException {
        //Provider provider = new ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI();
//        System.out.println(provider);
        //digestDriver = MessageDigest.getInstance("GOST3411-94",provider);
        Signature signatureDriver = Signature.getInstance(
                JCP.GOST_EL_SIGN_NAME
        );
        PrivateKey pKey = (PrivateKey) keyStore.getKey(ALIAS, PASSWORD);
        signatureDriver.initSign(pKey);
        String canonXML = toCanonicalize(signedInfo);
        byte[] nextDataChunk = canonXML.getBytes("UTF-8");
        signatureDriver.update(nextDataChunk);
        byte[] signatureValue = signatureDriver.sign();
        String sign = Base64.encodeBase64String(signatureValue);
        return sign;
    }

    public String getCertification() throws CertificateEncodingException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        X509Certificate cert = (X509Certificate) keyStore.getCertificate(ALIAS);
        String certificate = Base64.encodeBase64String(cert.getEncoded());
        return certificate;
    }

    public String toCanonicalize(Element xmlElement) throws InvalidCanonicalizerException, IOException, ParserConfigurationException, SAXException, CanonicalizationException {
        Canonicalizer canon = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
        byte[] canonBody = canon.canonicalize(xmlElement.asXML().getBytes("UTF-8"));
        String canonXML = new String(canonBody, "UTF-8");
        return canonXML;
    }

    public File PKCS7sign(String filename, UUID guid) throws Exception {
        keyStore = KeyStore.getInstance(JCP.HD_STORE_NAME);
        keyStore.load(null, null);
        PrivateKey privateKey = (PrivateKey) keyStore.getKey("dber", "1".toCharArray());
        X509Certificate certificate = (X509Certificate) keyStore.getCertificate("dber");

        // Создаем подпись, при этом сначала получаем хеш с файла.
        Signature signature = Signature.getInstance(JCP.GOST_DHEL_SIGN_NAME);
        signature.initSign(privateKey);
        readAndHash(signature, filename);
        // Создаем подпись.
        byte[] cms = CMS.createCMS(null, signature.sign(), certificate, true);

        // Пишем ее в файл - потом можно проверить в csptest:
        /*
            csptest -sfsign -verify -in "C:\TESTS\CMS\data.exe" -signature
            "C:\jcp_sig_64_data_exe.dat" -detached -cades_disable
         */
        //String sig = DatatypeConverter.printBase64Binary(Base64.decodeBase64(cms));
        //Array.writeFile("sig.txt", sig.getBytes());
        //Array.writeFile("sig.sig", cms);
        File file = new File("req_" + guid +  ".xml.sig");
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
        pw.print(DatatypeConverter.printBase64Binary(cms));
        pw.close();
        return file;
    }

    public Signature readAndHash(Signature signature, String fileName) throws Exception {

        File file = new File(fileName);
        FileInputStream fData = new FileInputStream(file);

        // Не очень удобный способ чтения, но ведь это пример.
        int read;
        while ( (read = fData.read()) != -1) {
            signature.update((byte)read);
        }

        fData.close();

        return signature;
    }


}
