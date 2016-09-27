package soap.request.Attachment;

import org.dom4j.Element;

/**
 * Created by Olcha on 10.06.2016.
 */
public class RequiredData {
    String objType, areaValue, areaUnit, regionNumber, cityName, cityType, streetName, streetType, l1Type, l1Value, l2Type, l2Value,
    l3Value, l3Type, apartmentType, apartmentValue, dopInfo;
    public RequiredData(String[] data)
    {
        this.objType = data[0];
        this.areaValue = data[1];
        this.areaUnit = data[2];
        this.regionNumber = data[3];
        this.cityName = data[4];
        this.cityType = data[5];
        this.streetName = data[6];
        this.streetType = data[7];
        this.l1Type = data[8];
        this.l1Value = data[9];
        this.l2Type = data[10];
        this.l2Value = data[11];
        this.l3Value = data[12];
        this.l3Type = data[13];
        this.apartmentType = data[14];
        this.apartmentValue = data[15];
        this.dopInfo = data[16];
    }

    public void createRequiredDataNode(Element request){
        Element requiredData = request.addElement("RequiredData");
        Element RequiredDataRealty = requiredData.addElement("RequiredDataRealty");
        Element ExtractRealty = RequiredDataRealty.addElement("ExtractRealty");
        Element Objects = ExtractRealty.addElement("Objects");
        Element Object = Objects.addElement("Object");
        Element ObjKind = Object.addElement("ObjKind");
        Element room = ObjKind.addElement("Room");
        Element objectType = room.addElement(objType)
                .addText("true");
        Element area = Object.addElement("Area");
        Element value = area.addElement("Value")
                .addText(areaValue);
        Element unit = area.addElement("Unit")
                .addText(areaUnit);
        Element location = Object.addElement("Location");
        Element region = location.addElement("Region")
                .addText(regionNumber);
        Element city = location.addElement("City")
                .addAttribute("Name", cityName)
                .addAttribute("Type", cityType);
        Element street = location.addElement("Street")
                .addAttribute("Name", streetName)
                .addAttribute("Type", streetType);
        Element level1 = location.addElement("Level1")
                .addAttribute("Type", l1Type)
                .addAttribute("Value", l1Value);
        Element level2 = location.addElement("Level2")
                .addAttribute("Type", l2Type)
                .addAttribute("Value", l2Value);
        Element level3 = location.addElement("Level3")
                .addAttribute("Type", l3Type)
                .addAttribute("Value", l3Value);
        Element apartment = location.addElement("Apartment")
                .addAttribute("Type", apartmentType)
                .addAttribute("Value", apartmentValue);
        Element other = location.addElement("DopInfo")
                .addText(dopInfo);
    }
}
