package org.lwjglb.game.world;

public class Block {
    public final float[] textCoords = new float[]{
            0.0f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.5f, 0.0f,

            0.0f, 0.0f,
            0.5f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,

            // For text coords in top face
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.0f, 1.0f,
            0.5f, 1.0f,

            // For text coords in right face
            0.0f, 0.0f,
            0.0f, 0.5f,

            // For text coords in left face
            0.5f, 0.0f,
            0.5f, 0.5f,

            // For text coords in bottom face
            0.5f, 0.0f,
            1.0f, 0.0f,
            0.5f, 0.5f,
            1.0f, 0.5f,
    };
    public final int[] indices = new int[]{
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            8, 10, 11, 9, 8, 11,
            // Right face
            12, 13, 7, 5, 12, 7,
            // Left face
            14, 15, 6, 4, 14, 6,
            // Bottom face
            16, 18, 19, 17, 16, 19,
            // Back face
            4, 6, 7, 5, 4, 7,};
    public float[] positions;
    int x, y, z;
    TYPE type;

    public Block(int x, int y, int z, TYPE type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;

        this.positions = new float[]{
                // V0
                x - 0.5f, y + 0.5f, z + 0.5f,
                // V1
                x - 0.5f, y - 0.5f, z + 0.5f,
                // V2
                x + 0.5f, y - 0.5f, z + 0.5f,
                // V3
                x + 0.5f, y + 0.5f, z + 0.5f,
                // V4
                x - 0.5f, y + 0.5f, z - 0.5f,
                // V5
                x + 0.5f, y + 0.5f, z - 0.5f,
                // V6
                x - 0.5f, y + -0.5f, z - 0.5f,
                // V7
                x + 0.5f, y + -0.5f, z - 0.5f,

                // For text coords in top face
                // V8: V4 repeated
                x - 0.5f, y + 0.5f, z - 0.5f,
                // V9: V5 repeated
                x + 0.5f, y + 0.5f, z - 0.5f,
                // V10: V0 repeated
                x - 0.5f, y + 0.5f, z + 0.5f,
                // V11: V3 repeated
                x + 0.5f, y + 0.5f, z + 0.5f,

                // For text coords in right face
                // V12: V3 repeated
                x + 0.5f, y + 0.5f, z + 0.5f,
                // V13: V2 repeated
                x + 0.5f, y + -0.5f, z + 0.5f,

                // For text coords in left face
                // V14: V0 repeated
                x - 0.5f, y + 0.5f, z + 0.5f,
                // V15: V1 repeated
                x - 0.5f, y + -0.5f, z + 0.5f,

                // For text coords in bottom face
                // V16: V6 repeated
                x - 0.5f, y + -0.5f, z - 0.5f,
                // V17: V7 repeated
                x + 0.5f, y + -0.5f, z - 0.5f,
                // V18: V1 repeated
                x - 0.5f, y - 0.5f, z + 0.5f,
                // V19: V2 repeated
                x + 0.5f, y - 0.5f, z + 0.5f,
        };

    }

    public static enum TYPE {
        DIRT, GRASS
    }

}
