/*
package noise;

import org.lwjglb.engine.Engine;
import org.lwjglb.engine.IAppLogic;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.graph.Model;
import org.lwjglb.engine.graph.Render;
import org.lwjglb.engine.scene.Entity;
import org.lwjglb.engine.scene.Scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class oneDNoiseViewTest implements IAppLogic {
    private Entity noiseEntity;

    public static void main(String[] args) {
        oneDNoiseViewTest main = new noise.oneDNoiseViewTest();
        Engine gameEng = new Engine("1D Noise", new Window.WindowOptions(), main);
        gameEng.start();
    }

    @Override
    public void cleanup() {
        // Nothing to be done yet
    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        int size = 20;
        Random rand = new Random(1);
        Vec3[] vecPositions = new Vec3[size];

        for (int i = 0; i < size; i++) {
            vecPositions[i] = new Vec3(
                    i * (2.0f / size) - 1
                    , rand.nextFloat() * 2 - 1
                    , -2.f);
        }

        float[] positions = new float[vecPositions.length * 3];
        ArrayList<Integer> indices = new ArrayList<Integer>(vecPositions.length + 1);
        float[] colors = new float[vecPositions.length * 3];

        for (int i = 0; i < size; i++) {
            positions[i * 3] = vecPositions[i].getX();
            positions[i * 3 + 1] = vecPositions[i].getY();
            positions[i * 3 + 2] = vecPositions[i].getZ();
            colors[i * 3] = 0.5f;
            colors[i * 3 + 1] = 0.0f;
            colors[i * 3 + 2] = 0.0f;
        }

        int indicesCounter = 0;
        for (int i = 0; i < size; i++) {
            indices.add(i);
            if (indicesCounter == 2) {
                indicesCounter = 0;
                indices.add(i);
            }
            indicesCounter++;
            System.out.println("i:" + i + ";counter:" + indicesCounter);
            System.out.println(indices);
        }

        int[] indicesArray = indices.stream().mapToInt(i -> i).toArray();

  */
/*      int[] indicesArray = {
                0, 1, 2,
                2, 3, 4,
                4, 5, 6,
                6, 7, 8,
                8, 9, 0
        };*//*


        System.out.println("vecPositions");
        System.out.println(Arrays.toString(vecPositions));
        System.out.println("positions");
        System.out.println(Arrays.toString(positions));
        System.out.println("colors");
        System.out.println(Arrays.toString(colors));
        System.out.println("indices");
        System.out.println(Arrays.toString(indicesArray));


        //scene.addModel("triangle", mesh);

        List<Mesh> meshList = new ArrayList<>();

        Mesh mesh = new Mesh(positions, colors, indicesArray);
        meshList.add(mesh);
        final String modelId = "model";
        Model model = new Model(modelId,meshList);
        scene.addModel(model);
        noiseEntity = new Entity("entity",modelId);
        noiseEntity.setPosition(0,0,-2);
        scene.addEntity(noiseEntity);



    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        // Nothing to be done yet
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
        // Nothing to be done yet
    }
}
*/
