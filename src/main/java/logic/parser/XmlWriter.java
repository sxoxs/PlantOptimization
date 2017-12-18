package logic.parser;

import logic.antOptimization.AntColony;
import logic.antOptimization.ParameterAntOptimization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlWriter {

    public void write (ParameterAntOptimization param, AntColony colony, String fileName) {

        try {
            fileName = fileName.trim().toLowerCase();

            File file = new File(fileName + "_parameter.xml");
            JAXBContext jaxbContext= JAXBContext.newInstance(ParameterAntOptimization.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            // output pretty printed
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(param, file);

            file = new File(fileName + "_colony.xml");

            jaxbContext = JAXBContext.newInstance(AntColony.class);
            marshaller = jaxbContext.createMarshaller();

            // output pretty printed
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(colony, file);

        }
        catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
