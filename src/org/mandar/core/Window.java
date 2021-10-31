package org.mandar.core;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import org.mandar.event.Event;
import org.mandar.event.IEventListener;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private final String title;

    private int width;
    private int height;

    private long window;

    private boolean vSync;

    private IEventListener eventListener;

    public Window(String title, int width, int height, boolean vSync){
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
    }

    public void init(){
        if(!glfwInit()){
            throw new IllegalStateException("Failed to initialize GLFW");
        }

        this.window = glfwCreateWindow(width,height,title,NULL,NULL);
        if(window == NULL){
            throw new RuntimeException("Failed to create Window");
        }

        //***CALLBACKS***//

        //Sets close callback
        glfwSetWindowCloseCallback(window, (window) -> {
            glfwSetWindowShouldClose(window,false); //set shouldClose to false, engine should decide that

            if(eventListener != null) {
                eventListener.onEvent(new Event.WindowCloseEvent());
            }
        });

        //Sets focus callback
        glfwSetWindowFocusCallback(window, (window, focused) -> {
            if(focused)
                eventListener.onEvent(new Event.WindowFocusEvent());
            else
                eventListener.onEvent(new Event.WindowLostFocusEvent());
        });

        //Sets moved callback
        glfwSetWindowPosCallback(window, (window, x, y) -> {
            eventListener.onEvent(new Event.WindowMovedEvent(x, y));
        });

        //Sets resize callback
        glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
            this.width = width;
            this.height = height;

            eventListener.onEvent(new Event.WindowResizedEvent(width, height));
        });


        //Sets key callback
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            Event keyEvent = null;

            switch(action)
            {
                case GLFW_PRESS:
                    keyEvent = new Event.KeyPressedEvent(KeyCode.getKey(key), false);
                    break;
                case GLFW_REPEAT:
                    keyEvent = new Event.KeyPressedEvent(KeyCode.getKey(key), true);
                    break;
                case GLFW_RELEASE:
                    keyEvent = new Event.KeyReleasedEvent(KeyCode.getKey(key));
                    break;
            }
            eventListener.onEvent(keyEvent);

        });

        //Sets key typed callback
        glfwSetCharCallback(window, (window, c) -> {
            eventListener.onEvent(new Event.KeyTypedEvent(c));
        });

        //Sets mouse button callback
        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {

            Event mouseButtonEvent = null;

            switch(action)
            {
                case GLFW_PRESS:
                    mouseButtonEvent = new Event.MouseButtonPressedEvent(KeyCode.getKey(button));
                    break;
                case GLFW_RELEASE:
                    mouseButtonEvent = new Event.MouseButtonReleasedEvent(KeyCode.getKey(button));
                    break;
            }
            eventListener.onEvent(mouseButtonEvent);
        });

        //Sets mouse moved callback
        glfwSetCursorPosCallback(window, (window, x, y) -> {
            eventListener.onEvent(new Event.MouseMovedEvent( (float)x, (float)y) );
        });

        //Sets mouse scroll callback
        glfwSetScrollCallback(window, (window, sx, sy) -> {
            eventListener.onEvent(new Event.MouseScrolledEvent( (float)sx, (float)sy) );
        });

        //Get Resolution of monitor and Center Window
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        //Enable vsync
        if(vSync){
            glfwSwapInterval(1);
        }

        //Gives the window the ability to render using openGL on the current window
        GL.createCapabilities();
        GL11.glClearColor(0.5f, 0.0f, 0.12f, 1f);
    }

    public void update(){
        GL11.glClearColor(GameEngine.engine.r, GameEngine.engine.g, GameEngine.engine.b, 1f);
        GL11.glClear(GL_COLOR_BUFFER_BIT);
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public String getTitle(){
        return title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isvSync() {
        return vSync;
    }

    public void setvSync(boolean vSync) {
        this.vSync = vSync;
    }

    public long getSystemWindow() { return window; }

    public void setEventListener(IEventListener listener)
    {
        this.eventListener = listener;
    }
}
