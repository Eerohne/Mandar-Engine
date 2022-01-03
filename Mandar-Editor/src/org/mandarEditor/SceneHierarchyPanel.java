package org.mandarEditor;

import imgui.flag.ImGuiTreeNodeFlags;
import imgui.internal.ImGui;
import org.mandar.scene.Entity;
import org.mandar.scene.Scene;
import org.mandar.scene.components.TagComponent;

import java.util.Iterator;

public class SceneHierarchyPanel {

    Scene currentScene;
    Entity selectedEntity;

    public SceneHierarchyPanel(Scene currentScene) {
        this.currentScene = currentScene;
        this.selectedEntity = null;
    }

    public void setSelectedEntity(Entity selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public void onImGuiRender()
    {
        ImGui.begin("Scene Hierarchy");

        // Right-click on blank space
        if (ImGui.beginPopupContextWindow()) {
            if (ImGui.menuItem("Create Empty Entity"))
                currentScene.createEntity("Empty Entity");

            ImGui.endPopup();
        }

        var entityIterator = currentScene.getEntityRegistry().view().keySet().iterator();
        while(entityIterator.hasNext())
        {
            Entity entity = new Entity(entityIterator.next(), this.currentScene);
            drawEntityNode(entity);
        }

        if (ImGui.isMouseDown(0) && ImGui.isWindowHovered())
            selectedEntity = null;


        ImGui.end();
    }

    private void drawEntityNode(Entity entity)
    {
        //TODO : OpenOnArray must only be used if entity has children, add another check
        int treeNodeFlags = ( ( selectedEntity != null && entity.equals(selectedEntity) ) ? ImGuiTreeNodeFlags.Selected : 0) | ImGuiTreeNodeFlags.OpenOnArrow;

        treeNodeFlags |= ImGuiTreeNodeFlags.SpanAvailWidth;

        var tag = entity.getComponent(TagComponent.class);
        boolean nodeOpened = ImGui.treeNodeEx(entity.getHandle(), treeNodeFlags, tag.name);
        if(ImGui.isItemClicked())
            this.selectedEntity = entity;

        boolean entityDeleted = false;
        if (ImGui.beginPopupContextItem())
        {
            if (ImGui.menuItem("Delete Entity"))
                entityDeleted = true;

            ImGui.endPopup();
        }

        //child nodes are not yet supported but code is somewhat ready
        if(nodeOpened)
        {
            int childNodeflags = ImGuiTreeNodeFlags.OpenOnArrow | ImGuiTreeNodeFlags.SpanAvailWidth;
            //using dummy node id
            boolean childOpened = ImGui.treeNodeEx(2457845, childNodeflags, "Child node not supported");
            if (childOpened)
                ImGui.treePop();
            ImGui.treePop();
        }

        if (entityDeleted)
        {
            currentScene.destroyEntity(entity);
            if (entity.equals(selectedEntity))
                selectedEntity = null;
        }
    }
}
