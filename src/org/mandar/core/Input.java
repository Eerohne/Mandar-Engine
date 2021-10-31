package org.mandar.core;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector2d;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

public class Input {

    public static boolean isKeyPressed(KeyCode key)
    {
        var window = GameEngine.engine.getWindow().getSystemWindow();
        return glfwGetKey(window, key.getKeyCode()) == GLFW_PRESS;
    }

    public static boolean isMousePressed(KeyCode key)
    {
        var window = GameEngine.engine.getWindow().getSystemWindow();
        return glfwGetMouseButton(window, key.getKeyCode()) == GLFW_PRESS;
    }

    public static Vector2d getMousePosition()
    {
        var window = GameEngine.engine.getWindow().getSystemWindow();
        DoubleBuffer xpos = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer ypos = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(window, xpos, ypos);
        return new Vector2d(xpos.get(), ypos.get());
    }
}
