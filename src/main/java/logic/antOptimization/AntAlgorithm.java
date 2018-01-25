package logic.antOptimization;

import logic.factory.FactoryParameter;
import java.io.IOException;
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
        for (int i = 0; i < antAlgoritmParam.getCountAnt(); i++){
            Ant.addAnt( new Ant( (int) (Math.random() * ac.getCountColony()),  antAlgoritmParam, ac, fParam));
        }
    }

    private DataOptimization runAntAlgorithm(ParameterAntOptimization parametrs, AntColony ac, DataOptimization inputData, FactoryParameter fp) throws IOException  {

        int indexOptimalAnt = Ant.getIngexMinimalLengthWay(Ant.getAntList());

        double lengthWayCurrentOptima = Ant.getAntList().get(indexOptimalAnt).getLengthWay();
        double lengthWayOptima = lengthWayCurrentOptima;

        int[] currentOptimaWay = new int[ac.getCountColony()];
        int[] optimaWay = new int[ac.getCountColony()];

        for (int i = 0; i < optimaWay.length; i++){
            currentOptimaWay[i] = Ant.getAntList().get(indexOptimalAnt).getAntWay()[i];
            optimaWay[i] = currentOptimaWay[i];
        }

        int CurrentEra = 1;
        int NotChangeMinWay = 0;

        Date date = new Date();
        long timeOptimization = date.getTime();
        int countAnt = Ant.getAntList().size();

        inputData.addOptimaWay(currentOptimaWay);
        inputData.addLengthOptimaWay(lengthWayCurrentOptima);

        do {
            parametrs.changePferomoneOnWay(ac);
            parametrs.changeProbabityTransitionInColony();

            for (int ant = 0; ant < countAnt; ant++){
                Ant.getAntList().get(ant).changeWay(parametrs, ac, fp);
            }

            indexOptimalAnt = Ant.getIngexMinimalLengthWay(Ant.getAntList());

            for (int i = 0; i < currentOptimaWay.length; i++){
                currentOptimaWay[i] = Ant.getAntList().get(indexOptimalAnt).getAntWay()[i];

            }

            lengthWayCurrentOptima = Ant.getAntList().get(indexOptimalAnt).getLengthWay();


            if (lengthWayOptima > lengthWayCurrentOptima) {
                lengthWayOptima =  lengthWayCurrentOptima;
                for (int i = 0; i < optimaWay.length; i++){
                    optimaWay[i] = currentOptimaWay[i];
                    NotChangeMinWay = 0;
                }
            }
            else {
                NotChangeMinWay++;
            }

            inputData.addOptimaWay(optimaWay);
            inputData.addLengthOptimaWay(lengthWayOptima);

        } while ((++CurrentEra <= parametrs.getMaxCountEra())&(NotChangeMinWay < 1000));

        Date date2 = new Date();
        timeOptimization -= date2.getTime();
        System.out.println("Алгоритм работал:  " + (timeOptimization * (-1)) + " мс");
        System.out.println("Эпох пройдено : " + --CurrentEra);
        if (1000 == NotChangeMinWay){
            System.out.println("На протяжении 1000 эпох путь не улучшался, алгоритм закончен");
        }
        else{
            System.out.println("Домтигнут лимит эпох, алгоритм закончен");
        }

        inputData.setOptimaWay(optimaWay);
        inputData.setLengthOptimaWay(lengthWayOptima);
        inputData.setTimeOptimization(timeOptimization);
        return inputData;
    }

    private static void outInConsoleDataAntOptimization(DataOptimization dataOut) throws IOException {
        System.out.println();
        System.out.println("Результат работы АСО: ");
        System.out.println();
        System.out.println("Лучший путь за все эпохи: ");
        System.out.println(Arrays.toString(dataOut.getOptimaWay()));

        System.out.print("Длина оптимального пути за все эпохи: ");
        System.out.println(dataOut.getLengthOptimaWay());
    }



}
