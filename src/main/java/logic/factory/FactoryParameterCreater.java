package logic.factory;

import logic.parser.ExcelReader;
import logic.parser.ExcelWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FactoryParameterCreater {

    public FactoryParameter changer (FactoryParameter factoryParameter) throws IOException{
        String fileNameForLoad;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите название файла для загрузки матриц");
        fileNameForLoad = trimmer(br.readLine()) + ".xls";

        ExcelReader er = new ExcelReader();
        er.setFileNameForLoad(fileNameForLoad);
// TODO: 20/12/17 исправить нижнее
        int countProduct = (int) er.readValue("параметры", 0,1);
        factoryParameter.setProductCount( countProduct );

        int countApparat = (int) er.readValue("параметры", 1,1);
        factoryParameter.setApparatCount( countApparat );

        FactoryParameterReader fpR = new FactoryParameterReader();

        factoryParameter.setChangeoverListArray(fpR.readChangeoverListArray(fileNameForLoad, countApparat));
        factoryParameter.setProductTimeReleaseArray(fpR.readProductTimeReleaseArray(fileNameForLoad));
        factoryParameter.setApparatScheduleArray(fpR.apparatScheduleArray(fileNameForLoad));

        return factoryParameter;
    }

    public void fileCreater (String fileName, int countApparat, int countProduct) throws IOException {
        ExcelWriter ew = new ExcelWriter();
        ew.setFileNameForSave(fileName);

        ew.createBook("время выпуска");
        ew.createBook("маршрут выпуска");
        ew.createBook("параметры");

        for (int j = 0; j < countApparat; j++){
            ew.createBook("аппарат_" + j);
        }
    }

    private String trimmer (String text) {
        if (!text.isEmpty()) {
            text.trim().toLowerCase();
            return text;
        }
        else {
            System.out.println("Значение отсутствует");
        }

        return "1";
    }
}
