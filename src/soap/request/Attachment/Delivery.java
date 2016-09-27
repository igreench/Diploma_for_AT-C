package soap.request.Attachment;

import org.dom4j.Element;

/**
 * Created by Olcha on 10.06.2016.
 */
public class Delivery {
    public Delivery() {
    }
    public void createDeliveryNode(Element request){
        Element delivery = request.addElement("Delivery");
        Element webService = delivery.addElement("WebService")
                .addText("true");
    }
}
