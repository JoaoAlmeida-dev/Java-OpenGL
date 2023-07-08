package noise.pixel;

import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.lwjglb.engine.Engine;
import org.lwjglb.engine.IAppLogic;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.graph.Model;
import org.lwjglb.engine.graph.Render;
import org.lwjglb.engine.scene.Entity;
import org.lwjglb.engine.scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PixelMain implements IAppLogic {
    private Entity pixelEntity;
    private Vector4f displInc = new Vector4f();

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
        //scene.addModel("triangle", mesh);

        List<Mesh> meshList = new ArrayList<>();

        Mesh mesh = new Mesh(preparationGPU.positions, preparationGPU.colors, preparationGPU.indices);
        meshList.add(mesh);
        final String modelId = "model";
        Model model = new Model(modelId,meshList);
        scene.addModel(model);
        pixelEntity = new Entity("entity",modelId);
        pixelEntity.setPosition(0,0,-2);
        scene.addEntity(pixelEntity);


    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        displInc.zero();
        if(window.isKeyPressed(GLFW.GLFW_KEY_UP)){
            displInc.y=1;
        }else if(window.isKeyPressed(GLFW.GLFW_KEY_DOWN)){
            displInc.y=-1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT)) {
            displInc.x = -1;
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_RIGHT)) {
            displInc.x = 1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_A)) {
            displInc.z = -1;
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_Q)) {
            displInc.z = 1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_Z)) {
            displInc.w = -1;
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_X)) {
            displInc.w = 1;
        }


        displInc.mul(diffTimeMillis/1000.0f);
        Vector3f entityPos = pixelEntity.getPosition();
        pixelEntity.setPosition(displInc.x  +entityPos.x, displInc.y  +entityPos.y, displInc.z  +entityPos.z);
        pixelEntity.setScale(pixelEntity.getScale()+displInc.w);
        pixelEntity.updateModelMatrix();

    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {

    }
}
