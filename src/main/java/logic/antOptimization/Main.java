package logic.antOptimization;


import java.io.IOException;



public class Main {

    public static void main(String[] args) throws IOException {

        System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        Menu menu = new Menu();
        menu.runProgramm();
    }
}


