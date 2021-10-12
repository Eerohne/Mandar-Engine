package com.mandar.core;

import com.mandar.renderer.Renderer;

public abstract class LogicLayer {
    private int priority;

    protected Renderer renderer;
    protected Events events;

    public abstract void init() throws Exception;

    public abstract void update();

    public abstract void render();

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

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
