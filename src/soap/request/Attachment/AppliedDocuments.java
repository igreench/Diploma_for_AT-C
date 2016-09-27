package soap.request.Attachment;

import org.dom4j.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Olcha on 10.06.2016.
 */
public class AppliedDocuments {
    public void createAppliedDocumentsNode(Element request) throws ParseException {
        Element appliedDocuments = request.addElement("Applied_Documents");
        Element Applied_Document = appliedDocuments.addElement("Applied_Document");
        Element Code_Document = Applied_Document.addElement("Code_Document")
                .addText("558102100000");
        Element Name = Applied_Document.addElement("Name")
                .addText("Запрос о предоставлении сведений, содержащихся в Едином государственном реестре прав на недвижимое имущество и сделок с ним");
        Element Number = Applied_Document.addElement("Number")
                .addText("26-0-1-21/4001/2011-166");
        Element Date = Applied_Document.addElement("Date")
                .addText("2012-07-24");
        Element Images = Applied_Document.addElement("Images");
        Element image = Images.addElement("Image")
                .addAttribute("Name", "");
        Element Quantity = Applied_Document.addElement("Quantity");
        Element Original = Quantity.addElement("Original")
                .addAttribute("Quantity", "1")
                .addAttribute("Quantity_Sheet", "1");
    }

}
