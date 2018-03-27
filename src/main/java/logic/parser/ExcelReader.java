package logic.parser;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    private String fileNameForLoad;

    public double readValue(String bookName, int i, int j) throws IOException {
        double result = 0;
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(fileNameForLoad));
        HSSFSheet myExcelSheet = myExcelBook.getSheet(bookName);
        HSSFRow row = myExcelSheet.getRow(i);

        {
            result = row.getCell(j).getNumericCellValue();
        }
        myExcelBook.close();
        return result;
    }

    public  double[][] readDoubleArray(String bookName) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(fileNameForLoad));
        HSSFSheet myExcelSheet = myExcelBook.getSheet(bookName);
        HSSFRow row = myExcelSheet.getRow(0);

        int lengthI =  myExcelSheet.getLastRowNum()+1;
        int lengthJ = row.getLastCellNum();

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
