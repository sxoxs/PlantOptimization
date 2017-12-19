/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */


package logic.parser;

import logic.antOptimization.AntColony;
import logic.antOptimization.DataOptimization;
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
        getNameValueParameters("Count colony", ac.getCountColony());
        getNameValueParameters("Count Ants In One colony", ac.getCountAntsInOneColony());
        getNameValueParameters("Degree Influence Distance", outData.getDegreeInfluenceDistance());
        getNameValueParameters("Degree Influence Pheromone", outData.getDegreeInfluencePheromone());
        getNameValueParameters("Evaporation Pheromone", outData.getEvaporationPheromone());
        getNameValueParameters("Count Era", outData.getMaxCountEra());
        getNameValueParameters("Time", outData.getTimeOptimization());
    }

    private void getNameValueParameters(String cellname, double value) throws IOException {
        Workbook book = new HSSFWorkbook(new FileInputStream(this.fileNameForSave));
        Sheet sheet = book.getSheet("Параметры алгоритма");

        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
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

        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        Cell NameParametr = row.createCell(0);
        Cell ValueParametr = row.createCell(1);
        NameParametr.setCellValue(name);
        ValueParametr.setCellValue(value);

        sheet.autoSizeColumn(0);
        book.write(new FileOutputStream(this.fileNameForSave));
        book.close();
    }


//    public void paintLineChart(DataOptimization outData) {
//        try {
//            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(this.fileNameForSave));
//            XSSFSheet sheet = wb.createSheet("linechart");
//            XSSFDrawing drawing = sheet.createDrawingPatriarch();
//            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);
//
//            XSSFChart chart = drawing.createChart(anchor);
//
//
//
//
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//    }

}