package logic.factory;

import logic.parser.ExcelReader;

import java.io.IOException;
import java.util.ArrayList;

public class FactoryParameterReader {

    public ArrayList<double[][]> readChangeoverListArray(String fileName, int countApparat) throws IOException{
        ArrayList<double[][]> changeoverList = new ArrayList<>();
        ExcelReader er = new ExcelReader();
        er.setFileNameForLoad(fileName);
        String bookNameForLoad = "";

        for (int apparat = 0; apparat < countApparat; apparat++){
            bookNameForLoad = "аппарат_" + apparat;
            changeoverList.add(er.readDoubleArray(bookNameForLoad));
        }

        return changeoverList;
    }

    public double[][] readProductTimeReleaseArray (String fileName) throws IOException{
        double[][] result;

        ExcelReader er = new ExcelReader();
        er.setFileNameForLoad(fileName);
        String bookNameForLoad = "время выпуска";
        result = er.readDoubleArray(bookNameForLoad);

        return result;
    }

    public int[][] apparatScheduleArray (String fileName) throws IOException{
        int[][] result;
        int countApparat = 0;
        int countProduct = 0;

        ExcelReader er = new ExcelReader();
        er.setFileNameForLoad(fileName);
        String bookNameForLoad = "маршрут выпуска";

        double[][] tempArray = er.readDoubleArray(bookNameForLoad);
        countProduct = tempArray.length;
        countApparat = tempArray[0].length;

        result = new int[countProduct][];
        for (int i = 0; i < countProduct; i++){
            result[i] = new int[countApparat];
            for (int j = 0; j < countApparat; j++){
                result[i][j] = (int) tempArray[i][j];
            }
        }

        return result;
    }

}
