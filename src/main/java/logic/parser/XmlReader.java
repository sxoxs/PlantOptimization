package logic.parser;

import logic.antOptimization.AntColony;
import logic.antOptimization.ParameterAntOptimization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlReader {

    public ParameterAntOptimization parameterParse(String fileName) {
        try {
            fileName = fileName.trim().toLowerCase() + "_parameter.xml";
            File file = new File(fileName);
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
            fileName = fileName.trim().toLowerCase() + "_colony.xml";
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(AntColony.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (AntColony) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }


}
