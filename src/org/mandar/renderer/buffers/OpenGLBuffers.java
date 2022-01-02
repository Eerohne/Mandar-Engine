package org.mandar.renderer.buffers;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL45.glCreateBuffers;

public class OpenGLBuffers extends Buffers{

    public VertexBuffer createOpenGLVertexBuffer(FloatBuffer buffer){
        return new OpenGLVertexBuffer(buffer);
    }

    public IndexBuffer createOpenGLVertexBuffer(IntBuffer buffer){
        return new OpenGLIndexBuffer(buffer);
    }

    public class OpenGLVertexBuffer extends VertexBuffer{
        public OpenGLVertexBuffer(FloatBuffer buffer){
            vertexBufferID = glCreateBuffers();
            this.bind();
            glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        }

        @Override
        public void bind() {
            glBindBuffer(GL_ARRAY_BUFFER, vertexBufferID);
        }

        @Override
        public void unbind() {
            glBindBuffer(GL_ARRAY_BUFFER, 0);
        }
    }

    public class OpenGLIndexBuffer extends IndexBuffer{
        public OpenGLIndexBuffer(IntBuffer buffer){
            indexBufferID = glCreateBuffers();
            this.bind();
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        }

        @Override
        public void bind() {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBufferID);
        }

        @Override
        public void unbind() {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        }
    }
}
