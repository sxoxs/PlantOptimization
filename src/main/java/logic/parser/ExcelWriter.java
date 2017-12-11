package logic.parser;

import logic.AntOptimization.AntColony;
import logic.AntOptimization.DataOptimization;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelWriter {

    private String fileNameForSave;

    public void setFileNameForSave(String fileNameForSave) {
        this.fileNameForSave = fileNameForSave +".xls";
    }

    public void WriteEraLengthWay(ArrayList<int[]> listWay, ArrayList<Double> listLength) throws IOException {
        CreateBook("DataOutOptimization");

        Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
        Sheet sheet = book.getSheet("DataOutOptimization");

        for (int i = 0; i < listLength.size(); i++) {
            Row row = sheet.createRow(i);
            Cell Era = row.createCell(0);
            Era.setCellValue(i+1);

            Era = row.createCell(1);
            Era.setCellValue( listLength.get(i));


            Era = row.createCell(2);
            Era.setCellValue("Путь: ");

            for (int j = 0; j < listWay.get(i).length; j++) {
                Era = row.createCell(3+ j);
                Era.setCellValue(listWay.get(i)[j]);
            }
            sheet.autoSizeColumn(i);
        }

        book.write(new FileOutputStream(fileNameForSave));
        book.close();

    }
    private void WriteArray(double[][] Array, String bookName) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
        Sheet sheet = book.getSheet(bookName);
        Row row;
        for (int i = 0; i < Array.length; i++) {
            row = sheet.createRow(i);
            for (int j = 0; j <Array[i].length; j++) {
                Cell Era = row.createCell(j);
                Era.setCellValue(Array[i][j]);
                sheet.autoSizeColumn(j);
            }
        }
        book.write(new FileOutputStream(fileNameForSave));
        book.close();
    }
    public void SaveConficDistance(AntColony ac) throws IOException {
        CreateBook("Матрица путей между колониями");
        WriteArray(ac.getDistanceBetweenColony(), "Матрица путей между колониями");
    }
    private void CreateFile()throws IOException {
        Workbook book = new HSSFWorkbook();
        book.write(new FileOutputStream(this.fileNameForSave));
        book.close();
    }
    private void CreateBook(String bookName) throws IOException, FileNotFoundException {
        try {

            Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
            Sheet sheet = book.createSheet(bookName);

            book.write(new FileOutputStream(this.fileNameForSave));
            book.close();
        }
        catch (FileNotFoundException e) {
            CreateFile();
            Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
            Sheet sheet = book.createSheet(bookName);
            book.write(new FileOutputStream(this.fileNameForSave));
            book.close();
        }
    }
    public void SaveConfig(DataOptimization outData, AntColony ac) throws IOException {

        CreateBook("Параметры алгоритма");
        getNameValueParameters("Count colony", ac.getCountColony());
        getNameValueParameters("Count Ants In One colony", ac.getCountAntsInOneColony());
        getNameValueParameters("Degree Influence Distance", outData.getDegreeInfluenceDistance());
        getNameValueParameters("Degree Influence Pheromone", outData.getDegreeInfluencePheromone());
        getNameValueParameters("Evaporation Pheromone", outData.getEvaporationPheromone());
        getNameValueParameters("Count Era", outData.getMaxCountEra());
//        getNameValueParameters("Time", outData.getTimeOptimization());
    }
    private void getNameValueParameters(String cellname, double value) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
        Sheet sheet = book.getSheet("Параметры алгоритма");

        Row row = sheet.createRow(sheet.getLastRowNum()+1);
        Cell NameParametr = row.createCell(0);
        Cell ValueParametr = row.createCell(1);
        NameParametr.setCellValue(cellname);
        ValueParametr.setCellValue(value);

        sheet.autoSizeColumn(0);
        book.write(new FileOutputStream(this.fileNameForSave));
        book.close();
    }
    private void getNameValueParameters(String name, int value) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
        Sheet sheet = book.getSheet("Параметры алгоритма");

        Row row = sheet.createRow(sheet.getLastRowNum()+1);
        Cell NameParametr = row.createCell(0);
        Cell ValueParametr = row.createCell(1);
        NameParametr.setCellValue(name);
        ValueParametr.setCellValue(value);

        sheet.autoSizeColumn(0);
        book.write(new FileOutputStream(this.fileNameForSave));
        book.close();
    }

}
