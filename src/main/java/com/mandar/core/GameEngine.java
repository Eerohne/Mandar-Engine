package com.mandar.core;

import java.util.Arrays;
import java.util.LinkedList;


public class GameEngine implements Runnable{
    private final Window window;
    private final Thread gameLoopThread;

    private LinkedList<LogicLayer> logicLayers;

    private boolean isEngineRunning;

    private float targetFPS;
    private float updatesPerSec;

    public GameEngine(String windowTitle, LogicLayer gameLogic) throws Exception{
        this(windowTitle, 800, 600, 100, 30, true, gameLogic);
    }

    public GameEngine(String windowTitle, int windowWidth, int windowHeight, float maxFPS, float maxUpdates, boolean vSync, LogicLayer... logicLayers) throws Exception{
        gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");

        window = new Window(windowTitle, windowWidth, windowHeight, vSync);
        this.logicLayers.addAll(Arrays.asList(logicLayers));

        this.targetFPS = maxFPS;
        this.updatesPerSec = maxUpdates;

        this.isEngineRunning = true;
    }

    public void start(){
        gameLoopThread.start();
    }

    @Override
    public void run() {
        try{
            start();
            init();
            gameLoop();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void gameLoop(){
        float updateTimer = 0f;

        while(isEngineRunning && !window.windowShouldClose()){
            Time.update();
            updateTimer += Time.getDeltaTime();

            if(updateTimer >= 1/updatesPerSec){
                update();
                updateTimer = 0f;
            }

            render();

            if(!window.isvSync()){
                customSync();
            }
        }
    }

    private void customSync() {
        float fpsTime = 1/targetFPS;
        double syncTime = Time.getSystemTime() + fpsTime;
        while(Time.getSystemTime() <= syncTime){
            try{
                Thread.sleep(1);
            } catch(Exception e){
                System.err.println("Custom Sync Sleep Error");
            }
        }
    }

    private void init() throws Exception{
        window.init();
        Time.init();
        for (LogicLayer layer : this.logicLayers) {
            layer.init();
        }
    }

    private void update(){
        for (LogicLayer layer : this.logicLayers) {
            layer.update();
        }
    }

    private void render(){
        for (LogicLayer layer : this.logicLayers) {
            layer.render();
        }
        window.update();
    }
}
