package logic.antOptimization;

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

public class ParameterAntOptimization {
    @XmlElement(name = "averangDistant")
    private double averangDistant;
    @XmlElement(name = "degreeInfluencePheromone")
    private double degreeInfluencePheromone;
    @XmlElement(name = "degreeInfluenceDistance")
    private double degreeInfluenceDistance;
    @XmlElement(name = "evaporationPheromone")
    private double evaporationPheromone;
    @XmlElement(name = "arrayVisibilityColony")
    private double[][] arrayVisibilityColony;
    @XmlElement(name = "arrayAmountPheromoneOnWay")
    private double[][] arrayAmountPheromoneOnWay;
    @XmlElement(name = "probabilitiTransitionInColony")
    private double[][] probabilitiTransitionInColony;
    @XmlElement(name = "maxCountEra")
    private int maxCountEra;
    @XmlElement(name = "countAnt")
    private int countAnt;


    ParameterAntOptimization(){

    }

    public ParameterAntOptimization(AntColony ac) throws IOException {
        assingValueVariableFromConsole();
        assingLengthArray(ac);
        averangDistant = (2 * AntMath.summArray(ac.getDistanceBetweenColony())) / (ac.getCountColony() * (ac.getCountColony() - 1));
        firstCalculationArray(ac);
        changeProbabityTransitionInColony();
    }

    public int getCountAnt() {
        return countAnt;
    }

    public void setCountAnt(int countAnt) {
        this.countAnt = countAnt;
    }

    public double getAverangDistant() {
        return averangDistant;
    }

    public void setAverangDistant(double averangDistant) {
        this.averangDistant = averangDistant;
    }

    public double getDegreeInfluencePheromone() {
        return degreeInfluencePheromone;
    }

    public void setDegreeInfluencePheromone(double degreeInfluencePheromone) {
        this.degreeInfluencePheromone = degreeInfluencePheromone;
    }

    public double getDegreeInfluenceDistance() {
        return degreeInfluenceDistance;
    }

    public void setDegreeInfluenceDistance(double degreeInfluenceDistance) {
        this.degreeInfluenceDistance = degreeInfluenceDistance;
    }

    public double getEvaporationPheromone() {
        return evaporationPheromone;
    }

    public void setEvaporationPheromone(double evaporationPheromone) {
        this.evaporationPheromone = evaporationPheromone;
    }

    public double[][] getArrayVisibilityColony() {
        return arrayVisibilityColony;
    }

    public void setArrayVisibilityColony(double[][] arrayVisibilityColony) {
        this.arrayVisibilityColony = arrayVisibilityColony;
    }

    public double[][] getArrayAmountPheromoneOnWay() {
        return arrayAmountPheromoneOnWay;
    }

    public void setArrayAmountPheromoneOnWay(double[][] arrayAmountPheromoneOnWay) {
        this.arrayAmountPheromoneOnWay = arrayAmountPheromoneOnWay;
    }

    public double[][] getProbabilitiTransitionInColony() {
        return probabilitiTransitionInColony;
    }

    public void setProbabilitiTransitionInColony(double[][] probabilitiTransitionInColony) {
        this.probabilitiTransitionInColony = probabilitiTransitionInColony;
    }

    public int getMaxCountEra() {
        return maxCountEra;
    }

    public void setMaxCountEra(int maxCountEra) {
        this.maxCountEra = maxCountEra;
    }

    private void assingLengthArray(AntColony ac) {
        this.arrayVisibilityColony = new double[ac.getDistanceBetweenColony().length][];
        this.arrayAmountPheromoneOnWay = new double[ac.getDistanceBetweenColony().length][];
        this.probabilitiTransitionInColony = new double[ac.getDistanceBetweenColony().length][];
        for (int i = 0; i < ac.getDistanceBetweenColony().length; i++) {
            this.arrayVisibilityColony[i] = new double[ac.getDistanceBetweenColony()[i].length];
            this.arrayAmountPheromoneOnWay[i] = new double[ac.getDistanceBetweenColony()[i].length];
            this.probabilitiTransitionInColony[i] = new double[ac.getDistanceBetweenColony()[i].length];
        }
    }

