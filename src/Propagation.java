import java.math.BigDecimal;
import java.math.RoundingMode;

public class Propagation {
    static RetrnProp Propagation(Double[][] w, Double b, Double[][] x, Double[][] y){
        Double[][] Z = new Double[1][x[0].length];
        Z = Mathnn.matmult(Mathnn.Transpose(w),x);
        Z = Mathnn.addb(b,Z);
        Double [][] dZ = new Double[1][x[0].length];
        dZ = Mathnn.SubT(Z,y);
        Double[][] L = new Double[1][x[0].length];
        L = Mathnn.CalcL(dZ);
        Double cost = Mathnn.Sum(L)/(x[0].length);
        Double[][] dW = new Double[x.length][1];
        dW = Mathnn.CalcdW(x,dZ);
        Double dB = Mathnn.Sum(dZ)/(x[0].length);
        return new RetrnProp(dW,dB,cost);
    }
}

class RetrnProp{
    private Double[][]dW;
    private Double dB;
    private Double cost;
    public RetrnProp(Double[][]dW, Double dB, Double cost){
        this.dW = dW;
        this.dB = dB;
        this.cost = cost;
    }

    public Double[][] getdW(){
        return dW;
    }

    public Double getdB(){
        return dB;
    }

    public Double getCost(){
        return cost;
    }
}
