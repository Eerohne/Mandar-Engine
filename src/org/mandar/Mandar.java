package org.mandar;

import org.mandar.debug.Debug;
import org.mandar.core.GameEngine;

import java.util.HashMap;
import java.util.Locale;

public class Mandar {

    private static final String ENGINE_NAME = "Mandar";

    public static void main(String[] args) {


        Debug.init();

        String prix = "aaaa";

        HashMap<String, String> map = new HashMap<>();
        map.put("prix", prix);

        Debug.coreLog(prix);
        Debug.coreLog(map.get("prix"));

        Debug.coreLog(map.get("prix"));
        Debug.coreLog(prix);

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