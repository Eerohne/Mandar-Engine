package org.mandar.core.window;

import org.mandar.event.IEventListener;
import org.mandar.exceptions.window.WindowOutOfContextException;
import org.mandar.renderer.Renderer;

public abstract class Window {
    protected String title;

    protected long window;

    protected int width;
    protected int height;

    protected boolean vSync;
    protected boolean debugMode = true;

    protected IEventListener eventListener;

    public Window(String title, int width, int height, boolean vSync, boolean debugMode){
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
        this.debugMode = debugMode;
    }

    public static Window createWindow(String title, int width, int height, boolean vSync, boolean debugMode) throws WindowOutOfContextException {
        switch (Renderer.RENDERER_API){
            case OPENGL:
                return new OpenGLWindow(title, width, height, vSync, debugMode);
        }

        throw new WindowOutOfContextException();
    }

    /*Abstract Methods*/
    public abstract void init(IEventListener listener);
    public abstract void update();
    public abstract void close();
    public abstract void setDebugMode(boolean isDebugOn);
    public abstract void setTitle(String title);
    public abstract void setWidth(int width);
    public abstract void setHeight(int height);
    public abstract void setVSync(boolean vSync);
    /*End Abstract Methods*/

    /*Getters and Setters*/
    public String getTitle() {
        return title;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public boolean isVSync() {
        return vSync;
    }
    public long getSystemWindow() {
        return window;
    }
    public void setEventListener(IEventListener eventListener) {
        this.eventListener = eventListener;
    }
}
