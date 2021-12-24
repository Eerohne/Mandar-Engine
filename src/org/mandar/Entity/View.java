package org.mandar.Entity;

import java.util.*;

public class View extends HashMap<Integer, ArrayList<Object>>{

    public View()
    {
        super();
    }

    public <T> boolean hasComponent(int entity, Class<T> componentType)
    {
        ArrayList<Object> componentList = this.get(entity);
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

        ArrayList<Object> componentList = this.get(entity);
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
        ArrayList<Object> componentList = this.get(entity);
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
        return this.get(entity);
    }

    public <T> View view(Class<T> componentType)
    {
        var view = (View) this.clone();

        var mapPairs = view.entrySet();// key and value pairs
        for(Iterator<Entry<Integer, ArrayList<Object>>> iterator = mapPairs.iterator(); iterator.hasNext();)
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

    public ArrayList<EntityEntry> getEntries()
    {
        ArrayList<EntityEntry> entries = new ArrayList<>();
        for(Entry<Integer, ArrayList<Object>> entry : this.entrySet())
        {
            entries.add((EntityEntry) entry);
            //this.
        }
        return entries;
    }



    /*public EntityIterator entityIterator()
    {
        Iterator<Map.Entry<Integer, ArrayList<Object>>> a = this.entrySet().iterator();
        return new EntityIterator(this.entrySet());
    }*/

    /*public Iterator<Object> componentIterator(int entity)
    {
        Iterator<Entry<Integer, ArrayList<Object>>> a;
        for(Iterator<EntityEntry> entityIterator = this.entrySet().iterator(); entityIterator.hasNext();)
        {
            Entry<Integer, ArrayList<Object>> entityEntry = entityIterator.next();
            if(entityEntry.getKey().equals(entity)) //check if this is the entity
            {
                return entityEntry.getValue().iterator(); //return an iterator of it's components
            }
        }
        return null;
    }*/
}
