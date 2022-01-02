package org.mandar.renderer.shaders;

import org.mandar.debug.Debug;
import org.mandar.renderer.shaders.Shader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDeleteShader;

public class OpenGLShader extends Shader {
    private boolean compiled = false;

    private int programID;
    private int vShaderID, fShaderID;
    private String vShaderSrc;
    private String fShaderSrc;

    public OpenGLShader(String vertexShaderFilePath, String fragmentShaderFilePath){
        try {
            vShaderSrc = new String(Files.readAllBytes(Paths.get(vertexShaderFilePath)));
        } catch (IOException ioe){
            Debug.coreError("Could not open shader file: {0}", vertexShaderFilePath);
            Debug.coreError(ioe.getMessage());
        }

        try{
            fShaderSrc = new String(Files.readAllBytes(Paths.get(fragmentShaderFilePath)));
        } catch (IOException ioe){
            Debug.coreError("Could not open shader file: {0}", fragmentShaderFilePath);
            Debug.coreError(ioe.getMessage());
        }
    }

    public OpenGLShader(String filePath){
        try{
            String shaderSrc = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] spliSrc = shaderSrc.split("(#type)( )+([a-zA-z]+)");

            int index = shaderSrc.indexOf("#type") + 6;// 6 is the length of #type plus the space that comes after
            int eol = shaderSrc.indexOf("\n", index); // Get end of line
            String shader1 = shaderSrc.substring(index, eol).trim(); // First part of the shader code

            index = shaderSrc.indexOf("#type", eol) + 6;// 6 is the length of #type plus the space that comes after
            eol = shaderSrc.indexOf("\n", index); // Get end of line
            String shader2 = shaderSrc.substring(index, eol).trim(); // Second part of the shader code

            // Assign the first part of source code to the appropriate variable
            if(shader1.equalsIgnoreCase("vertex")){
                vShaderSrc = spliSrc[1];
            }else if(shader1.equalsIgnoreCase("fragment")){
                fShaderSrc = spliSrc[1];
            } else{
                throw new IOException("Unexpected token: '" + shader1 + "'");
            }

            // Assign the second part of source code to the appropriate variable
            if(shader2.equalsIgnoreCase("vertex")){
                vShaderSrc = spliSrc[2];
            }else if(shader2.equalsIgnoreCase("fragment")){
                fShaderSrc = spliSrc[2];
            } else{
                throw new IOException("Unexpected token: '" + shader2 + "'");
            }
        } catch (IOException ioe){
            Debug.coreError("Could not open shader file: {0}", filePath);
            Debug.coreError(ioe.getMessage());
        }
    }

    public void compile(){
        //Compile Shaders
        vShaderID = createShader(vShaderSrc, GL_VERTEX_SHADER);
        fShaderID = -1;

        if(vShaderID != -1)
            fShaderID = createShader(fShaderSrc, GL_FRAGMENT_SHADER);

        if(fShaderID == -1){
            glDeleteShader(vShaderID);
            return;
        }

        this.compiled = true;


        //Link the compiled shaders
        programID = glCreateProgram();

        glAttachShader(programID, vShaderID);
        glAttachShader(programID, fShaderID);

        glLinkProgram(programID);

        int isLinkSuccess = glGetProgrami(programID, GL_LINK_STATUS);
        if(isLinkSuccess == GL_FALSE){
            int length = glGetProgrami(programID, GL_INFO_LOG_LENGTH);
            Debug.coreError("Shader Error: Linking Failed");
            Debug.coreError(glGetProgramInfoLog(programID, length));

            this.delete();

            return;
        }

        glDetachShader(programID, vShaderID);
        glDetachShader(programID, fShaderID);
    }

    private int createShader(String shaderSrc, int type){
        int shader = glCreateShader(type);
        glShaderSource(shader, shaderSrc);

        glCompileShader(shader);

        int success = glGetShaderi(shader, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            int length = glGetShaderi(shader, GL_INFO_LOG_LENGTH);
            if(type == GL_VERTEX_SHADER){
                Debug.coreError("Shader Error: Vertex Shader Compilation Failed");
            }
            if(type == GL_FRAGMENT_SHADER){
                Debug.coreError("Shader Error: Fragment Shader Compilation Failed");
            }
            Debug.coreError(glGetShaderInfoLog(shader, length));

            glDeleteShader(shader);

            return -1;
        }

        return shader;
    }

    public void attach(){
        glUseProgram(programID);
    }

    public void detach(){
        glUseProgram(0);
    }

    public void delete(){
        glDeleteProgram(programID);
        glDeleteShader(vShaderID);
        glDeleteShader(fShaderID);
        this.compiled = false;
    }
}
