package logic.antOptimization;


import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        Menu menu = new Menu();
        menu.runProgramm();

//
//        FactoryParameterCreater fpc = new FactoryParameterCreater();
//        FactoryParameter fp = new FactoryParameter();
//        fp = fpc.changer(fp);
//        System.out.println("end loading");
//        int[] sc = {0, 2, 1, 3 , 4};
//        fp.setSequenceProduct(sc);
//        Schedule schedule = new Schedule();
//        double time =  schedule.calculationAllTime(fp);
//
//        System.out.println(time);





    }
}


