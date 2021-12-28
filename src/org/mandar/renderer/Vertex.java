package org.mandar.renderer;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Vertex {
    public Vector4f pos;
    public Vector4f col;

    public Vertex(float x, float y, float z){
        pos = new Vector4f(x, y, z, 1.0f);
        col = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public Vertex(float x, float y, float z, float w, float r, float g, float b, float opacity){
        pos = new Vector4f(x, y, z, w);
        col = new Vector4f(r, g, b, opacity);
    }

    public Vertex(Vector4f pos, float r, float g, float b, float opacity){
        this.pos = pos;
        col = new Vector4f(r, g, b, opacity);
    }

    public Vertex(float x, float y, float z, float w, Vector4f color){
        pos = new Vector4f(x, y, z, w);
        this.col = color;
    }

    public Vector4f getPosition() {
        return pos;
    }

    public float getPosX(){
        return pos.x();
    }

    public float getPosY(){
        return pos.y();
    }

    public float getPosZ(){
        return pos.z();
    }

    public float getPosW(){
        return pos.w();
    }

    public Vector4f getColor() {
        return col;
    }

    public float getColorR(){
        return col.x();
    }

    public float getColorG(){
        return col.y();
    }

    public float getColorB(){
        return col.z();
    }

    public float getColorAlpha(){
        return col.w();
    }
}
