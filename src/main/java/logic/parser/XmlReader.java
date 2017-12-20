package logic.parser;

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

}
