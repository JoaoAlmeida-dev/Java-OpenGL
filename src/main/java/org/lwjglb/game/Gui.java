package org.lwjglb.game;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.ImVec2;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import org.joml.Vector2f;
import org.lwjglb.engine.IGuiInstance;
import org.lwjglb.engine.MouseInput;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.scene.Scene;
import org.lwjglb.log.Logger;

import java.util.List;

public class Gui implements IGuiInstance {
    static boolean SCROLLTOBOTTOM = true;

    @Override
    public void drawGui(Window window) {
        ImGui.newFrame();
        showConsole();

//        setupDockSpace(window);

//        ImGui.setNextWindowPos(0, 0, ImGuiCond.Always);
//        ImGui.showDemoWindow();


//        ImGui.end();
        ImGui.endFrame();
        ImGui.render();
    }

    @Override
    public boolean handleGuiInput(Scene scene, Window window) {
        ImGuiIO imGuiIO = ImGui.getIO();
        MouseInput mouseInput = window.getMouseInput();
        Vector2f mousePos = mouseInput.getCurrentPos();
        imGuiIO.setMousePos(mousePos.x, mousePos.y);
        imGuiIO.setMouseDown(0, mouseInput.isLeftButtonPressed());
        imGuiIO.setMouseDown(1, mouseInput.isRightButtonPressed());

        return imGuiIO.getWantCaptureMouse() || imGuiIO.getWantCaptureKeyboard();
    }

    private void setupDockSpace(Window window) {
        int windowFlags = ImGuiWindowFlags.MenuBar | ImGuiWindowFlags.NoDocking;
        ImGui.setNextWindowPos(0.0f, 0.0f, ImGuiCond.Always);

        ImGui.setNextWindowSize(window.getWidth() / 2, window.getHeight() / 2);
        ImGui.pushStyleVar(ImGuiStyleVar.WindowRounding, 0.0f);
        ImGui.pushStyleVar(ImGuiStyleVar.WindowBorderSize, 0.0f);
        windowFlags |= ImGuiWindowFlags.NoCollapse |
                ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove |
                ImGuiWindowFlags.NoBringToFrontOnFocus | ImGuiWindowFlags.NoNavFocus;

        ImGui.begin("Dockspace Demo", new ImBoolean(true), windowFlags);
        ImGui.popStyleVar(2);

        // Dockspace
        ImGui.dockSpace(ImGui.getID("Dockspace"));
    }

    private void showConsole() {
        if (ImGui.beginChild("Scrolling")) {

            ImGui.textColored(1, "Console");
            final List<String> logs = Logger.getLogs();
            ImGui.textUnformatted(logs.get(0));
            int dupCounter = 0;
            for (int i = 1, logsSize = logs.size(); i < logsSize; i++) {

                String n = logs.get(i);
                String previousN = logs.get(i - 1);
                if (previousN.equals(n)) {
                    dupCounter += 1;
                    ImGui.textUnformatted(previousN + dupCounter);
                } else {
                    dupCounter = 0;
                }
            }
            if (SCROLLTOBOTTOM || (ImGui.getScrollY() >= ImGui.getScrollMaxY()))
                ImGui.setScrollHereY(1.0f);
            SCROLLTOBOTTOM = false;
        }
        ImGui.endChild();
    }
}
