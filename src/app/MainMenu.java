package app;
import algorithm.GreedySolver;
import algorithm.LocalSearchSolver;
import experiment.ExperimentRunner;
import experiment.ParameterConfig;
import model.Solution;
import util.MatrixFileIO;
import util.MatrixGenerator;
import util.MatrixUtils;
import java.io.IOException;
import java.util.Scanner;
public class MainMenu {
    private static int[][] currentMatrix = null;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===== ГОЛОВНЕ МЕНЮ =====");
            System.out.println("1 - Робота з індивідуальною задачею");
            System.out.println("2 - Експерименти");
            System.out.println("0 - Вихід");
            System.out.print("Ваш вибір: ");
            int c = sc.nextInt(); sc.nextLine();
            if (c == 0) break;
            if (c == 1) individualTaskMenu(sc);
            if (c == 2) experimentsMenu(sc);
        }
        System.out.println("Завершено.");
    }
    private static void individualTaskMenu(Scanner sc) {
        while (true) {
            System.out.println("=== Робота з ІЗ ===");
            System.out.println("1 - Ввести матрицю вручну");
            System.out.println("2 - Згенерувати матрицю випадково");
            System.out.println("3 - Зчитати матрицю з CSV");
            System.out.println("4 - Розв’язати всіма алгоритмами");
            System.out.println("5 - Зберегти матрицю у CSV");
            System.out.println("0 - Назад");
            System.out.print("Ваш вибір: ");
            int c = sc.nextInt(); sc.nextLine();
            if (c == 0) break;
            switch (c) {
                case 1 -> inputMatrixManual(sc);
                case 2 -> generateMatrix(sc);
                case 3 -> readMatrix(sc);
                case 4 -> solveCurrent(sc);
                case 5 -> saveMatrix(sc);
            }
        }
    }
    private static void inputMatrixManual(Scanner sc) {
        System.out.print("Введіть розмір N: ");
        int n = sc.nextInt(); sc.nextLine();
        int[][] A = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = sc.nextInt();
        sc.nextLine();
        currentMatrix = A;
    }
    private static void generateMatrix(Scanner sc) {
        System.out.print("Введіть розмір N: ");
        int n = sc.nextInt(); sc.nextLine();
        currentMatrix = MatrixGenerator.generate(n, 1, 9, System.currentTimeMillis());
        MatrixUtils.print(currentMatrix);
    }
    private static void readMatrix(Scanner sc) {
        System.out.print("Введіть шлях до CSV: ");
        String path = sc.nextLine();
        try { currentMatrix = MatrixFileIO.readMatrixFromCsv(path);
            MatrixUtils.print(currentMatrix);
        } catch (IOException e) { System.out.println("Помилка: " + e.getMessage()); }
    }
    private static void saveMatrix(Scanner sc) {
        if (currentMatrix == null) return;
        System.out.print("Шлях для збереження: ");
        String path = sc.nextLine();
        try { MatrixFileIO.saveMatrixToCsv(currentMatrix, path); }
        catch (IOException e) { System.out.println("Помилка: " + e.getMessage()); }
    }
    private static void solveCurrent(Scanner sc) {
        if (currentMatrix == null) { System.out.println("Немає матриці."); return; }
        System.out.print("k_max: "); int kMax = sc.nextInt();
        System.out.print("eps: "); double eps = sc.nextDouble(); sc.nextLine();
        long t1 = System.nanoTime(); Solution g = GreedySolver.solve(currentMatrix);
        long t2 = System.nanoTime(); double tg = (t2 - t1) / 1_000_000.0;
        long t3 = System.nanoTime(); Solution l = LocalSearchSolver.improve(currentMatrix, g, kMax, eps);
        long t4 = System.nanoTime(); double tl = (t4 - t3) / 1_000_000.0;
        System.out.printf("%-16s | %-12s | %-8s%n", "Алгоритм", "Значення ЦФ", "Час (мс)");
        System.out.printf("%-16s | %-12d | %-8.3f%n", "Жадібний", g.value, tg);
        System.out.printf("%-16s | %-12d | %-8.3f%n", "Локальний пошук", l.value, tl);
        System.out.println("\n=== Деталі рішень ===");
        System.out.println("Жадібний алгоритм:");
        System.out.println(g.toPrettyString());
        System.out.println("Локальний пошук:");
        System.out.println(l.toPrettyString());
    }
    private static void experimentsMenu(Scanner sc) {
        System.out.print("N_min: "); int nMin = sc.nextInt();
        System.out.print("N_max: "); int nMax = sc.nextInt();
        System.out.print("step: "); int step = sc.nextInt();
        System.out.print("розмірність: "); int inst = sc.nextInt();
        System.out.print("k_max: "); int kMax = sc.nextInt();
        System.out.print("eps: "); double eps = sc.nextDouble(); sc.nextLine();
        System.out.print("Файл CSV: "); String path = sc.nextLine();
        ParameterConfig cfg = new ParameterConfig(nMin, nMax, step, inst, kMax, eps);
        try { ExperimentRunner.runAndSaveAll(cfg, path);
            System.out.println("Результати у: " + path);
        } catch (IOException e) { System.out.println("Помилка: " + e.getMessage()); }
    }
}