package useJaxB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class XmlCreate {

    public void create() {
        try {

            DataModel dataModel = new DataModel();
            List<Product> products = dataModel.result();
            Products obj = new Products();
            obj.setList(products);

            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File file = new File("product.xml");
            marshaller.marshal(obj, file);

        }catch (Exception ex) {
            System.err.println("Xml Create Error: " + ex);
        }
    }

}
