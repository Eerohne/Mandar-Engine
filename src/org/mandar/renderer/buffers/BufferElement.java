package org.mandar.renderer.buffers;

import org.mandar.renderer.shaders.ShaderDataType;

public class BufferElement {
    private boolean normalized = false;
    private String name;
    private ShaderDataType type;
    private int size;
    private int offset;

    public BufferElement(ShaderDataType type, String name){
        this.name = name;
        this.type = type;
        this.size = ShaderDataType.getDataTypeComponentCount(type);
        this.offset = 0;
    }

    public BufferElement(ShaderDataType type, String name, boolean normalized){
        this.name = name;
        this.type = type;
        this.size = ShaderDataType.getDataTypeComponentCount(type);
        this.offset = 0;
        this.normalized = normalized;
    }

    public String getName() {
        return name;
    }

    public ShaderDataType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public boolean isNormalized(){
        return normalized;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
