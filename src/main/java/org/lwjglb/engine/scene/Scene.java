package org.lwjglb.engine.scene;

import org.lwjglb.engine.graph.Model;

import java.util.*;

public class Scene {
    private final Projection projection;
    private Map<String, Model> modelMap;
    private Camera camera;


    public Scene(int width, int height) {
        modelMap = new HashMap<>();
        projection = new Projection(width, height);
        camera = new Camera();
    }

    public void addEntity(Entity entity) {
        String modelId = entity.getModelId();
        Model model = modelMap.get(modelId);
        if (model == null) {
            throw new RuntimeException("Could not find model [" + modelId + "]");
        }
        model.getEntitiesList().add(entity);
    }

    public void addModel(Model model) {
        modelMap.put(model.getId(), model);
    }

    public void cleanup() {
        modelMap.values().stream().forEach(Model::cleanup);
    }

    public Map<String, Model> getModelMap() {
        return modelMap;
    }

    public Projection getProjection() {
        return projection;
    }

    public Camera getCamera() {
        return camera;
    }

    public void resize(int width, int heigth) {
        projection.updateProjMatrix(width, heigth);
    }

}
