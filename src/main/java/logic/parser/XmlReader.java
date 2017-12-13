package logic.parser;

import logic.AntOptimization.AntColony;
import logic.AntOptimization.ParameterAntOptimization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlReader {

    public ParameterAntOptimization parameterParse(String fileName) {
        try {
            File file = new File(fileName + "_parameter.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(ParameterAntOptimization.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (ParameterAntOptimization) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public AntColony colonyParse(String fileName) {
        try {
            File file = new File(fileName + "_colony.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(AntColony.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (AntColony) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }


}
