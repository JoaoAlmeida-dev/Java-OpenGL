package org.lwjglb.game.world;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Vertex {
    public Vector3f position, normals;
    public Vector2f texture;

    public Vertex(Vector3f position, Vector2f texture, Vector3f normals) {
        this.position = position;
        this.normals = normals;
        this.texture = texture;
    }
}
