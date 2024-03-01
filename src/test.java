public class test {

    public static void main(String[] args) {

    }

    public static void rotateMatrix180(String[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Crear una matriz temporal para almacenar la matriz original rotada
        String[][] tempMatrix = new String[rows][cols];

        // Rotar la matriz 180 grados
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tempMatrix[i][j] = matrix[rows - 1 - i][cols - 1 - j];
            }
        }

        // Copiar la matriz rotada de vuelta a la matriz original
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = tempMatrix[i][j];
            }
        }
    }

}
