package org.mandar;

import org.mandar.Scene.*;
import org.mandar.Scene.Components.ComponentA;
import org.mandar.Scene.Components.ComponentB;
import org.mandar.debug.Debug;

public class Mandar {

    private static final String ENGINE_NAME = "Mandar";

    public static void main(String[] args) {


        Debug.init();

        Scene scene = new Scene();

        Entity entity = scene.createEntity();
        //entity.addComponent(new ComponentA(3));
        entity.addComponent(new ComponentB("aaa"));

        ComponentB compB = entity.getComponent(ComponentB.class);

        entity.removeComponent(ComponentA.class);



        /*

        try{
            //GameEngine e = new GameEngine(ENGINE_NAME, new GameLogic());
            //e.run();
        } catch(Exception e){
            Debug.coreError("{0} could not start", ENGINE_NAME);
        }
        */
    }
}