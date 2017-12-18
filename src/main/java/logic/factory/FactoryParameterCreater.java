package logic.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FactoryParameterCreater {

    public void creater (FactoryParameter factoryParameter) throws IOException{
        String fileNameForLoad;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите количество продуктов");
        int countProduct = Integer.parseInt(trimmer(br.readLine()));
        factoryParameter.setProductCount( countProduct );
        System.out.println("Введите количество аппаратов");
        int countApparat = Integer.parseInt(trimmer(br.readLine()));
        factoryParameter.setApparatCount( countApparat );
        System.out.println("Введите название файла для загрузки");
        fileNameForLoad = trimmer(br.readLine());

        FactoryParameterReader fpR = new FactoryParameterReader();

        factoryParameter.setChangeoverListArray(fpR.readChangeoverListArray(fileNameForLoad, countApparat));
        factoryParameter.setProductTimeReleaseArray(fpR.readProductTimeReleaseArray(fileNameForLoad, countApparat, countProduct));
        factoryParameter.setApparatScheduleArray(fpR.apparatScheduleArray(fileNameForLoad));
    }

    private String trimmer (String text) {
        if (text.isEmpty()) {
            text.trim().toLowerCase();
            return text;
        }
        else {
            System.out.println("Значение отсутствует");
        }
        return "1";

    }
}
