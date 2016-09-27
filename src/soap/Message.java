package soap;

import org.dom4j.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Olcha on 01.05.2016.
 */
public class Message {
    public Message(){

    }

    public Element createMessage(Element body, String type, UUID guid){
        Element message = body.addElement("smev:Message");
        Element sender = message.addElement("smev:Sender");
        Element sCode = sender.addElement("smev:Code")
                .addText("FMS001001");
        Element sName = sender.addElement("smev:Name")
                .addText("ФМС");
        Element recipient = message.addElement("smev:Recipient");
        Element rCode = recipient.addElement("smev:Code")
                .addText("RRTR01001");
        Element rName = recipient.addElement("smev:Name")
                .addText("Росреестр");
        Element originator = message.addElement("smev:Originator");
        Element oCode = originator.addElement("smev:Code")
                .addText("FMS001001");
        Element oName = originator.addElement("smev:Name")
                .addText("ФМС");
        Element typeCode = message.addElement("smev:TypeCode")
                .addText("GSRV");
        if (type.equals("request")) {
            Element status = message.addElement("smev:Status")
                    .addText("REQUEST");
        }else {
            Element status = message.addElement("smev:Status")
                    .addText("PING");
        }
        Element date = message.addElement("smev:Date")
                .addText("2016-06-09T10:36:54.928+07:00");
        Element exchangeType = message.addElement("smev:ExchangeType")
                .addText("2");
        if (type.equals("request")) {
            Element serviceCode = message.addElement("smev:ServiceCode")
                    .addText("10000013628");
            Element caseNumber = message.addElement("smev:CaseNumber")
                    .addText("1/1");
        }else{
            Element originRequestIdRef = message.addElement("smev:OriginRequestIdRef")
                    //.addText(guid.toString());
                    .addText(guid.toString());
        }
        return message;
    }

    private String getCurrentDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.format(new Date());
    }
}
