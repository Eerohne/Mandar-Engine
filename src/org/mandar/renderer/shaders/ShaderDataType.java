package org.mandar.renderer.shaders;

import org.mandar.exceptions.window.ShaderDataTypedDoesNotExistException;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_INT;
import static org.lwjgl.opengl.GL20.GL_BOOL;

public enum ShaderDataType {
    NONE, FLOAT, FLOAT2, FLOAT3, FLOAT4,
    MAT3, MAT4,
    INT, INT2, INT3, INT4, BOOL;

    public static int getDataTypeComponentCount(ShaderDataType type){
        switch (type){
            case NONE:                              return 0;
            case FLOAT:   case INT:   case BOOL:    return 1;
            case FLOAT2:  case INT2:                return 2;
            case FLOAT3:  case INT3:                return 3;
            case FLOAT4:  case INT4:                return 4;
            case MAT3:                              return 3 * 3;  // To verify
            case MAT4:                              return 4 * 4; // To verify
        }

        throw new ShaderDataTypedDoesNotExistException();
    }

    public static int getOpenGLTypeValue(ShaderDataType type){
        switch (type){
            case FLOAT: case FLOAT2: case FLOAT3: case FLOAT4: case MAT3: case MAT4:  return GL_FLOAT;
            case INT:   case INT2:   case INT3:   case INT4:                          return GL_INT;
            case BOOL:                                                                return GL_BOOL;
        }

        throw new ShaderDataTypedDoesNotExistException();
    }

    public static int getDataTypeByteSize(ShaderDataType type){
        switch (type){
            case NONE:                              return 0;
            case FLOAT:   case INT:                 return 4 * 1;
            case FLOAT2:  case INT2:                return 4 * 2;
            case FLOAT3:  case INT3:                return 4 * 3;
            case FLOAT4:  case INT4:                return 4 * 4;
            case MAT3:                              return 4 * 3 * 3;  // To verify
            case MAT4:                              return 4 * 4 * 4; // To verify
            case BOOL:                              return 1;
        }

        throw new ShaderDataTypedDoesNotExistException();
    }
}