    private void firstCalculationArray(AntColony ac){
        for(int i = 0; i < ac.getDistanceBetweenColony().length; i++) {
            for (int j = 0; j < ac.getDistanceBetweenColony()[i].length; j++) {
                if (i!=j) {
                    this.arrayVisibilityColony[i][j] = averangDistant / ac.getDistanceBetweenColony()[i][j];
                    this.arrayAmountPheromoneOnWay[i][j] = 1;
                }
                else {
                    this.arrayVisibilityColony[i][j] = 0;
                    this.arrayAmountPheromoneOnWay[i][j] = 0;
                }
            }
        }
    }

    private void assingValueVariableFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите количество муравьев: ");
        this.countAnt = Integer.parseInt(br.readLine().trim());
        System.out.println("Введите коэфициент влияния феромона: ");
        this.degreeInfluencePheromone = Double.parseDouble(br.readLine().trim());
        System.out.println("Введите коэфициент влияния расстояния между муравейниками: ");
        this.degreeInfluenceDistance = Double.parseDouble(br.readLine().trim());
        System.out.println("Введите коэфициент учитывающий испарение феромона: ");
        this.evaporationPheromone = Double.parseDouble(br.readLine().trim());
        System.out.println("Ведите количество эпох работы алгоритма");
        this.maxCountEra = Integer.parseInt(br.readLine().trim());
    }

    public void changePferomoneOnWay(AntColony ac){
        for(int i = 0; i < this.arrayAmountPheromoneOnWay.length; i++) {
            for (int j = 0; j < this.arrayAmountPheromoneOnWay[i].length; j++) {
                if (i!=j) {
                    this.arrayAmountPheromoneOnWay[i][j] =
                            (this.evaporationPheromone * this.arrayAmountPheromoneOnWay[i][j] + this.averangDistant *
                                    (ac.getCountColony()-1) * (1 - this.evaporationPheromone) * calculationSummReciprocalLengthWayGivenColony(i, j));
                }
            }
        }
    }

    public void changeProbabityTransitionInColony() {
        double summTemp = 0;

        for (int i = 0; i < this.probabilitiTransitionInColony.length; i++) {
            for (int k = 0; k < this.probabilitiTransitionInColony.length; k++){
               if (i != k) {
                    summTemp += (Math.pow(this.arrayAmountPheromoneOnWay[i][k], this.degreeInfluencePheromone)
                            * Math.pow(this.arrayVisibilityColony[i][k], this.degreeInfluenceDistance));
                }
            }

            for (int j = 0; j < this.probabilitiTransitionInColony[i].length; j++) {
                this.probabilitiTransitionInColony[i][j] = (Math.pow(this.arrayAmountPheromoneOnWay[i][j], this.degreeInfluencePheromone)
                        * Math.pow(this.arrayVisibilityColony[i][j], this.degreeInfluenceDistance));

                this.probabilitiTransitionInColony[i][j] = this.probabilitiTransitionInColony[i][j] / summTemp;
            }
        }
    }

    private double calculationSummReciprocalLengthWayGivenColony(int indexFirstColony, int indexSecondColony) {
        double summReciprocalLength = 0;


        for (Ant ant : Ant.getAntList()) {
            if (isSequenceValuesInArray(ant.getAntWay(), indexFirstColony, indexSecondColony)) {
                summReciprocalLength += 1 / ant.getLengthWay();
            }
        }



        return summReciprocalLength;
    }

    private boolean isSequenceValuesInArray(int[] array, int firstValue, int secondValue) {
        boolean result = false;
        for (int i = 1; i < array.length-1; i++){
            if((array[i] == firstValue)&(array[i+1] == secondValue)) {
                result = true;
            }
        }

        return result;
    }

}
