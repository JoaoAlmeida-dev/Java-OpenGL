package org.lwjglb.game;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.lwjglb.engine.IAppLogic;
import org.lwjglb.engine.IGuiInstance;
import org.lwjglb.engine.MouseInput;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.graph.Material;
import org.lwjglb.engine.graph.Model;
import org.lwjglb.engine.graph.Render;
import org.lwjglb.engine.graph.Texture;
import org.lwjglb.engine.scene.Camera;
import org.lwjglb.engine.scene.Entity;
import org.lwjglb.engine.scene.ModelLoader;
import org.lwjglb.engine.scene.Scene;
import org.lwjglb.game.world.Block;
import org.lwjglb.game.world.Chunk;
import org.lwjglb.game.world.ChunkMesh;
import org.lwjglb.log.Logger;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class AppLogic implements IAppLogic {
    private static final float MOUSE_SENSITIVITY = 0.1f;
    private static final float BASE_MOVEMENT_SPEED = 0.005f;
    private Entity cubeEntity;
    private Vector4f displInc = new Vector4f();
    private float rotation;
    private IGuiInstance iGuiInstance = new Gui();

    @Override
    public void cleanup() {
        // Nothing to be done yet
    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        Logger.debug("IAppLogic::init::init");


        Model cubeModel = ModelLoader.loadModel("cube-model", "src/main/resources/models/cube/cube.obj",
                scene.getTextureCache());
        scene.addModel(cubeModel);

        cubeEntity = new Entity("cube-entity", cubeModel.getId());
        cubeEntity.setPosition(0, 0, -2);
        scene.addEntity(cubeEntity);

        scene.setGuiInstance(iGuiInstance);
        Texture texture = scene.getTextureCache().createTexture("src/main/resources/models/cube/cube.png");

        List<Block> blockList = new ArrayList<>(
                asList(new Block(0, 0, 0, Block.TYPE.GRASS), new Block(1, 1, 0, Block.TYPE.GRASS))
        );
        int chunksize = 5;
        for (int i = 0; i < chunksize; i++) {
            for (int j = 0; j < chunksize; j++) {
                for (int k = 0; k < chunksize; k++) {
                    blockList.add(new Block(i, j, k, Block.TYPE.GRASS));
                }
            }

        }

        Chunk chunk = new Chunk(blockList, new Vector3f(0, 0, -2));
        ChunkMesh chunkMesh = new ChunkMesh(chunk);
        Model chunkModel = new Model("chunkModel", new ArrayList<>(List.of(new Material(chunkMesh.mesh, texture.getTexturePath()))));
        scene.addModel(chunkModel);
        scene.addEntity(new Entity("chunkEntity", chunkModel.getId()));
    }


    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
        rotation += .5;
        if (rotation > 360) {
            rotation = 0;
        }
        cubeEntity.setRotation(1, 1, 1, (float) Math.toRadians(rotation));
        cubeEntity.updateModelMatrix();
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis, boolean inputConsumed) {
        if (inputConsumed) {
            return;
        }
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
        Vector3f entityPos = cubeEntity.getPosition();
        cubeEntity.setPosition(displInc.x + entityPos.x, displInc.y + entityPos.y, displInc.z + entityPos.z);
        cubeEntity.setScale(cubeEntity.getScale() + displInc.w);
        cubeEntity.updateModelMatrix();
    }

}
