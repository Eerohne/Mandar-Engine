package org.mandar.Scene;

import java.util.*;

public class View extends HashMap<Integer, ArrayList<Object>>{

    public View()
    {
        super();
    }

    public boolean hasEntity(int entity)
    {
        return this.containsKey(entity);
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

    // TODO : remove component should be a registry-only feature, use the register for this
    /*public <T> boolean removeComponent(int entity, Class<T> componentType)
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
    }*/

    public ArrayList<Object> getComponentList(int entity)
    {
        return this.get(entity);
    }


    public <T> View view()
    {
        return this;
    }

    public <T> View view(Class<T> componentType)
    {
        return this.view(componentType, null);
    }

    //In the case component2 is given as null, then only component1 will be required
    public <T, V> View view(Class<T> componentType1, Class<V> componentType2)
    {
        var view = (View) this.clone();

        for(Iterator<Entry<Integer, ArrayList<Object>>> iterator = view.entrySet().iterator(); iterator.hasNext();) //get all entities in view
        {
           int componentsFound = 0;

            for(Object component : iterator.next().getValue())
            {
                if(componentType2 == null) // if component2 is null, then only look for component1
                {
                    if (component.getClass().equals(componentType1)) {
                        componentsFound += 2;
                        break;
                    }
                }
                else //otherwise, look for both
                {
                    if (component.getClass().equals(componentType1) || component.getClass().equals(componentType2)) { //if not null then look for both
                        componentsFound++;
                        if(componentsFound == 2)
                            break;
                    }
                }
            }

            if(componentsFound < 2)//dismiss if not all required components are present in entity
                iterator.remove();
        }
        return view;
    }
}
