package algorithm;
import model.Cell;
import model.Solution;
import util.MatrixUtils;
import java.util.ArrayList;
import java.util.List;
public class GreedySolver {
    public static Solution solve(int[][] A) {
        int n = A.length;
        List<Cell> all = new ArrayList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                all.add(new Cell(i, j));
        all.sort((c1, c2) -> Integer.compare(A[c2.i][c2.j], A[c1.i][c1.j]));
        Solution sol = new Solution();
        for (Cell c : all)
            if (canAdd(sol, c)) sol.add(c, A[c.i][c.j]);
        return sol;
    }
    private static boolean canAdd(Solution sol, Cell c) {
        for (Cell chosen : sol.chosen)
            if (MatrixUtils.isNeighbour(chosen.i, chosen.j, c.i, c.j)) return false;
        return true;
    }
}