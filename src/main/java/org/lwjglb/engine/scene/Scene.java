package org.lwjglb.engine.scene;

import org.lwjglb.engine.graph.Mesh;

import java.util.*;

public class Scene {
    private final Projection projection;
    private Map<String, Mesh> meshMap;

    public Scene(int width, int height) {
        meshMap = new HashMap<>();
        projection = new Projection(width, height);
    }

    public void addMesh(String meshId, Mesh mesh) {
        meshMap.put(meshId, mesh);
    }

    public void cleanup() {
        meshMap.values().stream().forEach(Mesh::cleanup);
    }

    public Map<String, Mesh> getMeshMap() {
        return meshMap;
    }

    public Projection getProjection() {
        return projection;
    }

    public void resize(int width, int heigth) {
        projection.updateProjMatrix(width, heigth);
    }

}
