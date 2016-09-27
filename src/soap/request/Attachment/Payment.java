package soap.request.Attachment;

import org.dom4j.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Olcha on 10.06.2016.
 */
public class Payment {

    public void createPaymentNode(Element request) throws ParseException {
        Element payment = request.addElement("Payment");
        Element ReasonFree_Documents = payment.addElement("ReasonFree_Documents");
        Element ReasonFree_Document = ReasonFree_Documents.addElement("ReasonFree_Document");
        Element Code_Document = ReasonFree_Document.addElement("Code_Document")
                .addText("555005000000");
        Element Name = ReasonFree_Document.addElement("Name")
                .addText("Документ, подтверждающий право заявителя на безвозмездное получение сведений");
        Element Number = ReasonFree_Document.addElement("Number")
                .addText("2012-07-24");
        Element Date = ReasonFree_Document.addElement("Date")
                .addText(getCurrentDate());
        Element Quantity = ReasonFree_Document.addElement("Quantity");
        Element original = Quantity.addElement("Original")
                .addAttribute("Quantity", "1")
                .addAttribute("Quantity_Sheet", "1");
    }

    private String getCurrentDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        return sdf.format(new Date());
    }
}
