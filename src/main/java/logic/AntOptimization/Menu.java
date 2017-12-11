package logic.AntOptimization;

import logic.parser.ExcelWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;


public  class Menu {
    private static DataOptimization outData;

    public  void runProgramm() throws IOException{
        AntColony antColony = createAntColony();
        ParameterAntOptimization param = createParameter(antColony);

        AntAlgoritm antAlgoritm = new AntAlgoritm();

        outData = antAlgoritm.Algoritm(param, antColony);
        saveToFile(antColony);

    }

    private AntColony createAntColony() throws IOException {
        AntColony ac = new AntColony();


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Добро пожаловать в муравьиный алгоритм");
        System.out.println("Загрузить предыдущие настройки колоний?");
        System.out.println("Да - Y / Нет - N");

        String fileNameForLoad;

        if ( sayYes(br.readLine()) ) {
            System.out.println("Введите название файла для загрузки настроек");
            fileNameForLoad = br.readLine().trim().toLowerCase();
            ac.createAntColony(fileNameForLoad);
        }
        else {
            ac.createAntColony();
        }

        return ac;
    }


    private ParameterAntOptimization createParameter(AntColony ac) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileNameForLoad;

        System.out.println("Загрузить предыдущие настройки алгоритма?");
        System.out.println("Да - Y / Нет - N");

        fileNameForLoad = "";
        if ( sayYes(br.readLine()) ) {
            System.out.println("Введите название файла для загрузки настроек");
            fileNameForLoad = br.readLine().trim().toLowerCase();
        }

        ParameterAntOptimization parametersAlgoritm = new ParameterAntOptimization();
        parametersAlgoritm.createParameterAntOptimization(fileNameForLoad, ac);

        return parametersAlgoritm;
    }

    private void saveToFile(AntColony ac) throws IOException{

        LocalDate localDate = LocalDate.now();
        System.out.println("Введите название файла для сохранения настроек");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuffer sb = new StringBuffer(br.readLine().trim().toLowerCase());
        sb.append("_")
                .append(Integer.toString(localDate.getDayOfMonth()))
                .append('.')
                .append(Integer.toString(localDate.getMonth().getValue()))
                .append('.')
                .append( Integer.toString(localDate.getYear()));


        ExcelWriter ew = new ExcelWriter();


        ew.setFileNameForSave(sb.toString());
       // WriteIntoExcel.WriteEraLengthWay(outData.listOptimaWay, outData.listLengthOptimaWay);
        ew.SaveConficDistance(ac);
        ew.SaveConfig(outData, ac);
    }

    private Boolean sayYes (String str) {
        boolean resalt = str.trim().toLowerCase().equals("y");

        return resalt;
    }
}
