package org.mandar.core.logic;

import org.mandar.renderer.Renderer;

public abstract class LogicLayer {
    private int priority;

    protected Renderer renderer;

    public abstract void init() throws Exception;

    public abstract void update();

    public abstract void render();

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
