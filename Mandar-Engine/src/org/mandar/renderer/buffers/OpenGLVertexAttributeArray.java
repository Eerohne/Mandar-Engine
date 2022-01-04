package org.mandar.renderer.buffers;

import org.mandar.exceptions.renderer.VertexBufferWithNoLayoutException;
import org.mandar.renderer.shaders.ShaderDataType;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class OpenGLVertexAttributeArray extends VertexAttributeArray {

    public OpenGLVertexAttributeArray(){
        attributeArrayID = glGenVertexArrays();
    }

    @Override
    public void bind() {
        glBindVertexArray(attributeArrayID);
    }

    @Override
    public void unbind() {
        glBindVertexArray(attributeArrayID);
    }

    @Override
    public void delete(boolean deleteBuffers) {
        if(deleteBuffers){
            for (Buffers.VertexBuffer v : vertexBuffers){
                v.delete();
            }

            indexBuffer.delete();
        }
        glDeleteVertexArrays(attributeArrayID);
    }

    @Override
    public void addVertexBuffer(Buffers.VertexBuffer vertexBuffer) throws VertexBufferWithNoLayoutException{
        if(vertexBuffer.getLayout().getElements().size() == 0)
            throw new VertexBufferWithNoLayoutException();

        vertexBuffers.add(vertexBuffer);
        glBindVertexArray(attributeArrayID);

        int index = 0;
        for (BufferElement element : vertexBuffer.getLayout()){
            glEnableVertexAttribArray(index);
            glVertexAttribPointer(
                    index,
                    ShaderDataType.getDataTypeComponentCount(element.getType()),
                    ShaderDataType.getDataTypeValue(element.getType()),
                    element.normalize(),
                    vertexBuffer.getLayout().getStride(),
                    element.getOffset()
            );

            index++;
        }
    }

    @Override
    public void setIndexBuffer(Buffers.IndexBuffer indexBuffer) {
        this.indexBuffer = indexBuffer;
    }
}
