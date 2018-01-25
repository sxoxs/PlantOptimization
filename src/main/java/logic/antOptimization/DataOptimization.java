package logic.antOptimization;

import java.util.ArrayList;

public class DataOptimization {

    private int countColony;
    private int countAnts;
    private double lengthOptimaWay;
    private int maxCountEra;
    private double degreeInfluencePheromone;
    private double degreeInfluenceDistance;
    private double evaporationPheromone;
    private int[] optimaWay;
    private ArrayList<Double> lengthOptimaWayList = new ArrayList<>();
    private ArrayList<int[]> optimaWayList = new ArrayList<>();
    private long timeOptimization;

    DataOptimization(ParameterAntOptimization param, AntColony ac) {
        this.countColony = ac.getCountColony();
        this.countAnts = param.getCountAnt();
        this.lengthOptimaWay = 0;
        this.maxCountEra = param.getMaxCountEra() ;
        this.degreeInfluencePheromone =  param.getDegreeInfluencePheromone() ;
        this.degreeInfluenceDistance =  param.getDegreeInfluenceDistance() ;
        this.evaporationPheromone = param.getEvaporationPheromone() ;
        this.optimaWay = new int[countColony];
    }

    public ArrayList<Double> getLengthOptimaWayList() {
        return lengthOptimaWayList;
    }

    public ArrayList<int[]> getOptimaWayList() {
        return optimaWayList;
    }

    public void addLengthOptimaWay (Double length) {
        this.lengthOptimaWayList.add(length);
    }

    public void addOptimaWay (int[] way) {
        int[] newWay = new int[way.length];

        for (int i = 0; i < way.length; i++){
            newWay[i] = way[i];
        }
        this.optimaWayList.add(newWay);
    }

    public int getCountColony() {
        return countColony;
    }

    public int getCountAnts() {
        return countAnts;
    }

    public double getLengthOptimaWay() {
        return lengthOptimaWay;
    }

    public int getMaxCountEra() {
        return maxCountEra;
    }

    public double getDegreeInfluencePheromone() {
        return degreeInfluencePheromone;
    }

    public double getDegreeInfluenceDistance() {
        return degreeInfluenceDistance;
    }

    public double getEvaporationPheromone() {
        return evaporationPheromone;
    }

    public int[] getOptimaWay() {
        return optimaWay;
    }

    public long getTimeOptimization() {
        return timeOptimization;
    }

    public void setTimeOptimization(long timeOptimization) {
        this.timeOptimization = timeOptimization;
    }

    public void setOptimaWay(int[] optimaWay) {
        this.optimaWay = optimaWay;
    }

    public void setLengthOptimaWay(double lengthOptimaWay) {
        this.lengthOptimaWay = lengthOptimaWay;
    }

    public void setLengthOptimaWayList(ArrayList<Double> lengthOptimaWayList) {
        this.lengthOptimaWayList = lengthOptimaWayList;
    }

    public void setOptimaWayList(ArrayList<int[]> optimaWayList) {
        this.optimaWayList = optimaWayList;
    }
}
