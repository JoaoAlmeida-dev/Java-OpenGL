package org.lwjglb.game;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiCond;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.lwjglb.engine.*;
import org.lwjglb.engine.graph.*;
import org.lwjglb.engine.scene.Camera;
import org.lwjglb.engine.scene.Entity;
import org.lwjglb.engine.scene.Scene;
import org.lwjglb.engine.scene.ModelLoader;
import org.lwjglb.log.Logger;

public class Main {


    public static void main(String[] args) {
        AppLogic appLogic = new AppLogic();
        Engine gameEng = new Engine("chapter-10", new Window.WindowOptions(500, 500), appLogic);
        gameEng.start();
    }


}
