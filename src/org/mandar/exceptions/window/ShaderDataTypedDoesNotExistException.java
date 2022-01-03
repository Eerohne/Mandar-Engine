package org.mandar.exceptions.window;

public class ShaderDataTypedDoesNotExistException extends RuntimeException{
    public ShaderDataTypedDoesNotExistException(String message){
        super(message);
    }
    public ShaderDataTypedDoesNotExistException(){
        this("The given ShaderDataType value does not exist or is not supported");
    }
}
