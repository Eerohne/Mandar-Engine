package org.mandar.exceptions.window;

public class WindowOutOfContextException extends RuntimeException{
    public WindowOutOfContextException() { this("Window creation attempt called a rendering api that is beyond the scope of the engine"); }
    public WindowOutOfContextException(String message){ super(message); }
}
