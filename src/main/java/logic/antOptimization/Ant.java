package logic.antOptimization;

import logic.factory.FactoryParameter;
import logic.factory.Schedule;

import java.util.ArrayList;


public class Ant {
    private static ArrayList<Ant> antList = new ArrayList<>();
    private int[] antWay;  //Set
    private double lengthWay;


    Ant(int firstColony, ParameterAntOptimization param, AntColony ac, FactoryParameter fParam) {
        antWay = new int[ac.getCountColony()];
        for(int j = 0; j < antWay.length; j++) {
            antWay[j] = j;
        }
        swapValueInArrayWay(antWay.length-1, firstColony);
        Schedule sch = new Schedule();
        changeWay(param, fParam, sch);
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

    public void changeWay(ParameterAntOptimization algParam, FactoryParameter fParam, Schedule schedule) {
        double probabilityTransitionAnt;
        int indexNextColony;
        double summProbabiliry;
        int indexNowColony = 0;
        swapValueInArrayWay(indexNowColony, antWay.length-1);

        for (; indexNowColony < antWay.length-2; indexNowColony++) {
            double summArrayProbabilityTransitionAnt = 0;

            for (int j = indexNowColony + 1; j < antWay.length; j++){
                summArrayProbabilityTransitionAnt +=  algParam.getProbabilitiTransitionInColony()[antWay[indexNowColony]][antWay[j]];
            }

            probabilityTransitionAnt =  Math.random() * summArrayProbabilityTransitionAnt;
            summProbabiliry = 0;
            indexNextColony = indexNowColony+1;

            for ( ; indexNextColony < antWay.length-1; indexNextColony++) {

                summProbabiliry +=  algParam.getProbabilitiTransitionInColony()[antWay[indexNowColony]][antWay[indexNextColony]];

                if (probabilityTransitionAnt < summProbabiliry) {
                    break;
                }
            }
            indexNowColony++;
            swapValueInArrayWay(indexNowColony, indexNextColony);

        }

        changeLengthWay(fParam, schedule);
    }

    private void swapValueInArrayWay(int firstIndex, int secondIndex) {
        int antWayBuffer = antWay[firstIndex];
        antWay[firstIndex] = antWay[secondIndex];
        antWay[secondIndex] = antWayBuffer;
    }

    private void changeLengthWay(FactoryParameter fp, Schedule schedule){
        fp.setSequenceProduct(this.antWay);
        this.lengthWay = schedule.calculationAllTime(fp);
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
