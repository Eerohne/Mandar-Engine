package org.mandar.exceptions;

public class WindowOutOfContextException extends Exception{
    public WindowOutOfContextException() {
        this("Window creation attempt called a rendering api that is beyond the scope of the engine");
    }

    public WindowOutOfContextException(String message){
        super(message);
    }
}
