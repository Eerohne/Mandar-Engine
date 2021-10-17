package org.mandar.core;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private final String title;

    private int width;
    private int height;

    private long windowKey;

    private boolean vSync;

    private boolean resized;

    public Window(String title, int width, int height, boolean vSync){
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
        this.resized = false;
    }

    public void init(){
        //GLFW Errors: Set were they are printed (To be displaced, attached to debugging)
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()){
            throw new IllegalStateException("Failed to initialize GLFW");
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        this.windowKey = glfwCreateWindow(width,height,title,NULL,NULL);
        if(windowKey == NULL){
            throw new RuntimeException("Failed to create Window");
        }

        //Sets resize callback
        glfwSetFramebufferSizeCallback(windowKey, (window, width, height) -> {
           this.width = width;
           this.height = height;
           this.setResized(true);
        });

        //Sets key callback
        glfwSetKeyCallback(windowKey, (window, key, scancode, action, mods) -> {
            if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE){
                glfwSetWindowShouldClose(window,true);
            }
        });

        //Get Resolution of monitor and Center Window
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(windowKey, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);

        glfwMakeContextCurrent(windowKey);

        //Enable vsync
        if(vSync){
            glfwSwapInterval(1);
        }

        GL.createCapabilities();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void update(){
        glfwSwapBuffers(windowKey);
        glfwPollEvents();
    }

    //Put this in Renderer
    //public void setClearColor();

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowKey, keyCode) == GLFW_PRESS;
    }

    public boolean windowShouldClose() {
        return glfwWindowShouldClose(windowKey);
    }

    public String getTitle(){
        return title;
    }

    public boolean isResized() {
        return resized;
    }

    public void setResized(boolean resized) {
        this.resized = resized;
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
}
