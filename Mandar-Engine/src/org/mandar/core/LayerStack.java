package org.mandar.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class LayerStack {

    public LinkedList<Layer> layers;

    public int layerIndex = 0;


    public LayerStack()
    {
        layers = new LinkedList<>();
    }

    public void addLayer(Layer layer)
    {
        layers.add(layerIndex++, layer);
    }

    public void addOverlay(Layer overlay)
    {
        layers.add(overlay);
    }

    public void popLayer()
    {
        if(layerIndex > 0)
            layers.remove(--layerIndex);
    }

    public void popLayer(Layer layer)
    {
        if(layers.remove(layer))
            layerIndex--;
    }

    public void popOverlay()
    {
        if(layers.size() > 0 && layers.size() > layerIndex) //if no overlays are present, do not start removing the layers down
            layers.remove(layers.size() - 1);
    }

    public void popOverlay(Layer overlay)
    {
        layers.remove(overlay);
    }

    public Iterator<Layer> iterator() {return layers.iterator();}
    public Iterator<Layer> reverseIterator() {return layers.descendingIterator();}
}
