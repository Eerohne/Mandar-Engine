package org.mandar.renderer.shaders;

import org.mandar.renderer.Renderer;

public abstract class Shader {
    protected int programID;

    public static Shader create(String srcPath){
        switch (Renderer.RENDERER_API){
            case OPENGL:
                return new OpenGLShader(srcPath);
        }

        return null;
    }

    public static Shader create(String vertexSrcPath, String fragmentSrcPath){
        return null;
    }

    public abstract void compile();

    public abstract void attach();

    public abstract void detach();

    public abstract void delete();

    public int getShaderProgram(){
        return programID;
    }
}
