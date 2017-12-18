package logic.antOptimization;

class AntMath {
    static double summArray(double Array[][]) {
        double summ = 0;
        for (double i[]: Array){
            for (double j: i){
                summ += j;
            }
        }
        return summ;
    }

    static double roundTo2Decimal(double x) {
        x = Math.round(x * 1);
        return x /= 1;
    }

    static double minIntValue(int[] Array) {
        double min = Array[0];
        for (int i=1; i<Array.length; i++) {
            if (Array[i] < min) {
                min = Array[i];
            }
        }
        return min;
    }
}
