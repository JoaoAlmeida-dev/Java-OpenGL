package org.lwjglb.engine.graph;

import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class Material {
    public static final Vector4f DEFAULT_COLOR = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
    private Vector4f diffuseColor;
    private List<Mesh> meshList;
    private String texturePath;

    public Material() {
        meshList = new ArrayList<>();
        diffuseColor = DEFAULT_COLOR;
    }

    public Material(Mesh mesh, String texturePath) {
        meshList = new ArrayList<>();
        diffuseColor = DEFAULT_COLOR;
        addMesh(mesh);
        setTexturePath(texturePath);
    }

    public void cleanup() {
        meshList.forEach(Mesh::cleanup);
    }

    public List<Mesh> getMeshList() {
        return meshList;
    }

    public void addMesh(Mesh mesh) {
        meshList.add(mesh);
    }

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    public Vector4f getDiffuseColor() {
        return diffuseColor;
    }

    public void setDiffuseColor(Vector4f diffuseColor) {
        this.diffuseColor = diffuseColor;
    }
}
