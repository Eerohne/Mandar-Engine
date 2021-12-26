package org.mandar;

import org.joml.Random;
import org.lwjgl.BufferUtils;
import org.mandar.core.GameEngine;
import org.mandar.core.Input;
import org.mandar.core.KeyCode;
import org.mandar.core.Layer;
import org.mandar.debug.Debug;
import org.mandar.event.Event;
import org.mandar.renderer.Shader;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class GameLogic extends Layer {

    float vertices[] = {
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.0f,  0.5f, 0.0f
    };

    Shader shader = null;
    private Random rand = new Random();

    int vaoID, vboID;
    public float r = 1, g =0, b = 0;

    @Override
    public void onAttach(){ //OnStart

        Debug.log("App Initiated");

        shader = new Shader("assets/shaders/default.glsl");
        shader.compile();

        //Create VAO
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertexBuffer.put(vertices);
        vertexBuffer.flip();

        //Create VBO
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
    }

    @Override
    public void update(float deltaTime) { //OnUpdate

        r = (float) Input.getMousePosition().x / GameEngine.engine.getWindow().getWidth();
        g = (float) Input.getMousePosition().y / GameEngine.engine.getWindow().getHeight();
        b = Input.isKeyPressed(KeyCode.G) ? 1 : 0;

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(r, g, b ,1);

        shader.use();

        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindVertexArray(vaoID);
        glDrawArrays(GL_TRIANGLES, 0, 3);
        glDisableVertexAttribArray(0);
    }

    @Override
    public void onDetach() { //OnExit

    }


    @Override
    public void onEvent(Event e) {

        e.handled = true; //mark event as handled, placeholder for now
    }
}
