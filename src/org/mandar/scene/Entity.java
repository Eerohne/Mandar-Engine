package org.mandar.scene;

import org.mandar.debug.Debug;

public class Entity {

    //id used in the registry
    private int entityHandle;

    private Scene scene;

    Entity(int entityHandle, Scene scene)
    {
        this.entityHandle = entityHandle;
        this.scene = scene;
    }

    public <T> T addComponent(T component)
    {
        if(hasComponent(component.getClass())) {
            Debug.coreError("Entity already has component {0}", component);
        }
        return this.scene.entityRegistry.addComponent(entityHandle, component);
    }

    public <T> boolean hasComponent(Class<T> componentType)
    {
        return this.scene.entityRegistry.hasComponent(entityHandle, componentType);
    }

    public <T> T getComponent(Class<T> componentType)
    {
        if(!hasComponent(componentType)) {
            Debug.coreError("Entity doesn't have component '{0}'", componentType.getSimpleName());
            return null;
        }
        return this.scene.entityRegistry.getComponent(entityHandle, componentType);
    }

    public <T> boolean removeComponent(Class<T> componentType)
    {
        if(!hasComponent(componentType)) {
            Debug.coreError("Entity doesn't have component '{0}'", componentType.getSimpleName());
            return false;
        }
        return this.scene.entityRegistry.removeComponent(entityHandle, componentType);
    }

    public int getHandle()
    {
        return this.entityHandle;
    }
}
