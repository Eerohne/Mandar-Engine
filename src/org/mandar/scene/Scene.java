package org.mandar.scene;

import org.mandar.scene.components.ComponentA;

public class Scene {

    Registry entityRegistry;

    public Scene()
    {
        this.entityRegistry = new Registry();
    }

    public Entity createEntity()
    {
        Entity entity = new Entity(entityRegistry.create(), this);
        return entity;
    }

    public void destroyEntity(Entity entity)
    {
        entityRegistry.destroy(entity.getHandle());
    }

    public void onStart()
    {

    }

    public void onUpdate()
    {

        var view = entityRegistry.view(ComponentA.class);

        for(var entry : view.entrySet())
        {
            Entity entity = new Entity(entry.getKey(), this);
            entity.getComponent(ComponentA.class).id = 7;

        }

    }

    public void onStop()
    {

    }
}
