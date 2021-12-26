package org.mandar.core;

import org.mandar.event.IEventListener;
import org.mandar.renderer.Renderer;

public abstract class Layer implements IEventListener {

    public abstract void onAttach();

    public abstract void update(float deltaTime);

    public abstract void onDetach();
}
