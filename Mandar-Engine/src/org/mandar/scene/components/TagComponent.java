package org.mandar.scene.components;

public class TagComponent {

    public String name;

    public TagComponent(String name)
    {
        this.name = (name == null ? "Entity" : name);
    }
}
