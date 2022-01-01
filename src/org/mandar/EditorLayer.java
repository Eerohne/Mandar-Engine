package org.mandar;

import imgui.*;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiDockNodeFlags;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiWindowFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.internal.ImGui;
import imgui.internal.ImGuiDockNode;
import imgui.type.ImBoolean;
import org.mandar.core.*;
import org.mandar.debug.Debug;
import org.mandar.event.Event;
import org.mandar.event.EventDispatcher;
import org.mandar.event.EventType;

public class EditorLayer extends Layer {

    ImGuiImplGlfw impl;
    ImGuiImplGl3 gl;

    boolean viewportFocused = false;
    boolean viewportHovered = false;

    float time = 0;

    @Override
    public void onAttach() {
    }


    @Override
    public void update(float deltaTime) {

        GameEngine.engine.getImGuiLayer().blockEvent( !(viewportFocused || viewportHovered) );
    }

    public void onImGuiRender()
    {
        boolean dockspaceOpen = true;
        boolean opt_fullscreen_persistant = true;
        boolean opt_fullscreen = opt_fullscreen_persistant;

        int window_flags = ImGuiWindowFlags.MenuBar | ImGuiWindowFlags.NoDocking;


        if (opt_fullscreen)
        {
            ImGuiViewport viewport = ImGui.getMainViewport();
            ImGui.setNextWindowPos(viewport.getPosX(), viewport.getPosY());
            ImGui.setNextWindowSize(viewport.getSizeX(), viewport.getSizeY());
            ImGui.setNextWindowViewport(viewport.getID());
            ImGui.pushStyleVar(ImGuiStyleVar.WindowRounding, 0.0f);
            ImGui.pushStyleVar(ImGuiStyleVar.WindowBorderSize, 0.0f);

            window_flags |= ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove
                    | ImGuiWindowFlags.NoBringToFrontOnFocus | ImGuiWindowFlags.NoNavFocus;

            window_flags |= ImGuiWindowFlags.NoBackground;
        }

        ImGui.begin("DockSpace Demo", new ImBoolean(true), window_flags);
        ImGui.popStyleVar(2);


        int dockspace_id = ImGui.getID("MyDockSpace");
        ImGui.dockSpace(dockspace_id);

        if (ImGui.beginMenuBar())
        {
            if (ImGui.beginMenu("File"))
            {
                if (ImGui.menuItem("Exit"))
                    GameEngine.engine.close();

                ImGui.endMenu();
            }

            ImGui.endMenuBar();
        }

        ImGui.begin("Debug");
        ImGui.text("fps: " + 1/Time.limitedDeltaTime);
        time += Time.limitedDeltaTime;
        ImGui.text("Time: " + (int)time);
        ImGui.text("Number (Press 'L') : " + GameEngine.engine.number);
        ImGui.text("Viewport Focused? : " + this.viewportFocused);
        ImGui.text("Viewport Hovered? : " + this.viewportHovered);


        ImGui.end();

        ImGui.begin("ViewPort");
        viewportFocused = ImGui.isWindowFocused();
        viewportHovered = ImGui.isWindowHovered();
        //rendering the image as a viewport here
        //ImGui.image();
        ImGui.end();

        //dockspace end
        ImGui.end();



    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onEvent(Event e) {

        EventDispatcher dispatcher = new EventDispatcher(e);

        dispatcher.dispatch(EventType.KeyPressed, (n) -> {
            if(((Event.KeyPressedEvent)e).keyCode == KeyCode.L)
                GameEngine.engine.number += 1;
            if(GameEngine.engine.number > 100)
                GameEngine.engine.number = 0;
            return true;
        });
    }

    private boolean keyPressed(Event.KeyPressedEvent e)
    {
        if(!e.repeat)
            Debug.log("Event");
        return true;
    }
}
