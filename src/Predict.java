import java.math.BigDecimal;

public class Predict {
    static Double[][] Predict(Double[][]W, Double b, Double[][]X){
        Double [][] Z = new Double[1][X[0].length];
        Z = Mathnn.matmult(Mathnn.Transpose(W),X);
        Z = Mathnn.addb(b,Z);
        return Z;
    }
}
