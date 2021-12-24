package org.mandar.renderer;

import org.mandar.debug.Debug;

import java.util.*;

import static org.lwjgl.opengl.GL20.*;

// Takes a vertex shader and fragment shader, compiles them and links them together
public class ShaderManager {
//    private static HashMap<String, ShaderHolder> shaders = new HashMap<>();
//    private static List<Integer> programs = new LinkedList<>();
//
//    private static int currentShaderProgram;
//
//    public static ShaderHolder createShader(String shaderName, String shaderSrc, int shaderType){
//        ShaderHolder shader = new ShaderHolder(shaderName, shaderSrc, shaderType);
//        if(shader.compile()){
//            shaders.put(shaderName, shader);
//
//            return shader;
//        }
//
//        return null;
//    }
//
//    public static ShaderHolder deleteShader(String shaderName){
//        ShaderHolder shader = shaders.get(shaderName);
//        glDeleteShader(shader.getShaderID());
//
//        return shader;
//    }
//
//    public static Integer makeShaderProgram(ShaderHolder... shaders){
//        int shaderProgram = glCreateProgram();
//
//        for (ShaderHolder shader : shaders) {
//            glAttachShader(shaderProgram, shader.getShaderID());
//        }
//
//        glLinkProgram(shaderProgram);
//
//        int isLinkSuccess = glGetProgrami(shaderProgram, GL_LINK_STATUS);
//        if(isLinkSuccess == GL_FALSE){
//            int length = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
//            Debug.coreError("Shader Program {0} Compilation Failed: {1}", shaderProgram, glGetProgramInfoLog(shaderProgram, length));
//
//            glDeleteProgram(shaderProgram);
//
//            return null;
//        }
//
//        for (ShaderHolder shader : shaders) {
//            glDetachShader(shaderProgram, shader.getShaderID());
//        }
//
//        programs.add(shaderProgram);
//
//        return shaderProgram;
//    }
//
//    public static void useShaderProgram(int program){
//        glUseProgram(program);
//        currentShaderProgram = program;
//    }
//
//    public static int getCurrentShaderProgram() {
//        return currentShaderProgram;
//    }
//
//    private static class ShaderHolder {
//        private String shaderName;
//        private String shaderSrc;
//        private int shaderID;
//        private int shaderType;
//
//        ShaderHolder(String name, String shaderSrc, int type){
//            this.shaderName = name;
//            this.shaderSrc = shaderSrc;
//            this.shaderType = type;
//        }
//
//        public boolean compile(){
//
//        }
//
//        public int getShaderType() {
//            return shaderType;
//        }
//
//        public int getShaderID() {
//            return shaderID;
//        }
//
//        public String getShaderSrc() {
//            return shaderSrc;
//        }
//
//        public String getShaderName() {
//            return shaderName;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            ShaderHolder that = (ShaderHolder) o;
//            return getShaderID() == that.getShaderID() && getShaderType() == that.getShaderType() && getShaderName().equals(that.getShaderName()) && getShaderSrc().equals(that.getShaderSrc());
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(getShaderName(), getShaderSrc(), getShaderID(), getShaderType());
//        }
//    }
}
