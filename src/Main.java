import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RetrnData train = PreProccessing.PreProcessTrain();
        Double[][] Xtrain = train.getXtrain();
        Double[][] Ytrain = train.getYtrain();
        Double[][] Xtest = train.getXtest();
        Double[][] Ytest = train.getYtest();
        Double[] Xmean = train.getXMeanScaler();
        Double[] Ymean = train.getYMeanScaler();
        Double[] Xstd = train.getXStdDev();
        Double[] Ystd = train.getYStdDev();
        RetrnModel model = Model.Model(Xtrain,Ytrain,Xtest,Ytest,10000, 0.01);
        Double[][] W = model.getW();
        Double B = model.getB();
        while(true) {
            Double[][] Xinput = new Double[4][1];
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Current Density: ");
            Xinput[0][0] = sc.nextDouble();
            System.out.println("Enter Power Density: ");
            Xinput[1][0] = sc.nextDouble();
            System.out.println("Enter Pressure: ");
            Xinput[2][0] = sc.nextDouble();
            System.out.println("Enter Relative Humidity: ");
            Xinput[3][0] = sc.nextDouble();
            for(int i=0;i<4;i++){
                Xinput[i][0] = (Xinput[i][0]-(Xmean[i]))/(Xstd[i]);
            }
            Double[][] Y = Predict.Predict(W, B, Xinput);
            Y[0][0] = (Y[0][0]*(Ystd[0]))+(Ymean[0]);
            System.out.println("Predicted Cell Voltage: " + Y[0][0]);
        }
    }
}