package logic.AntOptimization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ParameterAntOptimization {
    private static double averangDistant;
    private static double degreeInfluencePheromone;
    private static double degreeInfluenceDistance;
    private static double evaporationPheromone;
    private static double[][] arrayVisibilityColony;
    private static double[][] arrayAmountPheromoneOnWay;
    private static double[][] probabilitiTransitionInColony;
    private static int maxCountEra;

    public static void createParameterAntOptimization(String fileName) throws IOException {
        switch (fileName) {
            case "":{
                assingValueVariableFromConsole();
                break;
            }
            default:{
                assingValueVariableFromFile(fileName);
                break;
            }
        }
        assingLengthArray();
        averangDistant = (2 * AntMath.summArray(AntColony.getDistanceBetweenColony())) / (AntColony.getCountColony() * (AntColony.getCountColony() - 1));
        firstCalculationArray();
        changeProbabityTransitionInColony();
    }

    public static double getAverangDistant() {
        return averangDistant;
    }

    public static void setAverangDistant(double averangDistant) {
        ParameterAntOptimization.averangDistant = averangDistant;
    }

    public static double getDegreeInfluencePheromone() {
        return degreeInfluencePheromone;
    }

    public static void setDegreeInfluencePheromone(double degreeInfluencePheromone) {
        ParameterAntOptimization.degreeInfluencePheromone = degreeInfluencePheromone;
    }

    public static double getDegreeInfluenceDistance() {
        return degreeInfluenceDistance;
    }

    public static void setDegreeInfluenceDistance(double degreeInfluenceDistance) {
        ParameterAntOptimization.degreeInfluenceDistance = degreeInfluenceDistance;
    }

    public static double getEvaporationPheromone() {
        return evaporationPheromone;
    }

    public static void setEvaporationPheromone(double evaporationPheromone) {
        ParameterAntOptimization.evaporationPheromone = evaporationPheromone;
    }

    public static double[][] getArrayVisibilityColony() {
        return arrayVisibilityColony;
    }

    public static void setArrayVisibilityColony(double[][] arrayVisibilityColony) {
        ParameterAntOptimization.arrayVisibilityColony = arrayVisibilityColony;
    }

    public static double[][] getArrayAmountPheromoneOnWay() {
        return arrayAmountPheromoneOnWay;
    }

    public static void setArrayAmountPheromoneOnWay(double[][] arrayAmountPheromoneOnWay) {
        ParameterAntOptimization.arrayAmountPheromoneOnWay = arrayAmountPheromoneOnWay;
    }

    public static double[][] getProbabilitiTransitionInColony() {
        return probabilitiTransitionInColony;
    }

    public static void setProbabilitiTransitionInColony(double[][] probabilitiTransitionInColony) {
        ParameterAntOptimization.probabilitiTransitionInColony = probabilitiTransitionInColony;
    }

    public static int getMaxCountEra() {
        return maxCountEra;
    }

    public static void setMaxCountEra(int maxCountEra) {
        ParameterAntOptimization.maxCountEra = maxCountEra;
    }

    private static void assingLengthArray() {
        arrayVisibilityColony = new double[AntColony.getDistanceBetweenColony().length][];
        arrayAmountPheromoneOnWay = new double[AntColony.getDistanceBetweenColony().length][];
        probabilitiTransitionInColony = new double[AntColony.getDistanceBetweenColony().length][];
        for (int i = 0; i < AntColony.getDistanceBetweenColony().length; i++) {
            arrayVisibilityColony[i] = new double[AntColony.getDistanceBetweenColony()[i].length];
            arrayAmountPheromoneOnWay[i] = new double[AntColony.getDistanceBetweenColony()[i].length];
            probabilitiTransitionInColony[i] = new double[AntColony.getDistanceBetweenColony()[i].length];
        }
    }

    private static void firstCalculationArray(){
        for(int i = 0; i < AntColony.getDistanceBetweenColony().length; i++) {
            for (int j = 0; j < AntColony.getDistanceBetweenColony()[i].length; j++) {
                if (i!=j) {
                    arrayVisibilityColony[i][j] = averangDistant / AntColony.getDistanceBetweenColony()[i][j];
                    arrayAmountPheromoneOnWay[i][j] = 1;
                }
                else {
                    arrayVisibilityColony[i][j] = 0;
                    arrayAmountPheromoneOnWay[i][j] = 0;
                }
            }
        }
    }

    private static void assingValueVariableFromFile(String fileName) throws IOException {
        degreeInfluenceDistance = WriteIntoExcel.ReadFromExcell.ReadValue(fileName +".xls", "Параметры алгоритма", 3, 1);
        degreeInfluencePheromone = WriteIntoExcel.ReadFromExcell.ReadValue(fileName +".xls", "Параметры алгоритма", 4, 1);
        evaporationPheromone = WriteIntoExcel.ReadFromExcell.ReadValue(fileName +".xls", "Параметры алгоритма", 5, 1);
        maxCountEra = (int) WriteIntoExcel.ReadFromExcell.ReadValue(fileName +".xls", "Параметры алгоритма", 6, 1);
    }

    private static void assingValueVariableFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите коэфициент влияния феромона: ");
        degreeInfluencePheromone = Double.parseDouble(br.readLine().trim());
        System.out.println("Введите коэфициент влияния расстояния между муравейниками: ");
        degreeInfluenceDistance = Double.parseDouble(br.readLine().trim());
        System.out.println("Введите коэфициент учитывающий испарение феромона: ");
        evaporationPheromone = Double.parseDouble(br.readLine().trim());
        System.out.println("Ведите количество эпох работы алгоритма");
        maxCountEra = Integer.parseInt(br.readLine().trim());
    }

    public static void changePferomoneOnWay(){
        for(int i = 0; i < arrayAmountPheromoneOnWay.length; i++) {
            for (int j = 0; j < arrayAmountPheromoneOnWay[i].length; j++) {
                if (i!=j) {
                    arrayAmountPheromoneOnWay[i][j] =
                            (evaporationPheromone * arrayAmountPheromoneOnWay[i][j] + ParameterAntOptimization.getAverangDistant() *
                                    (AntColony.getCountColony()-1) * (1 - evaporationPheromone) * calculationSummReciprocalLengthWayGivenColony(i, j));
                }
            }
        }
    }

    public static void changeProbabityTransitionInColony() {
        for (int i = 0; i < probabilitiTransitionInColony.length; i++) {
            for (int j = 0; j < probabilitiTransitionInColony[i].length; j++) {

                probabilitiTransitionInColony[i][j] = (Math.pow(arrayAmountPheromoneOnWay[i][j], degreeInfluencePheromone)
                        * Math.pow(arrayVisibilityColony[i][j], degreeInfluenceDistance));
            }
        }
        double summProbabity = AntMath.summArray(probabilitiTransitionInColony);

    }

    private static double calculationSummReciprocalLengthWayGivenColony(int indexFirstColony, int indexSecondColony) {
        double summReciprocalLength = 0;

        for (Ant ant : Ant.getAntList()) {
            if (checkSequenceValuesInArray(ant.getAntWay(), indexFirstColony, indexSecondColony)) {
                summReciprocalLength += 1 / ant.getLengthWay();
            }
        }

        return summReciprocalLength;
    }

    private static boolean checkSequenceValuesInArray(int[] Array, int firstValue, int secondValue) {
        boolean Result = false;
        int i = 0;
        do {
            if((Array[i] == firstValue)&(Array[i+1] == secondValue)) {
                Result = true;
            }
        } while ((!Result)&(++i < Array.length-1));

        return Result;
    }

}
