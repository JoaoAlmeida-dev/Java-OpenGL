package noise.pixel;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import noise.oneDNoiseViewTest;
import org.jetbrains.annotations.NotNull;
import org.lwjglb.engine.Engine;
import org.lwjglb.engine.IAppLogic;
import org.lwjglb.engine.MouseInput;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.graph.Model;
import org.lwjglb.engine.graph.Render;
import org.lwjglb.engine.scene.Camera;
import org.lwjglb.engine.scene.Entity;
import org.lwjglb.engine.scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class PixelMain implements IAppLogic {

    private static final float MOUSE_SENSITIVITY = 0.1f;
    private static final float BASE_MOVEMENT_SPEED = 0.01f;

    private Entity pixelEntity;
    private Vector4f displInc = new Vector4f();

    public static void main(String[] args) {
        PixelMain main = new noise.pixel.PixelMain();
        Engine gameEng = new Engine("Pixel GL", new Window.WindowOptions(500, 500), main);
        gameEng.start();
    }

    private static RGB[][] getRgbs(int size, SingleArgInterface createRGB) {
        RGB[][] rgb_data = new RGB[size][size];
        float rgbDivision = 256.f / size;
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rgb_data[i][j] = new RGB(createRGB.operation(), createRGB.operation(), createRGB.operation());
            }
        }
        return rgb_data;
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        int size = 23;

        Random rand = new Random(1);
        RGB[][] rgb_data = getRgbs(size, () -> rand.nextInt(256));

        PixelMatrix pixelMatrix = new PixelMatrix(rgb_data, -size, size, size, -size);
        PixelMatrixGPUPrep preparationGPU = pixelMatrix.getPreparationGPU();

        List<Mesh> meshList = new ArrayList<>();

        Mesh mesh = new Mesh(preparationGPU.positions, preparationGPU.colors, preparationGPU.indices);
        meshList.add(mesh);
        final String modelId = "model";
        Model model = new Model(modelId, meshList);
        scene.addModel(model);
        pixelEntity = new Entity("entity", modelId);
        pixelEntity.setPosition(0, 0, -2);
        scene.addEntity(pixelEntity);


    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        float movementSpeed = BASE_MOVEMENT_SPEED;
        displInc.zero();
        if (window.isKeyPressed(GLFW.GLFW_KEY_UP)) {
            displInc.y = 1;
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
            displInc.y = -1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT)) {
            displInc.x = -1;
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_RIGHT)) {
            displInc.x = 1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_E)) {
            displInc.z = -1;
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_Q)) {
            displInc.z = 1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_Z)) {
            displInc.w = -1;
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_X)) {
            displInc.w = 1;
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            movementSpeed *= 3;
        }
        float move = diffTimeMillis * movementSpeed;
        Camera camera = scene.getCamera();
        if (window.isKeyPressed(GLFW.GLFW_KEY_W)) {
            camera.moveForward(move);
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_S)) {
            camera.moveBackwards(move);
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_A)) {
            camera.moveLeft(move);
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_D)) {
            camera.moveRight(move);
        }
        if (window.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
            camera.moveUp(move);
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT_CONTROL)) {
            camera.moveDown(move);
        }


        MouseInput mouseInput = window.getMouseInput();
        if (mouseInput.isRightButtonPressed()) {
            Vector2f displVec = mouseInput.getDisplVec();
            camera.addRotation((float) Math.toRadians(-displVec.x * MOUSE_SENSITIVITY),
                    (float) Math.toRadians(-displVec.y * MOUSE_SENSITIVITY));
        }


        displInc.mul(diffTimeMillis / 1000.0f);
        Vector3f entityPos = pixelEntity.getPosition();
        pixelEntity.setPosition(displInc.x + entityPos.x, displInc.y + entityPos.y, displInc.z + entityPos.z);
        pixelEntity.setScale(pixelEntity.getScale() + displInc.w);
        pixelEntity.updateModelMatrix();


    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {

    }

    interface SingleArgInterface {
        public int operation();
    }
}
