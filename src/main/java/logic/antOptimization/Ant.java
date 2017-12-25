package logic.antOptimization;

import logic.factory.FactoryParameter;
import logic.factory.Schedule;

import java.util.ArrayList;


public class Ant {
    private static ArrayList<Ant> antList = new ArrayList<>();
    private int[] antWay;  //Set
    private double lengthWay;

    Ant(ParameterAntOptimization param, AntColony ac, FactoryParameter fParam) {
        new Ant( (int) (Math.random() * (ac.getCountColony() + 1)), param, ac, fParam);
    }

    Ant(int firstColony, ParameterAntOptimization param, AntColony ac, FactoryParameter fParam) {
        antWay = new int[ac.getCountColony()];
        for(int j = 0; j < antWay.length; j++) {
            antWay[j] = j;
        }
        swapValueInArrayWay(antWay.length-1, firstColony);
        changeWay(param, ac, fParam);
        changeLengthWay(fParam);
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

    public void changeWay(ParameterAntOptimization algParam, AntColony ac, FactoryParameter fParam) {
        double probabilityTransitionAnt;
        int indexNextColony;
        boolean condition;
        double summProbabiliry;
        swapValueInArrayWay(0, antWay.length-1);
        int indexNowColony = 1;

        do {
            double summArrayProbabilityTransitionAnt = 0;

            for (int j = indexNowColony + 1; j < antWay.length; j++){
                summArrayProbabilityTransitionAnt +=  algParam.getProbabilitiTransitionInColony()[antWay[indexNowColony]][antWay[j]];
            }

            probabilityTransitionAnt =  Math.random() ;
            condition = false;
            summProbabiliry = 0;

            for (indexNextColony = indexNowColony; !condition; ) {
                indexNextColony++;
                summProbabiliry +=  algParam.getProbabilitiTransitionInColony()[antWay[indexNowColony]][antWay[indexNextColony]]/summArrayProbabilityTransitionAnt;

                if (probabilityTransitionAnt < summProbabiliry) {
                    condition = true;
                }
            }

            swapValueInArrayWay(indexNowColony, indexNextColony);
            indexNowColony++;

        } while (indexNowColony < antWay.length-2);

        changeLengthWay(fParam);
    }

    private void swapValueInArrayWay(int firstIndex, int secondIndex) {
        int antWayBuffer = antWay[firstIndex];
        antWay[firstIndex] = antWay[secondIndex];
        antWay[secondIndex] = antWayBuffer;
    }

    private void changeLengthWay(FactoryParameter fp){
        Schedule schedule = new Schedule();
        fp.setSequenceProduct(this.antWay);

        this.lengthWay = schedule.calculationAllTime(fp);
        if (this.lengthWay == 142) {
            int i = 0;
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
