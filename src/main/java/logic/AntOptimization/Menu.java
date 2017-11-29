package logic.AntOptimization;

import logic.AntOptimization.AntAlgoritm.DataOptimization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public  class Menu {
    private static DataOptimization outData;
    protected static String fileNameForLoad;


    public static void runProgramm() throws IOException{

        setParameter();
        runAlgoritm();
        saveToFile();

    }

    private static void setParameter() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String AGRUMENT;

        System.out.println("Добро пожаловать в муравьиный алгоритм");
        System.out.println("Загрузить предыдущие настройки?");
        System.out.println("Да - Y / Нет - N");

        if ( br.readLine().trim().toLowerCase().equals("y") ) {
            System.out.println("Введите название файла для загрузки настроек");
            fileNameForLoad = br.readLine().trim().toLowerCase();
            AntColony.createAntColony(fileNameForLoad);
        }
        else {
            AntColony.createAntColony();
        }

        System.out.println("Загрузить предыдущие настройки алгоритма?");
        System.out.println("Да - Y / Нет - N");

        fileNameForLoad = "";
        if ( br.readLine().trim().toLowerCase().equals("y") ) {
            System.out.println("Введите название файла для загрузки настроек");
            fileNameForLoad = br.readLine().trim().toLowerCase();
        }
    }


    private static void  runAlgoritm() throws IOException {
        ParameterAntOptimization.createParameterAntOptimization(fileNameForLoad);
        outData = AntAlgoritm.Algoritm();
    }

    private static void saveToFile() throws IOException{

        Date date = new Date();
        date.getTime();
        System.out.println("Введите название файла для сохранения настроек");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuffer sb = new StringBuffer(br.readLine().trim().toLowerCase());
        sb.append("_")
                .append(Integer.toString(date.getDate()))
                .append('.')
                .append(Integer.toString(date.getMonth()))
                .append('.')
                .append( Integer.toString(date.getYear()))
                .append("_")
                .append(Integer.toString(date.getHours()))
                .append(".")
                .append(Integer.toString(date.getMinutes()));

        WriteIntoExcel.setFileNameForSave(sb.toString());
       // WriteIntoExcel.WriteEraLengthWay(outData.listOptimaWay, outData.listLengthOptimaWay);
        WriteIntoExcel.SaveConficDistance();
        WriteIntoExcel.SaveConfig(outData);
    }
}
