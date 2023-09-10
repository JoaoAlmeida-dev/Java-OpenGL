package org.lwjglb.engine.scene;

import org.lwjglb.engine.graph.Model;
import org.lwjglb.engine.graph.TextureCache;
import org.lwjglb.engine.IGuiInstance;
import org.lwjglb.log.Logger;

import java.util.*;

public class Scene {
    private final Projection projection;
    private Map<String, Model> modelMap;
    private Camera camera;
    private TextureCache textureCache;
    private IGuiInstance guiInstance;

    public Scene(int width, int height) {
        modelMap = new HashMap<>();
        projection = new Projection(width, height);
        camera = new Camera();
        textureCache = new TextureCache();
        Logger.debug("Scene::Scene::Created Scene", "width:" + width, "height:" + height);
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
        modelMap.values().forEach(Model::cleanup);
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

    public IGuiInstance getGuiInstance() {
        return guiInstance;
    }

    public void setGuiInstance(IGuiInstance guiInstance) {
        this.guiInstance = guiInstance;
    }

    public TextureCache getTextureCache() {
        return textureCache;
    }

    public void resize(int width, int heigth) {
        projection.updateProjMatrix(width, heigth);
    }

}
