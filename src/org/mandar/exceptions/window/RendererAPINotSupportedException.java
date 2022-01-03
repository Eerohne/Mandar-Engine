package org.mandar.exceptions.window;

public class RendererAPINotSupportedException extends RuntimeException{
    public RendererAPINotSupportedException(String message){ super(message); }
    public RendererAPINotSupportedException(){ this("The Renderer API you are trying to use is not recognized by the engine"); }
}
