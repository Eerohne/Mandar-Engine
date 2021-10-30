package org.mandar;

import org.mandar.debug.Debug;
import org.mandar.core.GameEngine;

public class Mandar {

    private static final String ENGINE_NAME = "Mandar";

    public static void main(String[] args) {
        Debug.init();

        try{
            GameEngine e = new GameEngine(ENGINE_NAME, new GameLogic());
            e.run();
        } catch(Exception e){
            Debug.coreError("{0} could not start", ENGINE_NAME);
        }

    }

}