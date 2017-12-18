package logic.parser;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    private String fileNameForLoad;

    public double readValue(String bookName, int i, int j) throws IOException {
        double result = 0;
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(fileNameForLoad));
        XSSFSheet myExcelSheet = myExcelBook.getSheet(bookName);
        XSSFRow row = myExcelSheet.getRow(i);

        {
            result = row.getCell(j).getNumericCellValue();
        }
        myExcelBook.close();
        return result;
    }

    public  double[][] readDoubleArray(String bookName) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(fileNameForLoad));
        XSSFSheet myExcelSheet = myExcelBook.getSheet(bookName);
        XSSFRow row = myExcelSheet.getRow(0);

        int lengthI = row.getLastCellNum();
        int lengthJ = 0;
// TODO: 18/12/17 проверить обязательно
        for (;;){
            if (null != row.getCell(lengthI+1)) {
                lengthJ++;
            }
            else break;
        }

        double[][] result = new double[lengthI][];

        for (int i = 0; i < lengthI ; i++) {
            result[i] = new double[lengthJ];

            row = myExcelSheet.getRow(i);
            for (int j = 0; j < lengthJ; j++){
                result[i][j] = row.getCell(j).getNumericCellValue();
            }
        }

        return result;
    }

    public String getFileNameForLoad() {
        return fileNameForLoad;
    }

    public void setFileNameForLoad(String fileNameForLoad) {
        this.fileNameForLoad = fileNameForLoad;
    }
}
