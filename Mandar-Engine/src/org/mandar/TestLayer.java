package org.mandar;

import org.lwjgl.BufferUtils;
import org.mandar.core.Layer;
import org.mandar.debug.Debug;
import org.mandar.event.Event;
import org.mandar.renderer.buffers.VertexAttributeArray;
import org.mandar.renderer.buffers.BufferElement;
import org.mandar.renderer.buffers.BufferLayout;
import org.mandar.renderer.buffers.Buffers;
import org.mandar.renderer.shaders.Shader;
import org.mandar.renderer.shaders.ShaderDataType;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;


// TODO : TestLayer class is supposed to be inside a runtime project which uses Mandar, but it's here for now for testing

public class TestLayer extends Layer {

//    float vertices[] = {
//            -0.5f,  0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
//             0.5f,  0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f,
//             0.5f, -0.5f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f,
//            -0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f,
//             0.0f,  0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f
//    };
//
//    int indices[] = {//3,0,1,3,1,2};
//        3,0,4,
//        0,1,4,
//        4,1,2,
//        3,4,2
//    };

    float vertices[] = {
            -0.5f,  0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
             0.5f,  0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
            -0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
             0.5f,  0.5f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
             0.5f, -0.5f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
            -0.5f, -0.5f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
    };

    int indices[] = {//3,0,1,3,1,2};
            0, 1, 2,
            3, 4, 5
    };

    Shader shader = null;

    VertexAttributeArray vao;

    @Override
    public void onAttach(){ //OnStart

        Debug.log("App Initiated");

        shader = Shader.create("assets/shaders/default.glsl");
        shader.compile();
        shader.attach();

        //Create VAO
        vao = VertexAttributeArray.create();
        vao.bind();

        //Create VBO and IBO
        Buffers.VertexBuffer vbo = Buffers.createVertexBuffer(BufferUtils.createFloatBuffer(vertices.length).put(vertices).flip());
        Buffers.IndexBuffer ibo = Buffers.createIndexBuffer(BufferUtils.createIntBuffer(indices.length).put(indices).flip(), indices.length);

        vbo.setLayout(new BufferLayout(
                new BufferElement(ShaderDataType.FLOAT3, "aPos"),
                new BufferElement(ShaderDataType.FLOAT4, "aCol")
        ));
        vao.addVertexBuffer(vbo);
        vao.setIndexBuffer(ibo);
    }

    @Override
    public void update(float deltaTime) { //OnUpdate
        glClearColor(0.1f, 0.1f, 0.1f, 1f);
        glClear(GL_COLOR_BUFFER_BIT);

        vao.bind();
        glDrawElements(GL_TRIANGLES, vao.getIndexBuffer().getCount(), GL_UNSIGNED_INT, 0);
    }

    @Override public void onDetach() { //OnExit
    } @Override public void onEvent(Event e) {
         //mark event as handled, placeholder for now
    }
}
