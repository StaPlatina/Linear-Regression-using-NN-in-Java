import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class PreProccessing {
    static RetrnData PreProcessTrain(){
        String filePath = "Training.csv";
        Double[][]Xtrain = new Double[4][90];
        Double[][]Ytrain = new Double[1][90];
        Double[][]Xtest = new Double[4][10];
        Double[][]Ytest = new Double[1][10];
        Double[][]X = new Double[4][100];
        Double[][]Y = new Double[1][100];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath),1024*1024)) {
            String line;
            int i=0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                double value = Double.parseDouble(values[0]);
                X[0][i] = value;
                double value1 = Double.parseDouble(values[1]);
                Y[0][i] = (value1);
                double value2 = Double.parseDouble(values[2]);
                X[1][i] = (value2);
                double value3 = Double.parseDouble(values[3]);
                X[2][i] = (value3);
                double value4 = Double.parseDouble(values[4]);
                X[3][i] = (value4);
                i = i+1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        RetrnData1 Sms = standardizeRows(X);
        X = Sms.getXtrain();
        Double[] Xmeansc = Sms.getMeanScaler();
        Double[] Xstdev = Sms.getStdDev();
        RetrnData1 Sms1 = standardizeRows(Y);
        Y = Sms1.getXtrain();
        Double[] Ymeansc = Sms1.getMeanScaler();
        Double[] Ystddev = Sms1.getStdDev();
        for(int i=0; i<90;i++){
            Xtrain[0][i] = X[0][i];
            Xtrain[1][i] = X[1][i];
            Xtrain[2][i] = X[2][i];
            Xtrain[3][i] = X[3][i];
            Ytrain[0][i] = Y[0][i];
        }
        for(int j=0; j<10;j++){
            Xtest[0][j] = X[0][j+90];
            Xtest[1][j] = X[1][j+90];
            Xtest[2][j] = X[2][j+90];
            Xtest[3][j] = X[3][j+90];
            Ytest[0][j] = Y[0][
                    j+90];
        }
        printDoubleArray(Xtrain);
        System.out.println();
        printDoubleArray(Ytrain);
        System.out.println();
        printDoubleArray(Xtest);
        System.out.println();
        printDoubleArray(Ytest);
        return new RetrnData(Xtrain,Ytrain,Xtest,Ytest,Xmeansc,Xstdev,Ymeansc,Ystddev);
    }

    static void printDoubleArray(Double[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }

    public static RetrnData1 standardizeRows(Double[][] array) {
        Double[][] standardizedArray = new Double[array.length][array[0].length];
        Double[] MeanScaler = new Double[array.length];
        Double[] StdDev = new Double[array.length];

        for (int i = 0; i < array.length; i++) {
            Double[] row = array[i];
            Double mean = calculateMean(row);
            MeanScaler[i] = mean;
            Double stdDev = calculateStandardDeviation(row, mean);
            StdDev[i] = stdDev;

            for (int j = 0; j < row.length; j++) {
                standardizedArray[i][j] = (row[j]-(mean))/(stdDev);
            }
        }
        return new RetrnData1(standardizedArray,MeanScaler,StdDev);
    }

    private static Double calculateMean(Double[] row) {
        Double sum = 0.0;
        for (Double element : row) {
            sum = sum+(element);
        }
        return sum/(row.length);
    }

    private static Double calculateStandardDeviation(Double[] row, Double mean) {
        Double sumSquaredDiff = 0.0;
        for (Double element : row) {
            Double diff = element-(mean);
            sumSquaredDiff = sumSquaredDiff+(diff*(diff));
        }
        Double variance = sumSquaredDiff/(row.length);
        return (Math.sqrt(variance.doubleValue()));
    }

}

class RetrnData{
    private Double[][] Xtrain;
    private Double[][] Ytrain;
    private Double[][] Xtest;
    private Double[][] Ytest;
    private Double[] XMeanScaler;
    private Double[] XStdDev;
    private Double[] YMeanScaler;
    private Double[] YStdDev;
    public RetrnData(Double[][]Xtrain, Double[][]Ytrain, Double[][] Xtest, Double[][] Ytest, Double[] XMeanScaler, Double[] XStdDev,Double[] YMeanScaler, Double[] YStdDev){
        this.Xtrain = Xtrain;
        this.Ytrain = Ytrain;
        this.Xtest = Xtest;
        this.Ytest = Ytest;
        this.XMeanScaler = XMeanScaler;
        this.XStdDev = XStdDev;
        this.YMeanScaler = YMeanScaler;
        this.YStdDev = YStdDev;
    }

    public Double[][] getXtrain() {
        return Xtrain;
    }

    public Double[][] getYtrain() {
        return Ytrain;
    }

    public Double[][] getXtest(){ return Xtest;}
    public Double[][] getYtest(){return Ytest;}
    public Double[] getXMeanScaler(){return XMeanScaler;}
    public Double[] getXStdDev() {return XStdDev;}
    public Double[] getYMeanScaler(){return YMeanScaler;}
    public Double[] getYStdDev() {return YStdDev;}
}

class RetrnData1{
private Double[][] Xtrain;
private Double[] MeanScaler;
private Double[] StdDev;
public RetrnData1(Double[][]Xtrain, Double[] MeanScaler, Double[] StdDev){
    this.Xtrain = Xtrain;
    this.MeanScaler = MeanScaler;
    this.StdDev = StdDev;
}

public Double[][] getXtrain() {
    return Xtrain;
}

public Double[] getMeanScaler(){return MeanScaler;}
public Double[] getStdDev() {return StdDev;}
}
