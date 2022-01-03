package org.mandar.renderer.buffers;

import org.mandar.renderer.shaders.ShaderDataType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class BufferLayout implements Iterable<BufferElement>{
    private ArrayList<BufferElement> elements;
    private int stride = 0;

    public BufferLayout(ArrayList<BufferElement> elements){
        this.elements = elements;
        calculateOffsetAndStride();
    }

    public BufferLayout(BufferElement... elements){
        this(new ArrayList<>(Arrays.asList(elements)));
    }


    private void calculateOffsetAndStride(){
        int offset = 0;
        this.stride = 0;

        for(BufferElement element : elements){
            element.setOffset(offset);
            offset += element.getSize();
            stride += ShaderDataType.getDataTypeByteSize(element.getType());
        }
    }

    public ArrayList<BufferElement> getElements(){
        return elements;
    }

    public int getStride() {
        return stride;
    }

    @Override
    public Iterator<BufferElement> iterator() {
        return new BufferLayoutIterator(elements);
    }

    private class BufferLayoutIterator implements Iterator<BufferElement> {
        ArrayList<BufferElement> e;
        int pos;

        BufferLayoutIterator(ArrayList<BufferElement> e){
            this.e = e;
            this.pos = 0;
        }

        @Override
        public boolean hasNext() {
            return (pos < e.size());
        }

        @Override
        public BufferElement next() {
            return e.get(pos++);
        }
    }
}
