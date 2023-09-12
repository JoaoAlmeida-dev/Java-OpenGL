package org.lwjglb.game.world;

import org.joml.Vector3f;
import org.lwjglb.engine.graph.*;
import org.lwjglb.engine.scene.Entity;

import java.util.ArrayList;
import java.util.List;

public class Chunk {
    //pegamos nos cubos e fazemos a mesh, pegamos na mesh e fazemos o material
    // model -> entities + materials
    // entity -> criada com modelid
    // material -> add the meshes and set texturepath
    public List<Block> blocks;
    private Vector3f origin;

    public Chunk(List<Block> blocks, Vector3f origin) {
        this.origin = origin;
        this.blocks = blocks;
    }

}
