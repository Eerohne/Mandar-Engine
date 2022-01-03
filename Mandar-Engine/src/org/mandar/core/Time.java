package org.mandar.core;

import org.mandar.debug.Debug;

public class Time {

    private static double lastLoopTime;

    private static float deltaTime;

    public static float limitedDeltaTime = 0;

    public static void init(){
        lastLoopTime = getSystemTime();
        deltaTime = 0f;
    }

    public static void update(){
        double currentLoopTime = getSystemTime();
        deltaTime = (float)(currentLoopTime - lastLoopTime);
        lastLoopTime = currentLoopTime;
    }


    public static double getSystemTime(){
        return System.nanoTime()/1_000_000_000.0;
    }

    public static float getDeltaTime(){
        return deltaTime;
    }

    public static double getLastLoopTime(){
        return lastLoopTime;
    }
}
