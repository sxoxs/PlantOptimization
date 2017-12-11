package logic.parser;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

        public double ReadValue(String fileName,String bookName, int i, int j) throws IOException {
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

        public  double[][] LoadDoubleArray(int lengthI, int lengthJ, String fileNameForLoad) throws IOException {
            double[][] Result = new double[lengthI][];
            for (int i = 0; i < lengthI; i++) {
                Result[i] = new double[lengthJ];
                for (int j = 0; j < lengthJ; j++){
                    Result[i][j] = ReadValue(fileNameForLoad, "Матрица путей между колониями", i, j);
                }
            }
            return Result;
        }



}
