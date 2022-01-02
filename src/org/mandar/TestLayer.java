package org.mandar;

import org.joml.Random;
import org.lwjgl.BufferUtils;
import org.mandar.core.Layer;
import org.mandar.debug.Debug;
import org.mandar.event.Event;
import org.mandar.renderer.buffers.Buffers;
import org.mandar.renderer.shaders.Shader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;


// TODO : TestLayer class is supposed to be inside a runtime project which uses Mandar, but it's here for now for testing

public class TestLayer extends Layer {

    float vertices[] = {
             0.5f, -0.5f, 0.0f, //1.0f, 0.0f, 0.0f,
            -0.5f,  0.5f, 0.0f, //1.0f, 0.0f, 0.0f,
             0.5f,  0.5f, 0.0f, //1.0f, 0.0f, 1.0f
            -0.5f, -0.5f, 0.0f, //1.0f, 1.0f, 0.0f,
    };

    int indices[] = {2,1,0,0,1,3};

    Shader shader = null;
    //private Random rand = new Random();

    int vaoID;
    Buffers.VertexBuffer vbo;
    Buffers.IndexBuffer ibo;
    //public float r = 1, g =0, b = 0;

    @Override
    public void onAttach(){ //OnStart

        Debug.log("App Initiated");

        shader = Shader.create("assets/shaders/default.glsl");
        shader.compile();
        shader.attach();

        //Create VAO
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertexBuffer.put(vertices);
        vertexBuffer.flip();

        //Create VBO
        vbo = Buffers.createVertexBuffer(vertexBuffer);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        IntBuffer indexBuffer = BufferUtils.createIntBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.flip();

        ibo = Buffers.createIndexBuffer(indexBuffer, indices.length);
    }

    @Override
    public void update(float deltaTime) { //OnUpdate


        //ImGui.text("Hello, World!");

//        r = (float) Input.getMousePosition().x / GameEngine.engine.getWindow().getWidth();
//        g = (float) Input.getMousePosition().y / GameEngine.engine.getWindow().getHeight();
//        b = Input.isKeyPressed(KeyCode.G) ? 1 : 0;

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);//r, g, b ,1);



        glBindVertexArray(vaoID);
        glDrawElements(GL_TRIANGLES, ibo.getCount(), GL_UNSIGNED_INT, 0);
        //glDisableVertexAttribArray(0);
    }

    @Override
    public void onDetach() { //OnExit
    }


    @Override
    public void onEvent(Event e) {
         //mark event as handled, placeholder for now
    }
}
