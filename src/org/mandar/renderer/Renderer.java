package org.mandar.renderer;


public abstract class Renderer {
    public enum RenderingAPI { NONE, OPENGL }

    public static RenderingAPI RENDERER_API = RenderingAPI.OPENGL;

    abstract void init();

    abstract void push();

    public static Renderer createRenderer(){
        return null;
    }
}
