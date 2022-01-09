package org.mandar.renderer.shaders;

import org.mandar.plateform.opengl.shaders.OpenGLShader;
import org.mandar.renderer.Renderer;
import org.mandar.renderer.RendererAPI;

public abstract class Shader {
    protected int programID;

    public static Shader create(String srcPath){
        switch (RendererAPI.getRendererAPI()){
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
