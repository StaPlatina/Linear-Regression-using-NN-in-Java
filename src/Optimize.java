import java.math.BigDecimal;

public class Optimize {
    static RetrnOptimize Optimize(Double[][] W, Double b, Double[][]X, Double[][] Y, int numiter, Double learningrate){
        Double[] cost = new Double[numiter/100];
        Double best_cost = 1.0;
        Double decay = 0.999;
        Double[][] bestW = new Double[X.length][1];
        Double bestB = 0.0;
        Double[][] dW = new Double[X.length][1];
        Double dB= 0.0,cst;
        for (int i=0; i<numiter; i++){
            RetrnProp Gc = Propagation.Propagation(W,b,X,Y);
            dW = Gc.getdW();
            dB = Gc.getdB();
            cst = Gc.getCost();
            W = Mathnn.UpdateW(W,dW,learningrate);
            b = b - (learningrate*(dB));
            learningrate = learningrate*(decay);
//            if (i%100==0){
//                cost[i/100] = cst;
//            }
            if (cst<(best_cost)){
                best_cost = cst;
                bestW = W;
                bestB = b;
            }
            System.out.println(i+" iteration has passed.");
        }
        System.out.println("Best Cost = "+ best_cost);
        return new RetrnOptimize(W, b, learningrate, bestW, bestB, dW, dB,cost);
    }
}

class RetrnOptimize{
    private Double [][]W;
    private Double b;
    private Double learningrate;
    private Double [][]bestW;
    private Double bestB;
    private Double[][] dW;
    private Double dB;
    private Double[] cost;
    public RetrnOptimize(Double[][]W,Double b, Double learningrate, Double[][] bestW, Double bestB, Double[][] dW, Double dB,Double[] cost){
        this.b = b;
        this.bestB = bestB;
        this.dW = dW;
        this.bestW = bestW;
        this.dB = dB;
        this.learningrate = learningrate;
        this.W = W;
        this.cost = cost;
    }
    public Double[][] getW() { return W; }
    public Double getB(){return b;}
    public Double getLearningrate(){ return learningrate; }
    public Double[][] getBestW(){ return bestW; }
    public Double getBestB(){ return bestB; }
    public Double[][] getdW(){ return dW; }
    public Double getdB() { return dB; }
    public Double[] getCost() { return cost; }
}