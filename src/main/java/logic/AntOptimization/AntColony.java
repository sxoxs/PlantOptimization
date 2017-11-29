package logic.AntOptimization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AntColony {
    private static int countColony;
    private static int countAntsInOneColony;
    private static double[][] distanceBetweenColony;

    public static  void createAntColony() throws IOException {
        assingValueVariableColonyFromConsole();
        setDistanceBetweenColony();
    }

    public static  void createAntColony(String fileName) throws IOException {
        fileName += ".xls";
        countColony = (int) WriteIntoExcel.ReadFromExcell.ReadValue(fileName, "Параметры алгоритма", 1, 1);
        countAntsInOneColony = (int) WriteIntoExcel.ReadFromExcell.ReadValue(fileName, "Параметры алгоритма", 2, 1);
        distanceBetweenColony = new double[countColony][countColony];
        distanceBetweenColony = WriteIntoExcel.ReadFromExcell.LoadDoubleArray(countColony, countColony, fileName);
    }

    public static double[][] getDistanceBetweenColony() {
        return distanceBetweenColony;
    }

    public static void setDistanceBetweenColony(double[][] distanceBetweenColony) {
        AntColony.distanceBetweenColony = distanceBetweenColony;
    }

    public static int getCountColony() {
        return countColony;
    }

    public static void setCountColony(int countColony) {
        countColony = countColony;
    }

    public static int getCountAntsInOneColony() {
        return countAntsInOneColony;
    }

    public static void setCountAntsInOneColony(int countAntsInOneColony) {
        countAntsInOneColony = countAntsInOneColony;
    }

    private static void assingValueVariableColonyFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество муравейников");
        countColony = Integer.parseInt(br.readLine().trim());
        System.out.println("Введите количество муравьёв в каждом муравейнике");
        countAntsInOneColony = Integer.parseInt(br.readLine().trim());
    }

    private static void setDistanceBetweenColony() throws IOException {
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

    private static void setCustomDistanceBetweenColony() throws IOException  {
        System.out.println("В ручном вводе");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        distanceBetweenColony = new double[countColony][countColony];
        for (int i = 0; i < countColony; i++) {
            for (int j = 0; j < countColony; j++) {
                if (i!=j) {
                    {
                        System.out.println("Введите расстояние между " + (i+1) + "-м и " + (j+1) + "-м муравейниками");
                        distanceBetweenColony[i][j] = Double.parseDouble(br.readLine().trim());
                    }
                }
                else {
                    distanceBetweenColony[i][j] = 0;
                }
            }
        }
    }

    private static void setAutoDistanceBetweenColony() {
        distanceBetweenColony = new double[countColony][countColony];
        for (int i = 0; i < countColony; i++) {
            for (int j = 0; j < countColony; j++) {
                if (i!=j) {
                    distanceBetweenColony[i][j] = 1 +  Math.random() * 100;          // AntMath.roundTo2Decimal(AntMath.randomDouble(99.0));
                }
                else {
                    distanceBetweenColony[i][j] = 0;
                }
            }
        }
    }

}
