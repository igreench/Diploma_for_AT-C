package soap.crypto;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.apache.xml.security.utils.Base64;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.util.Store;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xml.sax.SAXException;
import ru.CryptoPro.JCP.JCP;
import ru.CryptoPro.JCPxml.xmldsig.SignatureGostR34102001;
import soap.request.Attachment.UserData;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by Olcha on 07.06.2016.
 */
public class AttachSign {
    UUID guid;
    Element userXML;
    String[] data;

    public AttachSign(UUID r, String[] data){
        guid = r;
        this.data = data;
    }

    public String createAttachment() throws Exception {
        File zipFile = new File("req_" + guid + ".zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        ZipEntry xml = new ZipEntry("req_" + guid + ".xml");
        ZipEntry sig = new ZipEntry("req_" + guid + ".xml.sig");

        out.putNextEntry(xml);
        byte[] data = IOUtils.toByteArray(new FileInputStream(createXML()));
        out.write(data, 0, data.length);
        out.closeEntry();

        out.putNextEntry(sig);
        data = IOUtils.toByteArray(new FileInputStream(createSIG()));
        out.write(data, 0, data.length);
        out.closeEntry();

        out.close();
        String attachment = convertToBase64FromFile(zipFile);
        return attachment;
    }

    public String convertToBase64FromFile(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        byte[] bytes = null;
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] barr = new byte[1024];
            while (true) {
                int r = in.read(barr);
                if (r <= 0) {
                    break;
                }
                buffer.write(barr, 0, r);
            }
            bytes = buffer.toByteArray();
        } finally {
            in.close();
        }
        //All chars in encoded are guaranteed to be 7-bit ASCII
        byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(bytes);
        return new String(encoded, "ASCII");
    }

    private File createXML() throws IOException, ParseException {
        UserData userData = new UserData(guid, data);
        userXML = userData.createUserDataXML();
        File file = new File("req_" + guid + ".xml");
        if (file.createNewFile())
            System.out.println("req.xml created");
        else
            System.out.println("req.xml doesn.t created");
        PrintWriter pw = new PrintWriter(file, "UTF-8");
        System.out.println("USER: " + userXML.asXML());
        pw.print(userXML.asXML());
        pw.close();
        return file;
    }

    private File createSIG() throws Exception {
        Crypto cr = new Crypto();
        return cr.PKCS7sign("req_" + guid + ".xml", guid);
    }


}
