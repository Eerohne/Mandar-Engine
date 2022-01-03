package org.mandarEditor;


import org.mandar.EditorLayer;
import org.mandar.TestLayer;
import org.mandar.core.GameEngine;
import org.mandar.debug.Debug;

public class EditorTest {


    private static final String ENGINE_NAME = "MANDAR";

    public static void main(String[] args) {

        try{
            GameEngine e = new GameEngine(ENGINE_NAME, new TestLayer(), new EditorLayer());
            //GameEngine e = new GameEngine(ENGINE_NAME,new TestLayer());
            e.run();

            Debug.coreLog("Editor was succesfully ported too!");
        } catch(Exception e){
            //Debug.coreError("{0} could not start", ENGINE_NAME); //<-- can't access the debugger if engine failed u dummy
            System.err.println(ENGINE_NAME + " could not start");
        }
    }
}
