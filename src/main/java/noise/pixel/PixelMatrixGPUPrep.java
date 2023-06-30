package noise.pixel;

import noise.Vec3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PixelMatrixGPUPrep {
    /**
     * offset used for defining the beggining of the drawable area
     */
    private final float xLeft;
    /**
     * offset used for defining the beggining of the drawable area
     */
    private final float yTop;
    /**
     * offset used for defining the end of the drawable area
     */
    private final float xRight;
    /**
     * offset used for defining the end of the drawable area
     */
    private final float yBot;
    private final RGB[][] rawPixelMatrix;
    private final int rawPixelMatrixSize;

    /**
     * Vector containing he positions of vertexes for gpu
     */
    public float[] positions;
    /**
     * Vector containing the indices for gpu
     */
    public int[] indices;
    /**
     * Vector containing colors of each vertex
     */
    public float[] colors;

    public PixelMatrixGPUPrep(PixelMatrix pixelMatrix) {
        this.xLeft = pixelMatrix.xLeft;
        this.xRight = pixelMatrix.xRight;
        this.yTop = pixelMatrix.yTop;
        this.yBot = pixelMatrix.yBot;
        rawPixelMatrix = pixelMatrix.pixelMatrix;
        rawPixelMatrixSize = pixelMatrix.size;
        preparePixelMatrix();
    }

    @Override
    public String toString() {
        return "PixelMatrixGPUPrep{" +
                "xLeft=" + xLeft +
                ", yTop=" + yTop +
                ", xRight=" + xRight +
                ", yBot=" + yBot +
                ",\n rawPixelMatrix=" + Arrays.toString(rawPixelMatrix) +
                ",\n rawPixelMatrixSize=" + rawPixelMatrixSize +
                ",\n positions=" + Arrays.toString(positions) +
                ",\n indices=" + Arrays.toString(indices) +
                ",\n colors=" + Arrays.toString(colors) +
                '}';
    }

    private void preparePixelMatrix() {
        ArrayList<Float> positionsArrayList = new ArrayList<>(rawPixelMatrixSize * 3);
        ArrayList<Integer> indicesArrayList = new ArrayList<>(rawPixelMatrixSize + 1);
        ArrayList<Float> colorsArrayList = new ArrayList<>(rawPixelMatrixSize + 1);


        int numVertexPerPixel = 4;
        float z = -50;

        int currentPixelIndex = 0;
        float pixelSizex = Math.abs(xRight - xLeft) / rawPixelMatrix.length;
        float pixelSizey;
        for (int row = 0; row < rawPixelMatrix.length; row++) {
            pixelSizey = Math.abs(yBot - yTop) / rawPixelMatrix[row].length;
            System.out.println("pixelSizex: " + pixelSizex + "; pixelSizey: " + pixelSizey);
            for (int col = 0; col < rawPixelMatrix[row].length; col++) {
                float xLeft = this.xLeft + (col * pixelSizex);
                float xRight = this.xLeft + ((col + 1) * pixelSizex);
                float yTop = this.yTop - (row * pixelSizey);
                float yBot = this.yTop - ((row + 1) * pixelSizey);
                System.out.println("xLeft: " + xLeft + "; xRight: " + xRight + "; yTop: " + yTop + ",yBot: " + yBot);


                Vec3 topLeftCorner = new Vec3(xLeft, yTop, z);
                Vec3 topRightCorner = new Vec3(xRight, yTop, z);
                Vec3 botLeftCorner = new Vec3(xLeft, yBot, z);
                Vec3 botRightCorner = new Vec3(xRight, yBot, z);

                positionsArrayList.addAll(List.of(botLeftCorner.toArray()));
                positionsArrayList.addAll(List.of(topLeftCorner.toArray()));
                positionsArrayList.addAll(List.of(topRightCorner.toArray()));
                positionsArrayList.addAll(List.of(botRightCorner.toArray()));

                indicesArrayList.add(currentPixelIndex * numVertexPerPixel);
                indicesArrayList.add(currentPixelIndex * numVertexPerPixel + 1);
                indicesArrayList.add(currentPixelIndex * numVertexPerPixel + 2);
                indicesArrayList.add(currentPixelIndex * numVertexPerPixel + 2);
                indicesArrayList.add(currentPixelIndex * numVertexPerPixel + 3);
                indicesArrayList.add(currentPixelIndex * numVertexPerPixel);
                //System.out.println("positionsArrayList" + positionsArrayList);
                //System.out.println("indicesArrayList" + indicesArrayList);

                for (int i = 0; i < 4; i++) {
                    colorsArrayList.add(rawPixelMatrix[row][col].getRedPercentage());
                    colorsArrayList.add(rawPixelMatrix[row][col].getGreenPercentage());
                    colorsArrayList.add(rawPixelMatrix[row][col].getBluePercentage());
                }
                //System.out.println("colorsArrayList" + colorsArrayList);

                currentPixelIndex++;
            }
        }


        int index = 0;
        positions = new float[positionsArrayList.size()];
        for (final Float value : positionsArrayList) {
            positions[index++] = value;
        }
        index = 0;
        indices = new int[indicesArrayList.size()];
        for (final Integer value : indicesArrayList) {
            indices[index++] = value;
        }
        index = 0;
        colors = new float[colorsArrayList.size()];
        for (final Float value : colorsArrayList) {
            colors[index++] = value;
        }

        System.out.print("positions:");
        System.out.println(Arrays.toString(positions));
        System.out.print("indices:");
        System.out.println(Arrays.toString(indices));
        System.out.print("colors:");
        System.out.println(Arrays.toString(colors));
        System.out.println("Length: positions:" + positions.length + "; indices: " + indices.length + " ;colors: " + colors.length);
    }


}
