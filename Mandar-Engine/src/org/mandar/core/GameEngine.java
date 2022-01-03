package org.mandar.core;

import org.mandar.ImGuiLayer;
import org.mandar.core.window.Window;
import org.mandar.debug.Debug;
import org.mandar.event.Event;
import org.mandar.event.EventDispatcher;
import org.mandar.event.EventType;
import org.mandar.event.IEventListener;

import java.util.Iterator;

import static org.lwjgl.opengl.GL30.glGenVertexArrays;


public class GameEngine implements Runnable, IEventListener {
    public static GameEngine engine; //static ref to the engine

    private final Window window;

    private LayerStack layerStack;

    private ImGuiLayer imGuiLayer;

    private boolean isRunning;

    private float targetFPS;
    private float updatesPerSec;

    public float number = 0;

    public GameEngine(String windowTitle, Layer... gameLogic) throws Exception{
        this(windowTitle, 800, 600, 60, 10000, false, gameLogic);
    }

    public GameEngine(String windowTitle, int windowWidth, int windowHeight, float maxFPS, float maxUpdates, boolean vSync, Layer... layers) throws Exception{

        engine = this;

        window = Window.createWindow(windowTitle, windowWidth, windowHeight, vSync, false);

        this.layerStack = new LayerStack();
        for(Layer l: layers)
            layerStack.addLayer(l);

        imGuiLayer = new ImGuiLayer();
        this.layerStack.addOverlay(imGuiLayer);

        this.targetFPS = maxFPS;
        this.updatesPerSec = maxUpdates;

        this.isRunning = true;

    }

    @Override
    public void run() {
        try{
            init();
            gameLoop();
            shutDown();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init() throws Exception{
        Debug.init();
        Time.init();

        if(window != null)
            window.init(this);

        Iterator<Layer> it = layerStack.iterator();
        while(it.hasNext()) {
            it.next().onAttach();
        }

    }

    private void gameLoop(){
        float updateTimer = 0f;

        while(isRunning){
            Time.update();
            Time.limitedDeltaTime += Time.getDeltaTime();




            if(Time.limitedDeltaTime >= 1/updatesPerSec) {
                update();
                //Debug.coreLog(updateTimer);
                Time.limitedDeltaTime = 0f;
            }

            if(window != null)
                window.update();


            /*if(!window.isvSync()){
                customSync();
            }*/
        }
    }

    private void update(){
        Iterator<Layer> it = layerStack.iterator();
        while(it.hasNext()) {
            it.next().update(Time.limitedDeltaTime);
        }

        if(GameEngine.engine.getWindow() != null)
        {
            imGuiLayer.begin();
            it = layerStack.iterator();
            while (it.hasNext()) {
                it.next().onImGuiRender();
            }
            imGuiLayer.end();
        }
    }

    //ends gameLoop
    public void close()
    {
        this.isRunning = false;
    }

    //clean up before closing
    private void shutDown() {
        Iterator<Layer> it = layerStack.iterator();
        while(it.hasNext()) {
            it.next().onDetach();
        }
        if(window != null)
            this.window.close();
    }

    /////*EVENTS*/////
    @Override
    public void onEvent(Event e) {

        EventDispatcher dispatcher = new EventDispatcher(e);
        dispatcher.dispatch(EventType.WindowClose, this::onWindowClosed);
        dispatcher.dispatch(EventType.WindowResize, this::onWindowResized);

        //if event wasn't handled yet, then propagate it through the layers
        Iterator<Layer> it = layerStack.reverseIterator();
        while(it.hasNext()) {
            if(e.handled) break;
            it.next().onEvent(e);
        }
    }

    private boolean onWindowClosed(Event.WindowCloseEvent e) {
        this.isRunning = false;
        return true;
    }
    private boolean onWindowResized(Event.WindowResizedEvent e) { return true; }

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

    public Window getWindow() { return this.window; }
    public ImGuiLayer getImGuiLayer() { return this.imGuiLayer; }
}
