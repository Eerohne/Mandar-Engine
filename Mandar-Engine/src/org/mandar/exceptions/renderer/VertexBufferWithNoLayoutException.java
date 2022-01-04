package org.mandar.exceptions.renderer;

public class VertexBufferWithNoLayoutException extends RuntimeException{
    public VertexBufferWithNoLayoutException(String message){
        super(message);
    }
    public VertexBufferWithNoLayoutException(){
        super("The Vertex Buffer has no layout attached to it.");
    }
}
