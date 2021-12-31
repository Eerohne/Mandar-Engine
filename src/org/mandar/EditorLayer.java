package org.mandar;

import imgui.ImGuiIO;
import imgui.ImGuiStyle;
import imgui.ImGuiViewport;
import imgui.ImGuiWindowClass;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiDockNodeFlags;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiWindowFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.internal.ImGui;
import imgui.internal.ImGuiDockNode;
import imgui.type.ImBoolean;
import org.mandar.core.GameEngine;
import org.mandar.core.Input;
import org.mandar.core.KeyCode;
import org.mandar.core.Layer;
import org.mandar.debug.Debug;
import org.mandar.event.Event;

public class EditorLayer extends Layer {

    ImGuiImplGlfw impl;
    ImGuiImplGl3 gl;

    @Override
    public void onAttach() {
        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();

        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);


        GameEngine engine = GameEngine.engine;
        var window = engine.getWindow().getSystemWindow();


        impl = new ImGuiImplGlfw();
        impl.init(window, true);

        gl = new ImGuiImplGl3();
        gl.init("#version 410");


    }

    @Override
    public void update(float deltaTime) {
        if(Input.isKeyPressed(KeyCode.L)) {
            GameEngine.engine.getWindow().setDebugMode(false);
            Debug.log("L");
        }
    }

    public void onImGuiRender()
    {
        ImGui.showDemoWindow();
        /*boolean dockspaceOpen = true;
        boolean opt_fullscreen_persistant = true;
        boolean opt_fullscreen = opt_fullscreen_persistant;
        ImGuiDockNode dockspace_flags = new ImGuiDockNode(0);
        dockspace_flags.addLocalFlags(ImGuiDockNodeFlags.None);

        ImGuiWindowClass window_flags = new ImGuiWindowClass();
        window_flags.addDockNodeFlagsOverrideSet(ImGuiWindowFlags.MenuBar);
        window_flags.addDockNodeFlagsOverrideSet(ImGuiWindowFlags.NoDocking);


        if (opt_fullscreen)
        {
            ImGuiViewport viewport = ImGui.getMainViewport();
            ImGui.setNextWindowPos(viewport.getPosX(), viewport.getPosY());
            ImGui.setNextWindowSize(viewport.getSizeX(), viewport.getSizeY());
            ImGui.setNextWindowViewport(viewport.getID());
            ImGui.pushStyleVar(ImGuiStyleVar.WindowRounding, 0.0f);
            ImGui.pushStyleVar(ImGuiStyleVar.WindowBorderSize, 0.0f);
            window_flags.addDockNodeFlagsOverrideSet(ImGuiWindowFlags.NoTitleBar);
            window_flags.addDockNodeFlagsOverrideSet(ImGuiWindowFlags.NoCollapse);
            window_flags.addDockNodeFlagsOverrideSet(ImGuiWindowFlags.NoResize);
            window_flags.addDockNodeFlagsOverrideSet(ImGuiWindowFlags.NoMove);
            window_flags.addDockNodeFlagsOverrideSet(ImGuiWindowFlags.NoBringToFrontOnFocus);
            window_flags.addDockNodeFlagsOverrideSet(ImGuiWindowFlags.NoNavFocus);
        }

        // When using ImGuiDockNodeFlags_PassthruCentralNode, DockSpace() will render our background and handle the pass-thru hole, so we ask Begin() to not render a background.
        if (window_flags.hasDockNodeFlagsOverrideSet(ImGuiDockNodeFlags.PassthruCentralNode))
            window_flags.addDockNodeFlagsOverrideSet(ImGuiWindowFlags.NoBackground);

        ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 0);
        ImGui.begin("DockSpace Demo", new ImBoolean(true), window_flags.geClassId());
        ImGui.popStyleVar();

        if (opt_fullscreen)
            ImGui.popStyleVar(2);

        ImGuiIO io = ImGui.getIO();
        ImGuiStyle style = ImGui.getStyle();

        float minWinSizeX = style.getWindowMinSize().x;
        style.setWindowMinSize(370.0f, 370.0f);

        if (io.hasConfigFlags(ImGuiConfigFlags.DockingEnable))
        {
            int dockspace_id = ImGui.getID("MyDockSpace");
            ImGui.dockSpace(dockspace_id, 0, 0, dockspace_flags.getLocalFlags());
        }

        style.setWindowMinSize(minWinSizeX, minWinSizeX);

        ImGui.begin("Viewport");
        ImGui.end();
        ImGui.popStyleVar();
        ImGui.end();*/


    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onEvent(Event e) {

    }
}
