import java.util.Random;

public class Matrix {
    private int[][] matrix;
    private Random rng = new Random(20);
    private static final int DEFAULT_ROWS = 3;
    private static final int DEFAULT_COLS = 3;
    private static final int MAX_VALUE = 10;

    public Matrix(int rows, int cols) {
        matrix = new int[rows][cols];
        init(rows, cols);
    }

    public Matrix() {
        this(DEFAULT_ROWS, DEFAULT_COLS);
    }
    public Matrix(int[][] array){
        matrix = array;
    }

    private void init(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rng.nextInt(MAX_VALUE);
            }
        }

    }

    public void swapRows(int row1, int row2) {
        row1--;
        row2--;
        int[] temp = new int[matrix[row1].length];

        temp = matrix[row2];
        matrix[row2] = matrix[row1];
        matrix[row1] = temp;
    }

    public void multRow(int row, int multiplier) {
        int index = 0;
        row--;
        for (int i : matrix[row]) {
            matrix[row][index] = i * multiplier;
            index++;
        }
    }

    public void multRowAdd(int row, int multiplier, int targetRow) {
        int index = 0;
        row--;
        targetRow--;
        for (int i : matrix[row]) {
            matrix[targetRow][index] += i * multiplier;
            index++;
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            out.append(java.util.Arrays.toString(matrix[i]) + '\n');
        }
        return out.toString();
    }
    
    public int[][] getMatrix(){
        return matrix;
    }
}