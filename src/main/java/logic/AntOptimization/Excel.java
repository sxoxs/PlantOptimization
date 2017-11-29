package logic.AntOptimization;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
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

class WriteIntoExcel {
    private static String fileNameForSave;

    public static void setFileNameForSave(String fileNameForSave) {
        WriteIntoExcel.fileNameForSave = fileNameForSave +".xls";
    }

    public static void WriteEraLengthWay(ArrayList<int[]> listWay, ArrayList<Double> listLength) throws IOException {
        CreateBook("DataOutOptimization");

        Workbook book = new HSSFWorkbook(new FileInputStream(fileNameForSave));
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
    static void WriteArray(double[][] Array, String bookName) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(fileNameForSave));
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
    public static void SaveConficDistance() throws IOException {
        CreateBook("Матрица путей между колониями");
        WriteArray(AntColony.getDistanceBetweenColony(), "Матрица путей между колониями");
    }
     public static void CreateFile()throws IOException {
        Workbook book = new HSSFWorkbook();
        book.write(new FileOutputStream(fileNameForSave));
        book.close();
    }
    static void CreateBook(String bookName) throws IOException, FileNotFoundException {
        try {

            Workbook book = new HSSFWorkbook(new FileInputStream(fileNameForSave));
            Sheet sheet = book.createSheet(bookName);

            book.write(new FileOutputStream(fileNameForSave));
            book.close();
        }
        catch (FileNotFoundException e) {
            CreateFile();
            Workbook book = new HSSFWorkbook(new FileInputStream(fileNameForSave));
            Sheet sheet = book.createSheet(bookName);
            book.write(new FileOutputStream(fileNameForSave));
            book.close();
        }
    }
    public static void SaveConfig(AntAlgoritm.DataOptimization outData) throws IOException {

        CreateBook("Параметры алгоритма");
        getNameValueParameters("Count colony", AntColony.getCountColony());
        getNameValueParameters("Count Ants In One colony", AntColony.getCountAntsInOneColony());
        getNameValueParameters("Degree Influence Distance", outData.degreeInfluenceDistance);
        getNameValueParameters("Degree Influence Pheromone", outData.degreeInfluencePheromone);
        getNameValueParameters("Evaporation Pheromone", outData.evaporationPheromone);
        getNameValueParameters("Count Era", outData.maxCountEra);
        getNameValueParameters("Time", outData.timeOptimization);
    }
    static void getNameValueParameters(String cellname, double value) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(fileNameForSave));
        Sheet sheet = book.getSheet("Параметры алгоритма");

        Row row = sheet.createRow(sheet.getLastRowNum()+1);
        Cell NameParametr = row.createCell(0);
        Cell ValueParametr = row.createCell(1);
        NameParametr.setCellValue(cellname);
        ValueParametr.setCellValue(value);

        sheet.autoSizeColumn(0);
        book.write(new FileOutputStream(fileNameForSave));
        book.close();
    }
    static void getNameValueParameters(String name, int value) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(fileNameForSave));
        Sheet sheet = book.getSheet("Параметры алгоритма");

        Row row = sheet.createRow(sheet.getLastRowNum()+1);
        Cell NameParametr = row.createCell(0);
        Cell ValueParametr = row.createCell(1);
        NameParametr.setCellValue(name);
        ValueParametr.setCellValue(value);

        sheet.autoSizeColumn(0);
        book.write(new FileOutputStream(fileNameForSave));
        book.close();
    }

    static class ReadFromExcell {
        public static double ReadValue(String fileName,String bookName, int i, int j) throws IOException {
            double Result = 0;
            HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(fileName));
            HSSFSheet myExcelSheet = myExcelBook.getSheet(bookName);
            HSSFRow row = myExcelSheet.getRow(i);

            {
                Result = row.getCell(j).getNumericCellValue();
            }
            myExcelBook.close();
            return Result;
        }
        public static double[][] LoadDoubleArray(int lengthI, int lengthJ, String fileNameForLoad) throws IOException {
            double[][] Result = new double[lengthI][];
            for (int i = 0; i < lengthI; i++) {
                Result[i] = new double[lengthJ];
                for (int j = 0; j < lengthJ; j++){
                    Result[i][j] = ReadFromExcell.ReadValue(fileNameForLoad, "Матрица путей между колониями", i, j);
                }
            }
            return Result;
        }
    }
}
