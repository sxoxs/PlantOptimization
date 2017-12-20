package logic.parser;

import logic.antOptimization.ParameterAntOptimization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlWriter {

    public void write (ParameterAntOptimization param, String fileName) {

        try {
            fileName = fileName.trim().toLowerCase();

            File file = new File(fileName + "_parameter.xml");
            JAXBContext jaxbContext= JAXBContext.newInstance(ParameterAntOptimization.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(param, file);
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
