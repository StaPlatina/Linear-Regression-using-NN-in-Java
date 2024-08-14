import java.math.BigDecimal;

public class Model {
    static RetrnModel Model(Double[][] Xtrain, Double[][] Ytrain, Double[][] Xtest, Double[][] Ytest, int numiter, Double learningrate){
        Double[][] W = new Double[Xtrain.length][1];
        Double B = 0.0;
        RetrnOptimize Pgc = Optimize.Optimize(W,B,Xtrain,Ytrain,numiter,learningrate);
        Double[][] bestW = new Double[Xtrain.length][1];
        Double bestB = 0.0;
        Double[][] dW = new Double[Xtrain.length][1];
        Double dB= 0.0;
        Double[] cost;
        W = Pgc.getW();
        B = Pgc.getB();
        bestW = Pgc.getBestW();
        bestB = Pgc.getBestB();
        dW = Pgc.getdW();
        dB = Pgc.getdB();
        learningrate = Pgc.getLearningrate();
        cost = Pgc.getCost();
        Double[][] YpredTrain = Predict.Predict(W,B,Xtrain);
        Double Rmsetrain = Mathnn.calculateRMSE(Ytrain,YpredTrain);
        Double[][] YpredTest = Predict.Predict(W,B,Xtest);
        Double Rmsetest = Mathnn.calculateRMSE(Ytest,YpredTest);
//        Double AccuracyTrain = (100)-(Mathnn.Mean(Mathnn.MulHundo(Mathnn.Absol(Mathnn.SubT(YpredTrain, Ytrain)))));
//        Double AccuracyTest = (100)-(Mathnn.Mean(Mathnn.MulHundo(Mathnn.Absol(Mathnn.SubT(YpredTest, Ytest)))));
        System.out.println("Learning Rate = "+learningrate);
        System.out.println("Training RMSE = "+Rmsetrain);
        System.out.println("Testing RMSE = "+Rmsetest);
        return new RetrnModel(W,B,learningrate,YpredTrain,YpredTest,numiter,cost);
    }
}
class RetrnModel{
    private Double [][]W;
    private Double b;
    private Double learningrate;
    private Double [][]YpredTrain;
    private Double[][] YpredTest;
    private int numiter;
    private Double[] cost;
    public RetrnModel(Double[][]W,Double b, Double learningrate, Double[][]YpredTrain,Double[][]YpredTest,int numiter,Double[] cost){
        this.b = b;
        this.learningrate = learningrate;
        this.W = W;
        this.cost = cost;
        this.YpredTest = YpredTest;
        this.YpredTrain = YpredTrain;
        this.numiter = numiter;
    }
    public Double[][] getW() { return W; }
    public Double getB(){return b;}
    public Double getLearningrate(){ return learningrate; }
    public Double[][] getYpredTrain(){ return YpredTrain; }
    public Double[][] getYpredTest(){ return YpredTest; }
    public int getNumiter(){ return numiter; }
    public Double[] getCost() { return cost; }
}
