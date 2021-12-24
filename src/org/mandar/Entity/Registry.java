package org.mandar.Entity;

import java.util.*;

public class Registry {

    HashMap<Integer, ArrayList<Object>> registry;

    private Random random;

    public Registry()
    {
        this.registry = new HashMap<>();
        random = new Random();
    }

    public int create()
    {
        int entity = random.nextInt();
        registry.put(entity, new ArrayList<>());

        return entity;
    }

    public <T> T addComponent(int entity, T component)
    {
        this.registry.get(entity).add(component);
        return component;
    }

    public <T> boolean hasComponent(int entity, Class<T> componentType)
    {
        ArrayList<Object> componentList = this.registry.get(entity);
        if(componentList == null)
            return false;

        for(Object component : componentList)
        {
            if(component.getClass().equals(componentType))
                return true;
        }
        return false;
    }

    public <T> T getComponent(int entity, Class<T> componentType)
    {

        ArrayList<Object> componentList = this.registry.get(entity);
        if(componentList == null)
            return null;

        for(Object component : componentList)
        {
            if(component.getClass().equals(componentType))
                return (T) component;
        }
        return null;
    }

    public <T> boolean removeComponent(int entity, Class<T> componentType)
    {
        ArrayList<Object> componentList = this.registry.get(entity);
        if(componentList == null)
            return false;

        for(Object component : componentList)
        {
            if(component.getClass().equals(componentType)) {
                componentList.remove(component);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Object> getComponentList(int entity)
    {
        return this.registry.get(entity);
    }

    public <T> HashMap<Integer, ArrayList<Object>> view(Class<T> componentType)
    {
        var view = (HashMap<Integer, ArrayList<Object>>) registry.clone();

        var mapPairs = view.entrySet();// key and value pairs
        for(Iterator<Map.Entry<Integer, ArrayList<Object>>> iterator = mapPairs.iterator(); iterator.hasNext();)
        {
            boolean found = false;
            for(Object component : iterator.next().getValue())
            {
                if(component.getClass().equals(componentType)) {
                    found = true;
                    break;
                }
            }
            if(!found)
                iterator.remove();
        }


        return view;
    }
}
