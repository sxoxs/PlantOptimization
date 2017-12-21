package logic.main;


import logic.antOptimization.DataOptimization;
import logic.factory.FactoryParameter;
import logic.factory.Schedule;
import java.util.ArrayList;
import java.util.Arrays;

public class TimeCalculation {

    public void calculationTime(DataOptimization dataOptimization, FactoryParameter factoryParameter){
        ArrayList<int[]> wayArrayList = getWayList(dataOptimization);
        ArrayList<Double> lengthList = new ArrayList<>();
        ArrayList<int[]> wayList = new ArrayList<>();

        Schedule schedule = new Schedule();

        for (int[] way : wayArrayList){
            factoryParameter.setSequenceProduct(way);
            lengthList.add( schedule.calculationAllTime(factoryParameter) );
            wayList.add(way);
            System.out.print("Путь: ");
            System.out.print(Arrays.toString(way));
            System.out.print("/; длинна: ");
            System.out.println(schedule.calculationAllTime(factoryParameter));
        }

        System.out.println("Минимальный путь за всё время");
        double minTime = lengthList.get(0);
        int[] wayMin = wayList.get(0);

        for (int i = 1; i < wayList.size(); i ++){
            if (minTime > lengthList.get(i)) {
                minTime = lengthList.get(i);
                wayMin = wayList.get(i);
            }
        }
        System.out.print("Путь: ");
        System.out.print(Arrays.toString(wayMin));
        System.out.print("/; длинна: ");
        System.out.println(minTime);

        System.out.print("34120: ");
        wayMin = new int[]{3, 4, 1, 2, 0};
        factoryParameter.setSequenceProduct(wayMin);

        System.out.println(schedule.calculationAllTime(factoryParameter));


    }

    private ArrayList<int[]> getWayList (DataOptimization dataOptimization){
        ArrayList<int[]> wayNewList = new ArrayList<>();
        double lengthOptimaWay = dataOptimization.getLengthOptimaWay();
        ArrayList<Double> lengthList = dataOptimization.getLengthOptimaWayList();
        ArrayList<int[]> wayList = dataOptimization.getOptimaWayList();
        wayNewList.add(wayList.get(0));
        boolean isSet = false;

        for (int i = 1; i < wayList.size(); i++) {
            if (isYes(lengthOptimaWay, lengthList.get(i))){
                isSet = true;
                for (int j = 0; j < wayNewList.size(); j++){
                    if (0 == Arrays.compare(wayNewList.get(j), wayList.get(i)) )  {
                        isSet = false;
                    }
                }
                if (isSet) {
                    wayNewList.add(wayList.get(i));
                }
            }
        }

        return wayNewList;
    }

    private boolean isYes (double x, double y) {
        if (0 == Double.compare(x, y)){
            return true;
        }
        else return false;
    }

}
