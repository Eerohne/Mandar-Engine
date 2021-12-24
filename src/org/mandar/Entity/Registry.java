package org.mandar.Entity;

import java.util.*;

public class Registry {

    View registry;

    private Random random;

    public Registry()
    {
        this.registry = new View();
        random = new Random();
    }

    public int create()
    {
        int entity = random.nextInt();
        registry.put(entity, new ArrayList<>());

        return entity;
    }

    public boolean destroy(int entity)
    {
        return this.registry.remove(entity) == null ? false : true;
    }
    public <T> T addComponent(int entity, T component)
    {
        this.registry.get(entity).add(component);
        return component;
    }

    public <T> boolean hasComponent(int entity, Class<T> componentType)
    {
        return this.registry.hasComponent(entity, componentType);
    }

    public <T> T getComponent(int entity, Class<T> componentType)
    {
        return this.registry.getComponent(entity, componentType);
    }

    public <T> boolean removeComponent(int entity, Class<T> componentType)
    {
        return this.registry.removeComponent(entity, componentType);
    }

    public ArrayList<Object> getComponentList(int entity)
    {
        return this.registry.getComponentList(entity);
    }

    public <T> View view(Class<T> componentType)
    {
        return this.registry.view(componentType);
    }
}
