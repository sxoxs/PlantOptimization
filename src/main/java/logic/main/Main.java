package logic.main;


import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        System.out.println("Добро пожаловать в муравьиный алгоритм");

        Menu menu = new Menu();
        menu.showMenu();
    }
}


