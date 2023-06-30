package noise.pixel;

import noise.oneDNoiseViewTest;
import org.lwjglb.engine.Engine;
import org.lwjglb.engine.IAppLogic;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.graph.Render;
import org.lwjglb.engine.scene.Scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PixelMain implements IAppLogic {

    public static void main(String[] args) {
        PixelMain main = new noise.pixel.PixelMain();
        Engine gameEng = new Engine("Pixel GL", new Window.WindowOptions(500, 500), main);
        gameEng.start();
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        int size = 20;
        RGB[][] rgb_data = new RGB[size][size];
        float rgbDivision = 256.f / size;
        Random rand = new Random(1);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int greyscale = rand.nextInt(256);
                //rgb_data[i][j] = new RGB(greyscale, greyscale, greyscale);
                //rgb_data[i][j] = new RGB((int) (i * rgbDivision), (int) (j * rgbDivision), (int) (rgbDivision));
                rgb_data[i][j] = new RGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

            }
        }
        //System.out.println(Arrays.deepToString(rgb_data));

        PixelMatrix pixelMatrix = new PixelMatrix(rgb_data, -size, size, size, -size);
        //System.out.println();
        //System.out.println(pixelMatrix);
        PixelMatrixGPUPrep preparationGPU = pixelMatrix.getPreparationGPU();
//        System.out.println();
        //System.out.println(preparationGPU);
        Mesh mesh = new Mesh(preparationGPU.positions, preparationGPU.colors, preparationGPU.indices);
        scene.addMesh("triangle", mesh);
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {

    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {

    }
}
