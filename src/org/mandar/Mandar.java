package org.mandar;

import org.mandar.core.GameEngine;
import org.mandar.core.RenderingAPI;


public class Mandar {

    private static final String ENGINE_NAME = "Mandar";

    public static void main(String[] args) {

        try{
            //GameEngine e = new GameEngine(ENGINE_NAME, RenderingAPI.OPENGL, new TestLayer());
            GameEngine e = new GameEngine(ENGINE_NAME, RenderingAPI.OPENGL, new TestLayer());
            e.run();
        } catch(Exception e){
            //Debug.coreError("{0} could not start", ENGINE_NAME); //<-- can't access the debugger if engine failed u dummy
            System.err.println(ENGINE_NAME + " could not start");
        }
    }
}