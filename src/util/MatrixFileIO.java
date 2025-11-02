package util;
import java.io.*;
public class MatrixFileIO {
    public static void saveMatrixToCsv(int[][] A, String path) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            int n = A.length; pw.println(n);
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(A[i][j]);
                    if (j < n - 1) sb.append(";");
                }
                pw.println(sb);
            }
        }
    }
    public static int[][] readMatrixFromCsv(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int n = Integer.parseInt(br.readLine().trim());
            int[][] A = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] parts = br.readLine().split(";");
                for (int j = 0; j < n; j++)
                    A[i][j] = Integer.parseInt(parts[j].trim());
            }
            return A;
        }
    }
}