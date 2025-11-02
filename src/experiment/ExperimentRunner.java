package experiment;
import algorithm.GreedySolver;
import algorithm.LocalSearchSolver;
import model.Solution;
import util.MatrixGenerator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class ExperimentRunner {
    public static void runAndSaveAll(ParameterConfig cfg, String csvPath) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvPath))) {
            pw.println("N;instance;alg;z;time_ms");
            for (int n = cfg.nMin; n <= cfg.nMax; n += cfg.step)
                for (int inst = 1; inst <= cfg.instancesPerN; inst++) {
                    int[][] A = MatrixGenerator.generate(n, 1, 9, 1000L + n * 100 + inst);
                    long t1 = System.nanoTime();
                    Solution g = GreedySolver.solve(A);
                    long t2 = System.nanoTime();
                    double tG = (t2 - t1) / 1_000_000.0;
                    pw.printf("%d;%d;%s;%d;%.3f%n", n, inst, "greedy", g.value, tG);
                    long t3 = System.nanoTime();
                    Solution l = LocalSearchSolver.improve(A, g, cfg.kMax, cfg.eps);
                    long t4 = System.nanoTime();
                    double tL = (t4 - t3) / 1_000_000.0;
                    pw.printf("%d;%d;%s;%d;%.3f%n", n, inst, "local", l.value, tL);
                }
        }
    }
}