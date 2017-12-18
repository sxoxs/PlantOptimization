package logic.factory;

import java.util.ArrayList;

public class FactoryParameter {
    private ArrayList<double[][]> changeoverListArray = new ArrayList<>();
    private double[][] productTimeReleaseArray;
    private int[][] apparatScheduleArray;
    private int[] sequenceProduct;
    int productCount;
    int apparatCount;

    public ArrayList<double[][]> getChangeoverListArray() {
        return changeoverListArray;
    }

    public void setChangeoverListArray(ArrayList<double[][]> changeoverListArray) {
        this.changeoverListArray = changeoverListArray;
    }

    public int[][] getApparatScheduleArray() {
        return apparatScheduleArray;
    }

    public void setApparatScheduleArray(int[][] apparatScheduleArray) {
        this.apparatScheduleArray = apparatScheduleArray;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getApparatCount() {
        return apparatCount;
    }

    public void setApparatCount(int apparatCount) {
        this.apparatCount = apparatCount;
    }

    public void addChangeoverArray (double[][] changeoverArray) {
        this.changeoverListArray.add(changeoverArray);
    }

    public double[][] getProductTimeReleaseArray() {
        return productTimeReleaseArray;
    }

    public void setProductTimeReleaseArray(double[][] productTimeReleaseArray) {
        this.productTimeReleaseArray = productTimeReleaseArray;
    }

    public int[] getSequenceProduct() {
        return sequenceProduct;
    }

    public void setSequenceProduct(int[] sequenceProduct) {
        this.sequenceProduct = sequenceProduct;
    }
}
