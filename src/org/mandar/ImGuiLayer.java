package org.mandar;

import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.internal.ImGui;
import org.mandar.core.GameEngine;
import org.mandar.core.Layer;
import org.mandar.event.Event;

import static org.lwjgl.glfw.GLFW.*;

public class ImGuiLayer extends Layer {

    ImGuiImplGlfw ImGuiGlfw;
    ImGuiImplGl3 ImGuiGl;
    ImGuiIO ImGuiIO;

    @Override
    public void onAttach() {
        ImGui.createContext();

        ImGuiIO = ImGui.getIO();
        ImGuiIO.addConfigFlags(ImGuiConfigFlags.DockingEnable);
        ImGuiIO.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);
        ImGuiIO.setConfigViewportsNoTaskBarIcon(true);


        GameEngine engine = GameEngine.engine;
        var window = engine.getWindow().getSystemWindow();

        ImGuiGlfw = new ImGuiImplGlfw();
        ImGuiGlfw.init(window, true);

        ImGuiGl = new ImGuiImplGl3();
        ImGuiGl.init("#version 410");


    }

    public void begin()
    {
        ImGuiGlfw.newFrame();
        ImGui.newFrame();
    }

    @Override
    public void update(float deltaTime) {
    }

    public void end(){

        ImGui.render();
        ImGuiGl.renderDrawData(ImGui.getDrawData());

        if(ImGuiIO.hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(backupWindowPtr);
        }
    }

    @Override
    public void onDetach() {
        ImGuiGl.dispose();
        ImGuiGlfw.dispose();
        //ImGui.destroyContext();
    }

    @Override
    public void onEvent(Event e) {

    }
}
