package org.mandar.scene;

import org.mandar.scene.components.ComponentA;
import org.mandar.scene.components.TagComponent;

public class Scene {

    Registry entityRegistry;

    public Scene()
    {
        this.entityRegistry = new Registry();
    }

    public Entity createEntity()
    {
        return createEntity(null);
    }

    public Entity createEntity(String name)
    {
        Entity entity = new Entity(entityRegistry.create(), this);
        entity.addComponent(new TagComponent(name));

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

    public Registry getEntityRegistry() {return this.entityRegistry;}
}
