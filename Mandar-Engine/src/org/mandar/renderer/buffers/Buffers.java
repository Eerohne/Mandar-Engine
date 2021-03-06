package org.mandar.renderer.buffers;

import org.mandar.debug.Debug;
import org.mandar.exceptions.renderer.RendererAPINotSupportedException;
import org.mandar.plateform.opengl.buffers.OpenGLBuffers;
import org.mandar.renderer.RendererAPI;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class Buffers {

    public static VertexBuffer createVertexBuffer(FloatBuffer vertices) throws RendererAPINotSupportedException {
        switch (RendererAPI.getRendererAPI()){
            case NONE:
                Debug.coreError("No Renderer API is specified");
                break;
            case OPENGL:
                return new OpenGLBuffers().createOpenGLVertexBuffer(vertices);
        }

        throw new RendererAPINotSupportedException();
    }

    public static IndexBuffer createIndexBuffer(IntBuffer indices, int count) throws RendererAPINotSupportedException {
        switch (RendererAPI.getRendererAPI()){
            case NONE:
                Debug.coreError("No Renderer API is specified");
            case OPENGL:
                return new OpenGLBuffers().createOpenGLVertexBuffer(indices, count);
        }

        throw new RendererAPINotSupportedException();
    }

    public abstract class VertexBuffer{
        protected int vertexBufferID;
        protected BufferLayout layout = new BufferLayout();

        public int getVertexBufferID(){
            return vertexBufferID;
        }

        public abstract void bind();
        public abstract void unbind();
        public abstract void delete();

        public void setLayout(BufferLayout layout){
            this.layout = layout;
        }
        public BufferLayout getLayout(){
            return layout;
        }
    }

    public abstract class IndexBuffer{
        protected int indexBufferID;
        protected int indexCount;

        public int getIndexBufferID() {
            return indexBufferID;
        }

        public int getCount(){
            return indexCount;
        }

        public abstract void bind();
        public abstract void unbind();
        public abstract void delete();
    }
}