package org.mandar.scene;

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

    }

    public void onStop()
    {

    }
}
