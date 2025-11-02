package model;
import java.util.ArrayList;
import java.util.List;
public class Solution {
    public List<Cell> chosen = new ArrayList<>();
    public int value;
    public void add(Cell c, int weight) { chosen.add(c); value += weight; }
    public Solution copy() {
        Solution s = new Solution();
        s.value = this.value;
        s.chosen = new ArrayList<>(this.chosen);
        return s;
    }
    public String toPrettyString() {
        StringBuilder sb = new StringBuilder();
        sb.append("z = ").append(value).append("\n");
        sb.append("Обрані клітинки:\n");
        for (Cell c : chosen) sb.append("  ").append(c).append("\n");
        return sb.toString();
    }
}