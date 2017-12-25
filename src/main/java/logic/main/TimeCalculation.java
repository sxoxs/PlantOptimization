package logic.main;


import logic.antOptimization.DataOptimization;
import logic.factory.FactoryParameter;
import logic.factory.Schedule;

import java.util.Arrays;

public class TimeCalculation {

    public void calculationTime(DataOptimization dataOptimization, FactoryParameter factoryParameter){

        Schedule schedule = new Schedule();




        int[] wayMin = new int[factoryParameter.getProductCount()];

        for (int i = 0; i < wayMin.length; i++){
            wayMin[i] = dataOptimization.getOptimaWayList().get(0)[i];
        }


        for (int i = 1; i < dataOptimization.getOptimaWayList().size(); i ++){
            if (dataOptimization.getLengthOptimaWay() == dataOptimization.getLengthOptimaWayList().get(i)) {

                for (int j = 0; j < wayMin.length; j++){
                    wayMin[j] = dataOptimization.getOptimaWayList().get(i)[j];
                }
            }
        }

        System.out.print("Путь: ");
        System.out.print(Arrays.toString(wayMin));
        System.out.print("/; длинна: ");
        factoryParameter.setSequenceProduct(wayMin);
        System.out.println(schedule.calculationAllTime(factoryParameter));

        System.out.print("34120: ");
        wayMin = new int[]{3, 4, 1, 2, 0};
        factoryParameter.setSequenceProduct(wayMin);
        System.out.println(schedule.calculationAllTime(factoryParameter));

        System.out.print("42301: ");
        wayMin = new int[]{4, 1, 2, 0, 3};
        factoryParameter.setSequenceProduct(wayMin);
        System.out.println(schedule.calculationAllTime(factoryParameter));

        System.out.print("03412: ");
        wayMin = new int[]{0, 3, 4, 1, 2};
        factoryParameter.setSequenceProduct(wayMin);
        System.out.println(schedule.calculationAllTime(factoryParameter));

    }

}
