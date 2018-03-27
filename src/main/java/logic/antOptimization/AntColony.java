package logic.antOptimization;

import logic.factory.FactoryParameter;
import logic.factory.Schedule;

public class AntColony {

    private int countColony;

    private double[][] distanceBetweenColony;

    public void createAntColony(FactoryParameter fp) {
        this.countColony = fp.getProductCount();
        setDistansFromFactory(fp);
    }

    public int getCountColony() {
        return countColony;
    }

    public void setCountColony(int countColony) {
        this.countColony = countColony;
    }

    public double[][] getDistanceBetweenColony() {
        return distanceBetweenColony;
    }

    public void setDistanceBetweenColony(double[][] distanceBetweenColony) {
        this.distanceBetweenColony = distanceBetweenColony;
    }

    private void setDistansFromFactory (FactoryParameter fp) {

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
