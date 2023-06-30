package noise;

import java.util.Random;

public class MatrixNoise {

    public static void main(String[] args) {
        Vec2[][] floats = generateMatrix(3);
        expandMatrix(floats);
    }

    private static Vec2[][] generateMatrix(int size) {
        Random rand = new Random();
        Vec2[][] matrix = new Vec2[size][size];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Vec2(rand.nextFloat(), rand.nextFloat());
            }
        }

        System.out.println("noise Matrix");
        System.out.println(matrixToString(matrix));

        return matrix;
    }

    private static Vec2[][] expandMatrix(Vec2[][] matrix) {
        Random rand = new Random();
        Vec2[][] expandedMatrix = new Vec2[matrix.length * 2 + 1][matrix[0].length * 2 + 1];
        System.out.println("size of new matrix: " + expandedMatrix.length + " - " + expandedMatrix[0].length);


        return matrix;
    }

    private static <T> String matrixToString(T[][] matrix) {
        StringBuilder stringResult = new StringBuilder();
        String separator = " ; ";
        String[] rowSeparator = {"[", "]"};
        stringResult.append(rowSeparator[0]).append("\n");
        for (int i = 0; i < matrix.length; i++) {

            stringResult.append(i).append(":").append(rowSeparator[0]);
            for (int j = 0; j < matrix[i].length; j++) {

                stringResult.append(matrix[i][j]);
                if (j < matrix[i].length - 1)
                    stringResult.append(separator);
            }
            stringResult.append(rowSeparator[1]);
            stringResult.append("\n");
        }
        stringResult.append(rowSeparator[1]);
        return stringResult.toString();

    }
}
