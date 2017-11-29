package logic.AntOptimization;

import java.util.ArrayList;


public class Ant {
    private static ArrayList<Ant> antList = new ArrayList<>();
    private int[] antWay;
    private double lengthWay;

    Ant() {
        new Ant( (int) Math.random() * (AntColony.getCountColony() + 1) );
    }

    Ant(int firstColony) {
        antWay = new int[AntColony.getCountColony()];
        for(int j = 0; j< antWay.length; antWay[j] = j++) ;
        swapValueInArrayWay(antWay.length-1, firstColony);
        changeWay();
        changeLengthWay();
    }

    public static void addAnt (Ant ant) {
        antList.add(ant);
    }

    public static ArrayList<Ant> getAntList() {
        return antList;
    }

    public int[] getAntWay() {
        return antWay;
    }

    public double getLengthWay() {
        return lengthWay;
    }

    public void changeWay() {
        double probabilityTransitionAnt;
        int indexNextColony;
        boolean condition;
        double summProbabiliry;
        swapValueInArrayWay(0, antWay.length-1);
        int i = 1;

        do {
            double summArrayProbabilityTransitionAnt = 0;
            for (int j = i; j < antWay.length; j++){
                summArrayProbabilityTransitionAnt +=  ParameterAntOptimization.getProbabilitiTransitionInColony()[antWay[i-1]][antWay[j]];
            }

            probabilityTransitionAnt =  Math.random() * summArrayProbabilityTransitionAnt;
            condition = false;
            summProbabiliry = 0;
            indexNextColony = i-1;

            do {
                summProbabiliry +=  ParameterAntOptimization.getProbabilitiTransitionInColony()[antWay[i-1]][antWay[++indexNextColony]];
                if ((probabilityTransitionAnt < summProbabiliry)|(indexNextColony == antWay.length-1)) {
                    condition = true;
                }
            } while (!condition);
            swapValueInArrayWay(i, indexNextColony);
        } while (++i < antWay.length-1);

        changeLengthWay();
    }

    private void swapValueInArrayWay(int FirstIndex, int SecondIndex) {
        int antWayBuffer = antWay[FirstIndex];
        antWay[FirstIndex] = antWay[SecondIndex];
        antWay[SecondIndex] = antWayBuffer;
    }

    private void changeLengthWay(){
        lengthWay = 0;
        for (int i = 1; i < antWay.length; i++) {
            lengthWay += AntColony.getDistanceBetweenColony()[antWay[i-1]][antWay[i]];
        }
    }

    public static int getIngexMinimalLengthWay(ArrayList<Ant> ants) {
        double min = ants.get(0).getLengthWay();
        int minIndex = 0;

        for (Ant ant : ants){
            if (ant.getLengthWay() < min) {
                min = ant.getLengthWay();
                minIndex = ants.indexOf(ant);
            }
        }

        return minIndex;
    }

}
