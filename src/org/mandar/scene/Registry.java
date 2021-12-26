package org.mandar.scene;

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
        return this.registry.remove(entity) != null;
    }

    public boolean hasEntity(int entity)
    {
        return this.registry.hasEntity(entity);
    }

    public <T> T addComponent(int entity, T component)
    {
        //only add component if it doesn't exist
        if(!this.registry.hasComponent(entity, component.getClass())) {
            this.registry.get(entity).add(component);
            return component;
        }
        else
            return null;
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
        ArrayList<Object> componentList = this.registry.getComponentList(entity);
        if(componentList == null)
            return false;

        for(Object component : componentList)
        {
            if(component.getClass().equals(componentType)) {
                return componentList.remove(component);
            }
        }
        return false;
    }

    public ArrayList<Object> getComponentList(int entity)
    {
        return this.registry.getComponentList(entity);
    }

    //returns a view of all entities
    public <T> View view()
    {
        return this.registry.view();
    }

    public <T> View view(Class<T> componentType)
    {
        return this.registry.view(componentType);
    }

    public <T, V> View view(Class<T> componentType1, Class<V> componentType2)
    {
        return this.registry.view(componentType1, componentType2);
    }
}
