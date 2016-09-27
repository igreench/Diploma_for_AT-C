package soap.request.Attachment;

import org.dom4j.Element;

/**
 * Created by Olcha on 10.06.2016.
 */
public class MunicipalService {

    public void createMunicipalService(Element request){
        Element municipalService = request.addElement("MunicipalService");
    }
}
