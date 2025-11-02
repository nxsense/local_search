package util;
import java.util.Random;
public class MatrixGenerator {
    public static int[][] generate(int n, int minVal, int maxVal, long seed) {
        Random rnd = new Random(seed);
        int[][] A = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = minVal + rnd.nextInt(maxVal - minVal + 1);
        return A;
    }
}