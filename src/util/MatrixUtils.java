package util;
public class MatrixUtils {
    public static void print(int[][] A) {
        for (int[] row : A) {
            for (int v : row) System.out.printf("%3d ", v);
            System.out.println();
        }
    }
    public static boolean isNeighbour(int i1, int j1, int i2, int j2) {
        return Math.abs(i1 - i2) <= 1 && Math.abs(j1 - j2) <= 1;
    }
}