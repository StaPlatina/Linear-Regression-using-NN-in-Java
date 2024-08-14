import java.math.BigDecimal;
import java.math.RoundingMode;

public class Mathnn {
    static Double[][] matmult(Double[][] array1, Double[][] array2) {
        int rows1 = array1.length;
        int cols1 = array1[0].length;
        int cols2 = array2[0].length;

        Double[][] result = new Double[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                result[i][j] = 0.0;
                for (int k = 0; k < cols1; k++) {
                    if (array1[i][k] != null && array2[k][j] != null) {
                        result[i][j] = result[i][j] + (array1[i][k]*(array2[k][j]));
                    }
                }
            }
        }

        return result;
    }
    static Double[][] Transpose(Double[][]w){
        Double[][] temp = new Double[w[0].length][w.length];
        for (int i = 0; i < w.length; i++)
            for (int j = 0; j < w[0].length; j++)
                temp[j][i] = w[i][j];
        return temp;
    }

    static Double[][] addb(Double b,Double[][] x){
        Double[][] B = new Double[1][x[0].length];
        for (int i=0; i<x[0].length; i++){
            B[0][i] = (x[0][i]+(b));
        }
        return B;
    }

    static Double[][] SubT(Double[][]A, Double[][]B){
        Double[][] dZ = new Double[1][A[0].length];
        for (int i=0;i<A[0].length;i++){
            dZ[0][i] = A[0][i]-(B[0][i]);
        }
        return dZ;
    }


    static Double[][] CalcL(Double[][]A){
        Double[][] L = new Double[1][A[0].length];
        for (int i=0; i<A[0].length; i++){
            L[0][i] = A[0][i]*(A[0][i]);
        }
        return L;
    }

    static Double Sum (Double[][]L){
        Double c= 0.0;
        for(int i=0; i<L[0].length; i++){
            c = c+(L[0][i]);
        }
        return c;
    }

    static Double[][] CalcdW (Double[][]X, Double[][]dZ){
        Double[][] dW = new Double[X.length][1];
        dW = matmult(X,Transpose(dZ));
        for (int i=0; i<X.length; i++){
            dW[i][0] = dW[i][0]/(X[0].length);
        }
        return dW;
    }

    static Double[][] UpdateW (Double[][]W, Double[][]dW, Double learningrate){
        Double[][] uW = new Double[W.length][1];
        for (int i=0; i<W.length; i++){
            if(W[i][0] != null){
            uW[i][0] = W[i][0]-(learningrate*(dW[i][0]));}
            else{
                uW[i][0] = W[i][0];
            }
        }
        return uW;
    }

    public static Double calculateRMSE(Double[][] actual, Double[][] predicted) {
        // Check if both arrays have the same dimensions
        if (actual.length != predicted.length || actual[0].length != predicted[0].length) {
            throw new IllegalArgumentException("Arrays must have the same dimensions");
        }

        int numRows = actual.length;
        int numCols = actual[0].length;
        double sumSquaredDiff = 0.0;

        // Iterate through the arrays and calculate the squared differences
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                double diff = actual[i][j] - predicted[i][j];
                sumSquaredDiff += diff * diff;
            }
        }

        // Calculate the mean squared difference
        double meanSquaredDiff = sumSquaredDiff / (numRows * numCols);

        // Return the square root of the mean squared difference
        return Math.sqrt(meanSquaredDiff);
    }

}