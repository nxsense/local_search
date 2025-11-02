package algorithm;
import model.Cell;
import model.Solution;
import util.MatrixUtils;
import java.util.HashSet;
import java.util.Set;
public class LocalSearchSolver {
    public static Solution improve(int[][] A, Solution start, int kMax, double eps) {
        Solution current = start.copy();
        int n = A.length, noImprove = 0;
        for (int iter = 0; iter < kMax; iter++) {
            boolean improved = false; int bestDelta = 0;
            Cell toRemove = null, toAdd = null;
            Set<Cell> chosenSet = new HashSet<>(current.chosen);
            for (Cell chosen : current.chosen) {
                int baseVal = current.value - A[chosen.i][chosen.j];
                for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
                    Cell cand = new Cell(i, j);
                    if (chosenSet.contains(cand)) continue;
                    if (!isValidReplacement(current, chosen, cand)) continue;
                    int delta = baseVal + A[i][j] - current.value;
                    if (delta > bestDelta + eps) {
                        bestDelta = delta; toRemove = chosen; toAdd = cand; improved = true;
                    }
                }
            }
            if (improved) {
                current.chosen.remove(toRemove);
                current.chosen.add(toAdd);
                current.value += bestDelta;
                noImprove = 0;
            } else noImprove++;
            if (noImprove >= 5) break;
        }
        return current;
    }
    private static boolean isValidReplacement(Solution current, Cell remove, Cell cand) {
        for (Cell other : current.chosen) {
            if (other.equals(remove)) continue;
            if (MatrixUtils.isNeighbour(other.i, other.j, cand.i, cand.j)) return false;
        }
        return true;
    }
}