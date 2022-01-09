package org.mandar.renderer;


import org.mandar.debug.Debug;
import org.mandar.exceptions.renderer.RendererAPINotSupportedException;

public abstract class Renderer {
    public abstract void init();

    public abstract void push();

    public static Renderer createRenderer() throws RendererAPINotSupportedException{


        throw new RendererAPINotSupportedException();
    }
}
