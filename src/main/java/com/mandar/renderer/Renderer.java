package com.mandar.renderer;

public abstract class Renderer {

    abstract void init();

    abstract void push();

    public static Renderer createRenderer(){
        return null;
    }
}
