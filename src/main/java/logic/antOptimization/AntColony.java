package logic.antOptimization;

import logic.factory.FactoryParameter;
import logic.factory.FactoryParameterCreater;
import logic.factory.Schedule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@XmlRootElement(name="parameterAntOptimization")
@XmlAccessorType(XmlAccessType.FIELD)
public class AntColony {

    @XmlElement(name = "countColony")
    private int countColony;
    @XmlElement(name = "countAntsInOneColony")
    private int countAntsInOneColony;
    @XmlElement(name = "distanceBetweenColony")
    private double[][] distanceBetweenColony;

    public void createAntColony() throws IOException {
        assingValueVariableColonyFromConsole();
//        setDistanceBetweenColony();
        setDistansFromFactory();
    }

    public int getCountColony() {
        return countColony;
    }

    public void setCountColony(int countColony) {
        this.countColony = countColony;
    }

    public int getCountAntsInOneColony() {
        return countAntsInOneColony;
    }

    public void setCountAntsInOneColony(int countAntsInOneColony) {
        this.countAntsInOneColony = countAntsInOneColony;
    }

    public double[][] getDistanceBetweenColony() {
        return distanceBetweenColony;
    }

    public void setDistanceBetweenColony(double[][] distanceBetweenColony) {
        this.distanceBetweenColony = distanceBetweenColony;
    }

    private void assingValueVariableColonyFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество муравейников");
        this.countColony = Integer.parseInt(br.readLine().trim());
        System.out.println("Введите количество муравьёв в каждом муравейнике");
        countAntsInOneColony = Integer.parseInt(br.readLine().trim());
    }

    private void setDistanceBetweenColony() throws IOException {
        System.out.println("Задать расстоянмя автоматически? Y/N ?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        switch (br.readLine().trim().toLowerCase()) {
            case "y":
            {
                setAutoDistanceBetweenColony();
                break;
            }
            case "n":
            {
                setCustomDistanceBetweenColony();
                break;
            }
        }

    }

    private void setCustomDistanceBetweenColony() throws IOException  {
        System.out.println("В ручном вводе");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        this.distanceBetweenColony = new double[this.countColony][this.countColony];
        for (int i = 0; i < this.countColony; i++) {
            for (int j = 0; j < this.countColony; j++) {
                if (i!=j) {
                    {
                        System.out.println("Введите расстояние между " + (i+1) + "-м и " + (j+1) + "-м муравейниками");
                        this.distanceBetweenColony[i][j] = Double.parseDouble(br.readLine().trim());
                    }
                }
                else {
                    this.distanceBetweenColony[i][j] = 0;
                }
            }
        }
    }

    private void setAutoDistanceBetweenColony() {
        this.distanceBetweenColony = new double[this.countColony][this.countColony];
        for (int i = 0; i < countColony; i++) {
            for (int j = 0; j < countColony; j++) {
                if (i!=j) {
                    this.distanceBetweenColony[i][j] = 1 +  Math.random() * 100;          // AntMath.roundTo2Decimal(AntMath.randomDouble(99.0));
                }
                else {
                    this.distanceBetweenColony[i][j] = 0;
                }
            }
        }
    }

    private void setDistansFromFactory () throws IOException {
        FactoryParameterCreater fpc = new FactoryParameterCreater();
        FactoryParameter fp = new FactoryParameter();
        fp = fpc.changer(fp);
        Schedule schedule = new Schedule();

        this.distanceBetweenColony = new double[this.countColony][this.countColony];
        for (int i = 0; i < countColony; i++) {
            for (int j = 0; j < countColony; j++) {
                if (i!=j) {
                    this.distanceBetweenColony[i][j] = schedule.wayTimeCalculation(fp, i, j);
                }
                else {
                    this.distanceBetweenColony[i][j] = 0;
                }
            }
        }
    }

}
