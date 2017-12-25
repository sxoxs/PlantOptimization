package logic.antOptimization;

import logic.factory.FactoryParameter;
import logic.factory.Schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AntAlgorithm {

    public DataOptimization algorithm(ParameterAntOptimization algoritmParametrs, AntColony colony, FactoryParameter factoryParameter) throws IOException {
        inizializeAnts(algoritmParametrs, colony, factoryParameter);

        DataOptimization dataOut = new DataOptimization(algoritmParametrs, colony);

        runAntAlgorithm(algoritmParametrs, colony, dataOut, factoryParameter);
        outInConsoleDataAntOptimization(dataOut);
        System.gc();

        return dataOut;
    }

    private void inizializeAnts(ParameterAntOptimization antAlgoritmParam, AntColony ac, FactoryParameter fParam) {
            for (int antsInOneColony = 0; antsInOneColony < ac.getCountAntsInOneColony(); antsInOneColony++){
                for (int IndexFirstColonyInWay = 0; IndexFirstColonyInWay < ac.getCountColony(); IndexFirstColonyInWay++){
                    Ant.addAnt(new Ant(IndexFirstColonyInWay, antAlgoritmParam, ac, fParam));
                }
            }
    }

    private DataOptimization runAntAlgorithm(ParameterAntOptimization parametrs, AntColony ac, DataOptimization inputData, FactoryParameter fp) throws IOException  {

        int indexOptimalAnt = Ant.getIngexMinimalLengthWay(Ant.getAntList());

        double lengthWayCurrentOptima = Ant.getAntList().get(indexOptimalAnt).getLengthWay();
        double lengthWayOptima = lengthWayCurrentOptima;

        int[] currentOptimaWay = new int[ac.getCountColony()];
        int[] optimaWay = new int[ac.getCountColony()];

        for (int i = 0; i < ac.getCountColony(); i++){
            currentOptimaWay[i] = Ant.getAntList().get(indexOptimalAnt).getAntWay()[i];
            optimaWay[i] = currentOptimaWay[i];
        }

        int CurrentEra = 1;
        int NotChangeMinWay = 0;

        Date date = new Date();
        long timeOptimization = date.getTime();
        int countAnt = Ant.getAntList().size();

        do {
            parametrs.changePferomoneOnWay(ac);
            parametrs.changeProbabityTransitionInColony();

            for (int ant = 0; ant < countAnt; ant++){
                Ant.getAntList().get(ant).changeWay(parametrs, ac, fp);
            }

            indexOptimalAnt = Ant.getIngexMinimalLengthWay(Ant.getAntList());

            for (int i = 0; i < ac.getCountColony(); i++){
                currentOptimaWay[i] = Ant.getAntList().get(indexOptimalAnt).getAntWay()[i];

            }
            lengthWayCurrentOptima = Ant.getAntList().get(indexOptimalAnt).getLengthWay();

            if (lengthWayOptima > lengthWayCurrentOptima) {
                lengthWayOptima =  lengthWayCurrentOptima;
                for (int i = 0; i < ac.getCountColony(); i++){
                    optimaWay[i] = currentOptimaWay[i];
                    NotChangeMinWay = 0;
                }

            }
            NotChangeMinWay++;

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
