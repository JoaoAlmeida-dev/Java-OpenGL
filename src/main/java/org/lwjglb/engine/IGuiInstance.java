package org.lwjglb.engine;

import org.lwjglb.engine.scene.Scene;

public interface IGuiInstance {
    void drawGui(Window window);

    boolean handleGuiInput(Scene scene, Window window);
}
