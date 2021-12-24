package org.mandar.core;

import org.joml.Random;
import org.lwjgl.BufferUtils;
import org.mandar.event.Event;
import org.mandar.event.EventDispatcher;
import org.mandar.event.EventType;
import org.mandar.event.IEventListener;
import org.mandar.renderer.Shader;

import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.LinkedList;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;


public class GameEngine implements Runnable, IEventListener {
    public static GameEngine engine; //static ref to the engine

    private final Window window;

    private LinkedList<Layer> layers;

    private boolean isRunning;

    private float targetFPS;
    private float updatesPerSec;

    public GameEngine(String windowTitle, Layer gameLogic) throws Exception{
        this(windowTitle, 800, 600, 100, 30, true, gameLogic);
    }

    public GameEngine(String windowTitle, int windowWidth, int windowHeight, float maxFPS, float maxUpdates, boolean vSync, Layer... layers) throws Exception{

        engine = this;

        this.layers = new LinkedList<>();

        window = new Window(windowTitle, windowWidth, windowHeight, vSync, true);
        window.setEventListener(this);

        this.layers.addAll(Arrays.asList(layers));

        this.targetFPS = maxFPS;
        this.updatesPerSec = maxUpdates;

        this.isRunning = true;

        init();
    }

    @Override
    public void run() {
        try{

            shader = new Shader("assets/shaders/default.glsl");
            shader.compile();

            //Create VAO
            vaoID = glGenVertexArrays();
            glBindVertexArray(vaoID);

            FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
            vertexBuffer.put(vertices);
            vertexBuffer.flip();

            //Create VBO
            vboID = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboID);
            glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);


            gameLoop();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    float vertices[] = {
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.0f,  0.5f, 0.0f
    };

    int vaoID, vboID;

    Shader shader = null;

    private void init() throws Exception{
        window.init();
        Time.init();
        for (Layer layer : this.layers) {
            layer.init();
        }

    }

    private void gameLoop(){
        float updateTimer = 0f;

        while(isRunning){
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

    public float r = 1, g =0, b = 0;
    private Random rand = new Random();

    private void update(){
//        r = (float) Input.getMousePosition().x / window.getWidth();
//        g = (float) Input.getMousePosition().y / window.getHeight();
//        b = Input.isKeyPressed(KeyCode.G) ? 1 : 0;

        for (Layer layer : this.layers) {
            layer.update();
        }
    }

    private void render(){
        for (Layer layer : this.layers) {
            layer.render();
        }

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.5f, 0.5f, 0.5f ,1);

        shader.use();

        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindVertexArray(vaoID);
        glDrawArrays(GL_TRIANGLES, 0, 3);
        glDisableVertexAttribArray(0);

        shader.detach();

        window.update();
    }

    /////*EVENTS*/////
    @Override
    public void onEvent(Event e) {

        EventDispatcher dispatcher = new EventDispatcher(e);
        dispatcher.dispatch(EventType.WindowClose, this::onWindowClosed);
        dispatcher.dispatch(EventType.WindowResize, this::onWindowResized);
        dispatcher.dispatch(EventType.WindowFocus, this::onWindowFocused);
        dispatcher.dispatch(EventType.WindowLostFocus, this::onWindowLostFocused);
        dispatcher.dispatch(EventType.WindowMoved, this::onWindowMoved);
        dispatcher.dispatch(EventType.KeyPressed, this::onKeyPressed);
        dispatcher.dispatch(EventType.KeyReleased, this::onKeyReleased);
        dispatcher.dispatch(EventType.KeyTyped, this::onKeyTyped);
        dispatcher.dispatch(EventType.MouseButtonPressed, this::onMouseButtonPressed);
        dispatcher.dispatch(EventType.MouseButtonReleased, this::onMouseButtonReleased);
        dispatcher.dispatch(EventType.MouseMoved, this::onMouseMoved);
        dispatcher.dispatch(EventType.MouseScrolled, this::onMouseScrolled);
    }

    public boolean onWindowClosed(Event.WindowCloseEvent e) {
        isRunning = false;
        return true;
    }

    public boolean onWindowResized(Event.WindowResizedEvent e) { return true; }
    public boolean onWindowFocused(Event.WindowFocusEvent e)
    {
        return true;
    }
    public boolean onWindowLostFocused(Event.WindowLostFocusEvent e) {
        //Debug.coreLog(e);
        return true;
    }

    public boolean onWindowMoved(Event.WindowMovedEvent e)
    {
        return true;
    }

    public boolean onKeyPressed(Event.KeyPressedEvent e)
    {
        Debug.coreLog(e.keyCode);
        return true;
    }
    public boolean onKeyReleased(Event.KeyReleasedEvent e)
    {
        return true;
    }
    public boolean onKeyTyped(Event.KeyTypedEvent e) {
        //Debug.coreLog(e.keyCode);
        return true;
    }

    public boolean onMouseButtonPressed(Event.MouseButtonPressedEvent e)
    {
        return true;
    }
    public boolean onMouseButtonReleased(Event.MouseButtonReleasedEvent e) {
        //Debug.coreLog(e.buttonCode);
        return true;
    }
    public boolean onMouseMoved(Event.MouseMovedEvent e) {
        //Debug.coreLog("{0}, {1}", e.x, e.y);
        return true;
    }
    public boolean onMouseScrolled(Event.MouseScrolledEvent e) {
        //Debug.coreLog("{0}, {1}", e.scrollX, e.scrollY);
        return true;
    }

    //public boolean

    /////*EVENTS*/////

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
}
