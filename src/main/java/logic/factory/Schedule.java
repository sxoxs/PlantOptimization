package logic.factory;

public class Schedule {
    private double[] beginTimeWorkApparatsArray;
    private double[] endTimeWorkApparatsArray;
    private double[][] productTimeArray;

    public double calculationAllTime(FactoryParameter inFactory) {

        getShedule(inFactory);
        double time = endTimeWorkApparatsArray[0];
        for (int i = 1; i < endTimeWorkApparatsArray.length; i++){
            if (time < endTimeWorkApparatsArray[i]) {
                time = endTimeWorkApparatsArray[i];
            }
        }

        return time;
    }
    public double wayTimeCalculation (FactoryParameter inFactory, int firstProduct, int secondProduct){
        double time = 0;
        int[] seqProduct = {firstProduct, secondProduct};
        inFactory.setSequenceProduct(seqProduct);
        getShedule(inFactory);
        time = productTimeArray[1][1] - productTimeArray[0][1];
//        time = productTimeArray[1][1];

        return time;
    }

    private void getShedule(FactoryParameter factory) {

        initializationArrays(factory);
        int productIndex = 0;
        int nowProductIndex = factory.getSequenceProduct()[productIndex];
        int lastProductIndex = 0;
        int nowApparatIndex = 0;
        int lastApparatIndex = 0;
        boolean isFirstApparat = true;

        for (int i = 0; i < productTimeArray.length; i++){
            productTimeArray[i][0] = 0;
            productTimeArray[i][1] = 0;
        }

        for (int i = 0; i < factory.apparatCount; i++){
            beginTimeWorkApparatsArray[i] = 0;
            endTimeWorkApparatsArray[i] = 0;
        }


        for (int apparat = 0; apparat < factory.apparatCount; apparat++){
            // TODO: 18/12/17 say yes 
            if (0 != (factory.getApparatScheduleArray()[nowProductIndex][apparat]) ){

                if (isFirstApparat) {
                    nowApparatIndex = factory.getApparatScheduleArray()[nowProductIndex][apparat] - 1;
                    endTimeWorkApparatsArray[nowApparatIndex] += factory.getProductTimeReleaseArray()[nowProductIndex][nowApparatIndex];
                    productTimeArray[productIndex][0] = 0;
                    isFirstApparat = false;

//                    System.out.println("продукт " + (nowProductIndex+1) + "начало: " + productTimeArray[productIndex][0]);
                }
                else {

                    nowApparatIndex = factory.getApparatScheduleArray()[nowProductIndex][apparat] - 1;
                    lastApparatIndex = factory.getApparatScheduleArray()[nowProductIndex][(apparat-1)] - 1;

                    beginTimeWorkApparatsArray[nowApparatIndex] = endTimeWorkApparatsArray[lastApparatIndex];
                    endTimeWorkApparatsArray[nowApparatIndex] = beginTimeWorkApparatsArray[nowApparatIndex] + factory.getProductTimeReleaseArray()[nowProductIndex][nowApparatIndex];

                }

//                System.out.println("продукт" + (nowProductIndex+1) + " аппарат " + (nowApparatIndex+1) + ": " + beginTimeWorkApparatsArray[nowApparatIndex]);
//                System.out.println("продукт" + (nowProductIndex+1) + " аппарат " + (nowApparatIndex+1) + ": " + endTimeWorkApparatsArray[nowApparatIndex]);

            }
        }
        productTimeArray[productIndex][1] = endTimeWorkApparatsArray[nowApparatIndex];


//        System.out.println("продукт " + (nowProductIndex+1) + "конец : " + productTimeArray[productIndex][1]);

        double tempBeginTime = 0;

        productIndex++;
        for (; productIndex < factory.getSequenceProduct().length; productIndex++) {

            nowProductIndex = factory.getSequenceProduct()[productIndex];
            lastProductIndex = factory.getSequenceProduct()[productIndex - 1];
            isFirstApparat = true;

            for (int apparat = 0; apparat < factory.apparatCount; apparat++) {

                if (0 != (factory.getApparatScheduleArray()[nowProductIndex][apparat])) {

                    if (isFirstApparat) {
                        nowApparatIndex = factory.getApparatScheduleArray()[nowProductIndex][apparat] - 1;
                        beginTimeWorkApparatsArray[nowApparatIndex] = endTimeWorkApparatsArray[nowApparatIndex] +
                                factory.getChangeoverListArray().get(nowApparatIndex)[lastProductIndex][nowProductIndex];

                        endTimeWorkApparatsArray[nowApparatIndex] = beginTimeWorkApparatsArray[nowApparatIndex] +
                                factory.getProductTimeReleaseArray()[nowProductIndex][nowApparatIndex];

                        productTimeArray[productIndex][0] = beginTimeWorkApparatsArray[nowApparatIndex];
                        isFirstApparat = false;

//                        System.out.println("продукт " + (nowProductIndex+1) + "начало: " + productTimeArray[productIndex][0]);

                    } else {
                        nowApparatIndex = factory.getApparatScheduleArray()[nowProductIndex][apparat] - 1;
                        lastApparatIndex = factory.getApparatScheduleArray()[nowProductIndex][(apparat - 1)] - 1;

                        tempBeginTime = endTimeWorkApparatsArray[nowApparatIndex] +
                                factory.getChangeoverListArray().get(nowApparatIndex)[lastProductIndex][nowProductIndex];


                        if (tempBeginTime > endTimeWorkApparatsArray[lastApparatIndex]) {
                            beginTimeWorkApparatsArray[nowApparatIndex] = tempBeginTime;
                        } else {
                            beginTimeWorkApparatsArray[nowApparatIndex] = endTimeWorkApparatsArray[lastApparatIndex];
                        }

                        endTimeWorkApparatsArray[nowApparatIndex] = beginTimeWorkApparatsArray[nowApparatIndex] +
                                factory.getProductTimeReleaseArray()[nowProductIndex][nowApparatIndex];
                    }
                }

//                System.out.println("продукт" + (nowProductIndex+1) + " аппарат " + (nowApparatIndex+1) + ": " + beginTimeWorkApparatsArray[nowApparatIndex]);
//                System.out.println("продукт" + (nowProductIndex+1) + " аппарат " + (nowApparatIndex+1) + ": " + endTimeWorkApparatsArray[nowApparatIndex]);


            }//apparat

            productTimeArray[productIndex][1] = endTimeWorkApparatsArray[nowApparatIndex];


//            System.out.println("продукт " + (nowProductIndex+1) + "конец : " + productTimeArray[productIndex][1]);

        }//productIndex

        return;
    }

    private int getIndex(int[] array, int value) {
        int index = 0;
        for (int element : array) {
            if (value == element) {
                return index;
            }
            index++;
        }
        return (index-1);
    }

    private void initializationArrays(FactoryParameter fp) {
        int productCount = fp.getSequenceProduct().length;

        this.beginTimeWorkApparatsArray = new double[fp.apparatCount];
        this.endTimeWorkApparatsArray = new double[fp.apparatCount];

        for (int i = 0; i < fp.apparatCount; i++) {
            this.beginTimeWorkApparatsArray[i] = 0;
            this.endTimeWorkApparatsArray[i] = 0;
        }

        this.productTimeArray = new double[productCount][];
        for (int i = 0; i < productCount; i++){
            this.productTimeArray[i] = new double[2];
        }
    }
}
