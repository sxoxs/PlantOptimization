package logic.main;

import logic.antOptimization.AntAlgorithm;
import logic.antOptimization.AntColony;
import logic.antOptimization.DataOptimization;
import logic.antOptimization.ParameterAntOptimization;
import logic.factory.FactoryParameter;
import logic.factory.FactoryParameterCreater;
import logic.parser.ExcelWriter;
import logic.parser.XmlReader;
import logic.parser.XmlWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;


public  class Menu {

    public void showMenu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer("Выберите:\n");
        sb.append("1. Создать Excel-файл для последующего ввода данных;\n")
                .append("2. Начать расчёт, используя подготовленный Excel файл;\n")
                .append("3. Выход.");

        String str = "";

        for (;;) {
            System.out.println(sb.toString());
            str = br.readLine();

            if (sayYes(str, "1")) {
                excelFileCreater();
                System.out.println("Файл создан, заполните и приступайте к расчёту.");
                break;
            }

            if (sayYes(str, "2")) {
                runProgramm();
                break;
            }

            if (sayYes(str, "3")) {
                System.out.println("Работа завершена.");
                break;
            }
        }
    }

    private void excelFileCreater()throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество продуктов");
        int productCount = Integer.parseInt(trimmer(br.readLine()));
        System.out.println("Введите количество аппаратов");
        int apparatCount = Integer.parseInt(trimmer(br.readLine()));
        System.out.println("Введите название файла");
        String fileName = trimmer(br.readLine());

        FactoryParameterCreater fpc = new FactoryParameterCreater();
        fpc.fileCreater(fileName, apparatCount, productCount);
    }

    private void runProgramm() throws IOException{
        FactoryParameterCreater fpc = new FactoryParameterCreater();
        FactoryParameter fp = new FactoryParameter();
        fp = fpc.changer(fp);

        AntColony antColony = createAntColony(fp);
        ParameterAntOptimization param = setParameter(antColony);

        AntAlgorithm antAlgorithm = new AntAlgorithm();
        DataOptimization outData = antAlgorithm.algorithm(param, antColony, fp);

        saver(param, outData, antColony);
    }

    private void saver(ParameterAntOptimization param, DataOptimization outData, AntColony antColony) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Cохранить результаты? y/n");
        if (sayYes(br.readLine())) {
            saveToFileExcel(outData, antColony);
        }
        System.out.println("Cохранить настройки? y/n");
        if (sayYes(br.readLine())) {
            paramXmlSave(param, antColony);
        }
    }

    private void paramXmlSave(ParameterAntOptimization param, AntColony antColony) throws IOException {
        System.out.println("Введите название файла");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = trimmer(br.readLine());
        XmlWriter xmlWriter = new XmlWriter();
        xmlWriter.write(param, fileName);
    }

    private AntColony createAntColony(FactoryParameter fp) throws IOException {
        AntColony ac = new AntColony();
        ac.createAntColony(fp);

        return ac;
    }

    private ParameterAntOptimization setParameter(AntColony ac) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileNameForLoad;

        System.out.println("Загрузить предыдущие настройки алгоритма?");
        System.out.println("Да - Y / Нет - N");

        if ( sayYes(br.readLine()) ) {
            System.out.println("Введите название файла для загрузки настроек");
            fileNameForLoad = br.readLine();
            XmlReader xmlReader = new XmlReader();
            ParameterAntOptimization parameterAntOptimization = xmlReader.parameterParse(fileNameForLoad);
            return parameterAntOptimization;
        }
        else {
            return new ParameterAntOptimization(ac);
        }
    }

    private void saveToFileExcel(DataOptimization outData, AntColony ac) throws IOException{

        LocalDate localDate = LocalDate.now();
        System.out.println("Введите название файла для сохранения результатов");

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
        ew.writeEraLengthWay(outData.getOptimaWayList(), outData.getLengthOptimaWayList());
        ew.saveConficDistance(ac);
        ew.saveConfig(outData, ac);
//        ew.paintLineChart(outData);
    }


    private boolean sayYes (String str) {
        return trimmer(str).equals("y");
    }

    private boolean sayYes (String strA, String strB){
        return trimmer(strA).equals(trimmer(strB));
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
