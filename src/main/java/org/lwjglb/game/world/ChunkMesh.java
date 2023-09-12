package org.lwjglb.game.world;

import org.joml.Vector3f;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.graph.MeshFactory;
import org.lwjglb.log.Logger;

import java.util.ArrayList;
import java.util.List;

public class ChunkMesh {
    public Chunk chunk;
    public Mesh mesh;
    public float[] positions, textureCoords, uvs, normals;
    public int[] indices;
    private List<Vertex> vertices;
    private List<Float> positionsList;
    private List<Integer> indicesList;
    private List<Float> textureList;


    public ChunkMesh(Chunk chunk) {
        this.chunk = chunk;
        vertices = new ArrayList<>();
        positionsList = new ArrayList<>();
        indicesList = new ArrayList<>();
        textureList = new ArrayList<>();

        buildMesh();
        populateLists();
        updateMesh();
    }

    public void update(Chunk chunk) {
        this.chunk = chunk;
        buildMesh();
        populateLists();
        updateMesh();
    }

    private void buildMesh() {

        for (int i = 0, size = chunk.blocks.size(); i < size; i++) {
            Block blockI = chunk.blocks.get(i);

            boolean px = false, nx = false, py = false, ny = false, pz = false, nz = false;
            for (int j = 0; j < size; j++) {
                Block blockJ = chunk.blocks.get(j);
                if (blockI.x + 1 == blockJ.x && blockI.y == blockJ.y && blockI.z == blockJ.z) {
                    px = true;
                }
                if (blockI.x - 1 == blockJ.x && blockI.y == blockJ.y && blockI.z == blockJ.z) {
                    nx = true;
                }
                if (blockI.x == blockJ.x && blockI.y + 1 == blockJ.y && blockI.z == blockJ.z) {
                    py = true;
                }
                if (blockI.x == blockJ.x && blockI.y - 1 == blockJ.y && blockI.z == blockJ.z) {
                    ny = true;
                }
                if (blockI.x == blockJ.x && blockI.y == blockJ.y && blockI.z + 1 == blockJ.z) {
                    pz = true;
                }
                if (blockI.x == blockJ.x && blockI.y == blockJ.y && blockI.z - 1 == blockJ.z) {
                    nz = true;
                }

            }

            if (!px) {
                for (int k = 0; k < 6; k++) {
                    vertices.add(new Vertex(
                            new Vector3f(CubeModel.PX_POS[k].x + blockI.x, CubeModel.PX_POS[k].y + blockI.y, CubeModel.PX_POS[k].z + blockI.z), CubeModel.SIDE_TEXT[k], CubeModel.NORMALS[k])
                    );
                }
            }
            if (!nx) {
                for (int k = 0; k < 6; k++) {
                    vertices.add(new Vertex(new Vector3f(CubeModel.NX_POS[k].x + blockI.x, CubeModel.NX_POS[k].y + blockI.y, CubeModel.NX_POS[k].z + blockI.z), CubeModel.SIDE_TEXT[k], CubeModel.NORMALS[k])
                    );
                }
            }
            if (!py) {
                for (int k = 0; k < 6; k++) {
                    vertices.add(new Vertex(new Vector3f(CubeModel.PY_POS[k].x + blockI.x, CubeModel.PY_POS[k].y + blockI.y, CubeModel.PY_POS[k].z + blockI.z), CubeModel.TOP_TEXT[k], CubeModel.NORMALS[k])
                    );
                }
            }
            if (!ny) {
                for (int k = 0; k < 6; k++) {
                    vertices.add(new Vertex(new Vector3f(CubeModel.NY_POS[k].x + blockI.x, CubeModel.NY_POS[k].y + blockI.y, CubeModel.NY_POS[k].z + blockI.z), CubeModel.BOT_TEXT[k], CubeModel.NORMALS[k])
                    );
                }
            }
            if (!pz) {
                for (int k = 0; k < 6; k++) {
                    vertices.add(new Vertex(new Vector3f(CubeModel.PZ_POS[k].x + blockI.x, CubeModel.PZ_POS[k].y + blockI.y, CubeModel.PZ_POS[k].z + blockI.z), CubeModel.SIDE_TEXT[k], CubeModel.NORMALS[k])
                    );
                }
            }
            if (!nz) {
                for (int k = 0; k < 6; k++) {
                    vertices.add(new Vertex(new Vector3f(CubeModel.NZ_POS[k].x + blockI.x, CubeModel.NZ_POS[k].y + blockI.y, CubeModel.NZ_POS[k].z + blockI.z), CubeModel.SIDE_TEXT[k], CubeModel.NORMALS[k])
                    );
                }
            }


        }

    }

    private void populateLists() {
        for (int i = 0, size = vertices.size(); i < size; i++) {
            positionsList.add(vertices.get(i).position.x);
            positionsList.add(vertices.get(i).position.y);
            positionsList.add(vertices.get(i).position.z);
            indicesList.add(i);
            textureList.add(vertices.get(i).texture.x);
            textureList.add(vertices.get(i).texture.y);
        }

        positions = new float[positionsList.size()];
        for (int i = 0, size = positionsList.size(); i < size; i++) {
            positions[i] = positionsList.get(i);
        }
        textureCoords = new float[textureList.size()];
        for (int i = 0, size = textureList.size(); i < size; i++) {
            textureCoords[i] = textureList.get(i);
        }

        indices = new int[indicesList.size()];
        for (int i = 0, size = indicesList.size(); i < size; i++) {
            indices[i] = indicesList.get(i);
        }
        positionsList.clear();
        textureList.clear();
        indicesList.clear();

    }

    private void updateMesh() {
        mesh = MeshFactory.TexturedMesh(positions, textureCoords, indices);
    }


}
