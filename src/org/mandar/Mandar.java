package org.mandar;

import org.mandar.Entity.*;
import org.mandar.debug.Debug;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Mandar {

    private static final String ENGINE_NAME = "Mandar";

    public static void main(String[] args) {


        Debug.init();

        /*ComponentA compA = new ComponentA(5);
        Class componentType = ComponentA.class;
        Class compType =  compA.getClass();

        Debug.coreLog(componentType);
        Debug.coreLog(compType);

        if(compType.equals(componentType))
            Debug.coreLog("Found");
        else
            Debug.coreLog("Not Found");*/


        Registry reg = new Registry();

        View v = new View();

        int entity1 = reg.create();
        int entity2 = reg.create();
        int entity3 = reg.create();
        Debug.coreLog("1: {0}, 2: {1}, 3:{2}", entity1, entity2, entity3);


        reg.addComponent(entity1, new ComponentA(1));
        reg.addComponent(entity1, new ComponentB("Hello"));

        reg.addComponent(entity2, new ComponentA(2));

        var view = reg.view(ComponentB.class);

        for(var entry : view.entrySet())
        {
            Debug.coreLog("{0}", entry.getKey());
        }

        /*for(EntityIterator it = view.entityIterator(); it.hasNext();)
        {
            EntityEntry entry = it.next();
            if(entry.getKey().equals(entity1))
            {
                Debug.coreLog("entity1 here");
                for(Iterator<Object> iter = view.componentIterator(entry.getKey()); iter.hasNext();)
                {
                    Debug.coreLog(" >{0}", iter.next());
                }
            }
            else if(entry.getKey().equals(entity2))
            {
                Debug.coreLog("entity2 here");
            }

        }*/



        /*ComponentA compA2 = reg.getComponent(entity, ComponentA.class);
        ComponentB compB2 = reg.getComponent(entity, ComponentB.class);
        if(compB2 != null)
            Debug.coreLog("Found: " + compB2.name);
        else
            Debug.coreLog("not found");*/

/*
        String s = "abcdefg";
        int a = 1;

        //case 1 : get one char
        Debug.coreLog("\u001B[35m"+"one char: {0}", s.substring(a, a+1));

        //case 2 : get one char
        Debug.coreLog("two chars, {0}", s.substring(a, a+2));

        try{
            //GameEngine e = new GameEngine(ENGINE_NAME, new GameLogic());
            //e.run();
        } catch(Exception e){
            Debug.coreError("{0} could not start", ENGINE_NAME);
        }

        */

    }

}