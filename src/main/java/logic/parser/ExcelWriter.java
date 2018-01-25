
package logic.parser;

import logic.antOptimization.AntColony;
import logic.antOptimization.DataOptimization;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelWriter {

    private String fileNameForSave;

    public void setFileNameForSave(String fileNameForSave) {
        this.fileNameForSave = fileNameForSave + ".xls";
    }

    public void writeEraLengthWay(ArrayList<int[]> listWay, ArrayList<Double> listLength) throws IOException {
        createBook("DataOutOptimization");

        Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
        Sheet sheet = book.getSheet("DataOutOptimization");

        for (int i = 0; i < listLength.size(); i++) {
            Row row = sheet.createRow(i);
            Cell Era = row.createCell(0);
            Era.setCellValue(i + 1);

            Era = row.createCell(1);
            Era.setCellValue(listLength.get(i));


            Era = row.createCell(2);
            Era.setCellValue("Путь: ");

            for (int j = 0; j < listWay.get(i).length; j++) {
                Era = row.createCell(3 + j);
                Era.setCellValue(listWay.get(i)[j]);
            }
            sheet.autoSizeColumn(i);
        }

        book.write(new FileOutputStream(fileNameForSave));
        book.close();

    }

    private void writeArray(double[][] Array, String bookName) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
        Sheet sheet = book.getSheet(bookName);
        Row row;
        for (int i = 0; i < Array.length; i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < Array[i].length; j++) {
                Cell Era = row.createCell(j);
                Era.setCellValue(Array[i][j]);
                sheet.autoSizeColumn(j);
            }
        }
        book.write(new FileOutputStream(fileNameForSave));
        book.close();
    }

    public void saveConficDistance(AntColony ac) throws IOException {
        createBook("Матрица путей между колониями");
        writeArray(ac.getDistanceBetweenColony(), "Матрица путей между колониями");
    }

    private void createFile() throws IOException {
        Workbook book = new HSSFWorkbook();
        book.write(new FileOutputStream(this.fileNameForSave));
        book.close();
    }

    public void createBook(String bookName) throws IOException {
        try {

            Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
            Sheet sheet = book.createSheet(bookName);

            book.write(new FileOutputStream(this.fileNameForSave));
            book.close();
        } catch (FileNotFoundException e) {
            createFile();
            Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
            Sheet sheet = book.createSheet(bookName);
            book.write(new FileOutputStream(this.fileNameForSave));
            book.close();
        }
    }

    public void saveConfig(DataOptimization outData, AntColony ac) throws IOException {
        createBook("Параметры алгоритма");
        writeValueParametersByName("Параметры алгоритма","Count colony", ac.getCountColony());
        writeValueParametersByName("Параметры алгоритма","Degree Influence Distance", outData.getDegreeInfluenceDistance());
        writeValueParametersByName("Параметры алгоритма","Degree Influence Pheromone", outData.getDegreeInfluencePheromone());
        writeValueParametersByName("Параметры алгоритма","Evaporation Pheromone", outData.getEvaporationPheromone());
        writeValueParametersByName("Параметры алгоритма","Count Era", outData.getMaxCountEra());
        writeValueParametersByName("Параметры алгоритма","Time", outData.getTimeOptimization());
    }

    public void writeValueParametersByName(String bookName, String cellName, double value) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
        Sheet sheet = book.getSheet(bookName);

        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        Cell NameParametr = row.createCell(0);
        Cell ValueParametr = row.createCell(1);
        NameParametr.setCellValue(cellName);
        ValueParametr.setCellValue(value);

        sheet.autoSizeColumn(0);
        book.write(new FileOutputStream(this.fileNameForSave));
        book.close();
    }

    public void writeValueParametersByName(String bookName, String cellName, int value) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
        Sheet sheet = book.getSheet(bookName);

        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        Cell NameParametr = row.createCell(0);
        Cell ValueParametr = row.createCell(1);
        NameParametr.setCellValue(cellName);
        ValueParametr.setCellValue(value);

        sheet.autoSizeColumn(0);
        book.write(new FileOutputStream(this.fileNameForSave));
        book.close();
    }

    public void setBorder(String bookName, int i, int j) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
        Sheet sheet = book.getSheet(bookName);
        Row row;
        CellStyle style = book.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        for (int ii = 0; ii < i; ii++) {
            row = sheet.createRow(ii);
            for (int jj = 0; jj < j; jj++) {
                Cell Era = row.createCell(jj);
                Era.setCellStyle(style);
            }
        }
        book.write(new FileOutputStream(fileNameForSave));
        book.close();
    }
}