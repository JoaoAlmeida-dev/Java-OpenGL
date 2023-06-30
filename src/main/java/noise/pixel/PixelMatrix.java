package noise.pixel;

import java.util.Arrays;

public class PixelMatrix {

    RGB[][] pixelMatrix;
    float xLeft;
    float yTop;
    float xRight;
    float yBot;

    int size = 0;

    public PixelMatrix(RGB[][] pixelMatrix, float xLeft, float xRight, float yTop, float yBot) {

        int maxLength = 0;
        for (int x = 0; x < pixelMatrix.length; x++) {
            for (int y = 0; y < pixelMatrix[x].length; y++) {
                if (maxLength < pixelMatrix[x].length) {
                    maxLength = pixelMatrix[x].length;
                }
                size += 1;
            }
        }
        this.pixelMatrix = new RGB[pixelMatrix.length][maxLength];
        for (int x = 0; x < pixelMatrix.length; x++) {
            for (int y = 0; y < pixelMatrix[x].length; y++) {
                this.pixelMatrix[x][y] = pixelMatrix[x][y];
            }
        }
        this.xLeft = xLeft;
        this.yTop = yTop;
        this.xRight = xRight;
        this.yBot = yBot;
    }

    public PixelMatrix(int size, float xLeft, float xRight, float yTop, float yBot) {
        this.pixelMatrix = new RGB[size][size];
        this.size = size * size;

        this.xLeft = xLeft;
        this.yTop = yTop;
        this.xRight = xRight;
        this.yBot = yBot;
    }

    @Override
    public String toString() {
        return "PixelMatrix{" +
                "xstart=" + xLeft +
                ", ystart=" + yTop +
                ", xend=" + xRight +
                ", yend=" + yBot +
                ", size=" + size +
                ",\n pixelMatrix=" + Arrays.toString(pixelMatrix) +
                '}';
    }

    public PixelMatrixGPUPrep getPreparationGPU() {
        return new PixelMatrixGPUPrep(this);
    }
}
