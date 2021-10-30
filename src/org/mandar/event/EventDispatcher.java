package org.mandar.event;

public class EventDispatcher{

    private Event event;

    public EventDispatcher(Event event)
    {
        this.event = event;
    }

    public <T> boolean dispatch(EventType type, IMethodCallBack<T> callback)
    {
        if(event.type != type) //not dispatching any other type of event
            return false;

        Class<T> c = (Class<T>) event.getClass();
        event.handled |= callback.call(c.cast(event));
        return true;
    }
}


