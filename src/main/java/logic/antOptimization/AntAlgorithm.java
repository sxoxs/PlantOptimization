package logic.antOptimization;

import logic.factory.FactoryParameter;
import logic.factory.FactoryParameterCreater;
import logic.factory.Schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AntAlgorithm {

    public DataOptimization algorithm(ParameterAntOptimization algoritmParametrs, AntColony colony) throws IOException {
        inizializeAnts(algoritmParametrs, colony);

        DataOptimization dataOut = new DataOptimization(algoritmParametrs, colony);

        runAntAlgorithm(algoritmParametrs, colony, dataOut);
        outInConsoleDataAntOptimization(dataOut);
        System.gc();

        return dataOut;
    }

    private void inizializeAnts(ParameterAntOptimization antAlgoritmParam, AntColony ac) {
            for (int antsInOneColony = 0; antsInOneColony < ac.getCountAntsInOneColony(); antsInOneColony++){
                for (int IndexFirstColonyInWay = 0; IndexFirstColonyInWay < ac.getCountColony(); IndexFirstColonyInWay++){
                    Ant.addAnt(new Ant(IndexFirstColonyInWay, antAlgoritmParam, ac));
                }
            }
    }

    private DataOptimization runAntAlgorithm(ParameterAntOptimization parametrs, AntColony ac, DataOptimization inputData) throws IOException  {

        int indexOptimalAnt = Ant.getIngexMinimalLengthWay(Ant.getAntList());
        Double lengthWayCurrentOptima = Ant.getAntList().get(indexOptimalAnt).getLengthWay();
        Double lengthWayOptima = lengthWayCurrentOptima;

        int[] currentOptimaWay = Ant.getAntList().get(indexOptimalAnt).getAntWay();
        int[] optimaWay = Ant.getAntList().get(indexOptimalAnt).getAntWay();

        ArrayList<Double> lengthWayList = new ArrayList<>();
        ArrayList<int[]> wayList = new ArrayList<>();
        lengthWayList.add(lengthWayCurrentOptima);
        wayList.add(currentOptimaWay);

        StringBuffer sb = new StringBuffer("");

        int CurrentEra = 1;
        int NotChangeMinWay = 0;

        Date date = new Date();
        long timeOptimization = date.getTime();

        do {
            parametrs.changePferomoneOnWay(ac);
            parametrs.changeProbabityTransitionInColony();
            for (int i = 0; i < Ant.getAntList().size(); Ant.getAntList().get(i++).changeWay(parametrs, ac) );

            indexOptimalAnt = Ant.getIngexMinimalLengthWay(Ant.getAntList());
            lengthWayCurrentOptima = Ant.getAntList().get(indexOptimalAnt).getLengthWay();
            currentOptimaWay = Ant.getAntList().get(indexOptimalAnt).getAntWay();

            if (lengthWayOptima > lengthWayCurrentOptima) {

                optimaWay = currentOptimaWay;
                lengthWayOptima =  lengthWayCurrentOptima;
            }

            wayList.add( currentOptimaWay );
            lengthWayList.add( lengthWayCurrentOptima );

            if ( isChangeWay(lengthWayList) ) {
                NotChangeMinWay++;
            }
            else {
                NotChangeMinWay = 0;
            }

            sb.delete( 0, sb.length() );
            sb.append("Эпоха № ")
                    .append(CurrentEra)
                    .append(" ")
                    .append(lengthWayCurrentOptima)
                    .append(Arrays.toString(currentOptimaWay));
            System.out.println(sb);

            // TODO: 06/12/17 change while for foo()
        } while ((++CurrentEra <= parametrs.getMaxCountEra())&(NotChangeMinWay < 5000));


        Date date2 = new Date();
        timeOptimization -= date2.getTime();
        System.out.println("Алгоритм работал:  " + (timeOptimization * (-1)) + " мс");
        System.out.println("Эпох пройдено : " + --CurrentEra);
        if (5000 == NotChangeMinWay){
            System.out.println("На протяжении 5000 эпох путь не улучшался, алгоритм закончен");
        }
        else{
            System.out.println("Домтигнут лимит эпох, алгоритм закончен");
        }


        inputData.setOptimaWay(optimaWay);
        inputData.setLengthOptimaWay(lengthWayOptima);
        inputData.setTimeOptimization(timeOptimization);

        return inputData;
    }

private static Boolean isChangeWay(ArrayList<Double> list) {
        if ( 0 == Double.compare( list.get(list.size()-2), list.get(list.size()-1) ) ){
            return true;
        }
        else {
            return false;
        }
}

    private static void outInConsoleDataAntOptimization(DataOptimization dataOut) throws IOException {
        System.out.println();
        System.out.println("Вывод");
//        System.out.println("Количество муравейников равно");
//        System.out.println(AntColony.getCountColony());
//        System.out.println("Количество муравьёв в каждом муравейнике равно: ");
//        System.out.println(AntColony.getCountAntsInOneColony());

//        System.out.println("Матрица расстояний между муравейниками равна: ");
//        System.out.println(Arrays.toString(AntColony.getDistanceBetweenColony()));

//        System.out.println();
//        System.out.println("Параметры алгоритма:");
//        System.out.println("Коэфициент влияния феромона: ");
//        System.out.println( ParameterAntOptimization.getDegreeInfluencePheromone());
//        System.out.println("Коэфициент влияния расстояния между муравейниками");
//        System.out.println(ParameterAntOptimization.getDegreeInfluenceDistance());
//        System.out.println("Коэфициент учитывающий испарение феромона:");
//        System.out.println( ParameterAntOptimization.getEvaporationPheromone());
//        System.out.println();
//        System.out.println("Переменные алгоритма:");
//        System.out.println("Среднее расстояние между муравейниками равно: ");
//        System.out.println( ParameterAntOptimization.getAverangDistant() );
//        System.out.println("Параметр видимости муравейника: ");
//        System.out.println(Arrays.toString( ParameterAntOptimization.getArrayVisibilityColony() ));

//        System.out.println("Количество феромона на пути из i-го в j-й муравейник");
//        System.out.println(Arrays.toString( ParameterAntOptimization.getArrayAmountPheromoneOnWay() ));
//        System.out.println("Вероятность перехода из i-го в j-й муравейник");
//        System.out.println(Arrays.toString( ParameterAntOptimization.getProbabilitiTransitionInColony() ));

        System.out.println("Общее количество муравьев:");
        System.out.println(Ant.getAntList().size());

        System.out.println();
        System.out.println("Лучший путь за все эпохи: ");
        System.out.println(Arrays.toString(dataOut.getOptimaWay()));

        System.out.print("Длина оптимального пути за все эпохи: ");
        System.out.println(dataOut.getLengthOptimaWay());

//        System.out.println("Матрица путей по эпохам: ");
//        System.out.println(Arrays.toString(dataOut.lengthOptimaWayList.toArray()));




    }



}
