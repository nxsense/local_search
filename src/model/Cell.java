package model;
import java.util.Objects;
public class Cell {
    public int i;
    public int j;
    public Cell(int i, int j) { this.i = i; this.j = j; }
    @Override public boolean equals(Object o) {
        if (!(o instanceof Cell)) return false;
        Cell c = (Cell) o;
        return i == c.i && j == c.j;
    }
    @Override public int hashCode() { return Objects.hash(i, j); }
    @Override public String toString() { return "(" + i + "," + j + ")"; }
}