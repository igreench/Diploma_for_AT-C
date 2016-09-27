package soap.request.Attachment;


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.ParseException;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Olcha on 15.05.2016.
 */
public class UserData {
    UUID guid;
    String[] data;
    public UserData(UUID guid, String[] data){
        this.guid = guid;
        this.data = data;
    }

    public Element createUserDataXML() throws ParseException {
        Document xml = DocumentHelper.createDocument();
        Element root = xml.addElement("RequestGRP")
                .addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            Element eDocument = root.addElement("eDocument")
                    .addAttribute("Version", "1.16")
                    .addAttribute("GUID", guid.toString());
            Element request = root.addElement("Request");

        String[] temp = null;
        System.arraycopy(data, 0, temp, 0, 12);
        RequiredData rd = new RequiredData(temp);
        rd.createRequiredDataNode(request);

        temp = null;
        System.arraycopy(data, 12, temp, 0, 33);
        Declarant dc = new Declarant(temp);
        dc.createDeclarantNode(request);

        temp = null;
        System.arraycopy(data, 45, temp, 0, 5);
        Payment p = new Payment();
        p.createPaymentNode(request);
        Delivery d = new Delivery();
        d.createDeliveryNode(request);

        temp = null;
        System.arraycopy(data, 50, temp, 0, 3);
        AppliedDocuments ad = new AppliedDocuments();
        ad.createAppliedDocumentsNode(request);
        MunicipalService m = new MunicipalService();
        m.createMunicipalService(request);

        return root;
    }
}
