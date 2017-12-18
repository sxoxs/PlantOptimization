package logic.antOptimization;

import java.util.ArrayList;


public class Ant {
    private static ArrayList<Ant> antList = new ArrayList<>();
    private int[] antWay;  //Set
    private double lengthWay;

    Ant(ParameterAntOptimization param, AntColony ac) {
        new Ant( (int) (Math.random() * (ac.getCountColony() + 1)), param, ac );
    }

    Ant(int firstColony, ParameterAntOptimization param, AntColony ac) {
        antWay = new int[ac.getCountColony()];
        for(int j = 0; j< antWay.length; antWay[j] = j++) ;
        swapValueInArrayWay(antWay.length-1, firstColony);
        changeWay(param, ac);
        changeLengthWay(ac);
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

    public void changeWay(ParameterAntOptimization algParam, AntColony ac) {
        double probabilityTransitionAnt;
        int indexNextColony;
        boolean condition;
        double summProbabiliry;
        swapValueInArrayWay(0, antWay.length-1);
        int i = 1;

        do {
            double summArrayProbabilityTransitionAnt = 0;
            for (int j = i; j < antWay.length; j++){
                summArrayProbabilityTransitionAnt +=  algParam.getProbabilitiTransitionInColony()[antWay[i-1]][antWay[j]];
            }

            probabilityTransitionAnt =  Math.random() * summArrayProbabilityTransitionAnt;
            condition = false;
            summProbabiliry = 0;
            indexNextColony = i-1;

            do {
                summProbabiliry +=  algParam.getProbabilitiTransitionInColony()[antWay[i-1]][antWay[++indexNextColony]];
                if ((probabilityTransitionAnt < summProbabiliry)|(indexNextColony == antWay.length-1)) {
                    condition = true;
                }
            } while (!condition);
            swapValueInArrayWay(i, indexNextColony);
        } while (++i < antWay.length-1);

        changeLengthWay(ac);
    }

    private void swapValueInArrayWay(int firstIndex, int secondIndex) {
        int antWayBuffer = antWay[firstIndex];
        antWay[firstIndex] = antWay[secondIndex];
        antWay[secondIndex] = antWayBuffer;
    }

    private void changeLengthWay(AntColony ac){
        lengthWay = 0;
        for (int i = 1; i < antWay.length; i++) {
            lengthWay += ac.getDistanceBetweenColony()[antWay[i-1]][antWay[i]];
        }
        lengthWay += 0;
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
