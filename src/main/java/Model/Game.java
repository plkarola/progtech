package Model;

/**
 *
 * @author plkar
 */

public class Game {
    private int matrix[][];
    private int id;
    private Difficulty diff;

    public Game(int[][] matrix, int id, Difficulty diff) {
        this.matrix = matrix;
        this.id = id;
        this.diff = diff;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
    }
    
    public void printMatrix() {
        for (int i =0; i< 9; i++) {
            for (int j = 0; j< 9; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
