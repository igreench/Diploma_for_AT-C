package soap.request.Attachment;

import org.dom4j.Element;

/**
 * Created by Olcha on 10.06.2016.
 */
public class Declarant {
    String declarantKind, governanceName, governanceCode, governanceEMail, governancePhone,secondName, firstName, patronymic,
            codeDocument, documentSeries, documentNumber, documentDate, issueOrgan, locationType, postalCode, regionNumber,
            cityName ,cityType, streetName, streetType, l1Type, l1Value,l2Type, l2Value, l3Type, l3Value, apartmentType,
            apartmentValue, other, note, email, phoneNumber, contactInfo, snils, agentKindNumber;
    public Declarant(String[] data)
    {
        this.declarantKind = data[0];
        this.governanceName = data[1];
        this.governanceCode = data[2];
        this.governanceEMail = data[3];
        this.governancePhone = data[4];
        this.secondName = data[5];
        this.firstName = data[6];
        this.patronymic = data[7];
        this.codeDocument = data[8];
        this.documentSeries = data[9];
        this.documentNumber = data[10];
        this.documentDate = data[11];
        this.issueOrgan = data[12];
        this.locationType = data[13];
        this.postalCode = data[14];
        this.regionNumber = data[15];
        this.cityName = data[16];
        this.cityType = data[17];
        this.streetName = data[18];
        this.streetType = data[19];
        this.l1Type = data[20];
        this.l1Value = data[21];
        this.l2Type = data[22];
        this.l2Value = data[23];
        this.l3Type = data[24];
        this.l3Value = data[25];
        this.apartmentType = data[26];
        this.apartmentValue = data[27];
        this.other = data[28];
        this.note = data[29];
        this.email = data[30];
        this.phoneNumber = data[31];
        this.contactInfo = data[32];
        this.snils = data[33];
        this.agentKindNumber = data[34];

    }
    public void createDeclarantNode(Element request){
        Element declarant = request.addElement("Declarant")
                .addAttribute("declarant_kind", "357007000000")
                .addAttribute("signatured", "true");
        Element Governance = declarant.addElement("Governance");
        Element name = Governance.addElement("Name")
                .addText(governanceName);
        Element Governance_Code = Governance.addElement("Governance_Code")
                .addText(governanceCode);
        Element eMail = Governance.addElement("E-mail")
                .addText(governanceEMail);
        Element phone = Governance.addElement("Phone")
                .addText(governancePhone);
        Element agent = Governance.addElement("Agent");
        Element fio = agent.addElement("FIO");
        Element surname = fio.addElement("Surname")
                .addText(secondName);
        Element first = fio.addElement("First")
                .addText(firstName);
        Element Patronymic = fio.addElement("Patronymic")
                .addText(patronymic);
        Element document = agent.addElement("Document");
        Element Code_Document = document.addElement("Code_Document")
                .addText(codeDocument);
        Element Series = document.addElement("Series")
                .addText(documentSeries);
        Element Number = document.addElement("Number")
                .addText(documentNumber);
        Element Date = document.addElement("Date")
                .addText(documentDate);
        Element IssueOrgan = document.addElement("IssueOrgan")
                .addText(issueOrgan);
        Element Location = agent.addElement(locationType);
        Element Postal_Code = Location.addElement("Postal_Code")
                .addText(postalCode);
        Element Region = Location.addElement("Region")
                .addText(regionNumber);
        Element City = Location.addElement("City")
                .addAttribute("Name", cityName)
                .addAttribute("Type", cityType);
        Element Street = Location.addElement("Street")
                .addAttribute("Name", streetName)
                .addAttribute("Type", streetType);
        Element Level1 = Location.addElement("Level1")
                .addAttribute("Type", l1Type)
                .addAttribute("Value", l1Value);
        Element Level2 = Location.addElement("Level2")
                .addAttribute("Type", l2Type)
                .addAttribute("Value", l2Value);
        Element Level3 = Location.addElement("Level3")
                .addAttribute("Type", l3Type)
                .addAttribute("Value", l3Value);
        Element Apartment = Location.addElement("Apartment")
                .addAttribute("Type", apartmentType)
                .addAttribute("Value", apartmentValue);
        Element Other = Location.addElement("Other")
                .addText(other);
        Element Note = Location.addElement("Note")
                .addText(note);
        Element eMail2 = agent.addElement("E-mail")
                .addText(email);
        Element Phone = agent.addElement("Phone")
                .addText(phoneNumber);
        Element Contact_Info = agent.addElement("Contact_Info")
                .addText(contactInfo);
        Element SNILS = agent.addElement("SNILS")
                .addText(snils);
        Element agent_kind = agent.addElement("agent_kind")
                .addText(agentKindNumber);
    }
}
