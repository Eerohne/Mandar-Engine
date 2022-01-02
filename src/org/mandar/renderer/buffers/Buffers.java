package org.mandar.renderer.buffers;

import org.mandar.debug.Debug;
import org.mandar.renderer.Renderer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class Buffers {

    public static VertexBuffer createVertexBuffer(FloatBuffer vertices){
        switch (Renderer.RENDERER_API){
            case NONE:
                Debug.coreError("No Renderer API is specified");
                break;
            case OPENGL:
                return new OpenGLBuffers().createOpenGLVertexBuffer(vertices);
        }

        Debug.coreError("The Renderer API you are trying to use is not recognized by the engine");
        return null;
    }

    public static IndexBuffer createIndexBuffer(IntBuffer indices){
        switch (Renderer.RENDERER_API){
            case NONE:
                Debug.coreError("No Renderer API is specified");
            case OPENGL:
                return new OpenGLBuffers().createOpenGLVertexBuffer(indices);
        }

        Debug.coreError("The Renderer API you are trying to use is not recognized by the engine");
        return null;
    }

    public abstract class VertexBuffer{
        protected int vertexBufferID;

        public int getVertexBufferID(){
            return vertexBufferID;
        }

        public abstract void bind();
        public abstract void unbind();
    }

    public abstract class IndexBuffer{
        protected int indexBufferID;

        public int getIndexBufferID() {
            return indexBufferID;
        }

        public abstract void bind();
        public abstract void unbind();
    }
}