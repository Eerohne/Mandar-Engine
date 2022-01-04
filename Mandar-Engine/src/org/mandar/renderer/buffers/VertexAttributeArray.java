package org.mandar.renderer.buffers;

import org.mandar.exceptions.renderer.RendererAPINotSupportedException;
import org.mandar.renderer.Renderer;

import java.util.ArrayList;

public abstract class VertexAttributeArray {
    protected int attributeArrayID;
    protected ArrayList<Buffers.VertexBuffer> vertexBuffers = new ArrayList<>();
    protected Buffers.IndexBuffer indexBuffer;

    public static VertexAttributeArray create() throws RendererAPINotSupportedException{
        switch (Renderer.RENDERER_API){
            case OPENGL:
                return new OpenGLVertexAttributeArray();
        }
        throw new RendererAPINotSupportedException();
    }

    public abstract void bind();
    public abstract void unbind();
    public abstract void delete(boolean deleteBuffers);
    public abstract void addVertexBuffer(Buffers.VertexBuffer vertexBuffer);
    public abstract void setIndexBuffer(Buffers.IndexBuffer indexBuffer);

    public ArrayList<Buffers.VertexBuffer> getVertexBuffers() {
        return vertexBuffers;
    }

    public Buffers.IndexBuffer getIndexBuffer() {
        return indexBuffer;
    }

    public int getAttributeArrayID() {
        return attributeArrayID;
    }
}
