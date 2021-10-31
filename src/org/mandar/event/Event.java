package org.mandar.event;


import org.mandar.core.KeyCode;

public class Event<T> {

    public EventType type;

    public boolean handled = false;

    public static class WindowCloseEvent extends Event{
        public WindowCloseEvent()
        {
            this.type = EventType.WindowClose;
        }
    }

    public static class WindowResizedEvent extends Event{
        public int width, height;
        public WindowResizedEvent(int width, int height)
        {
            this.type = EventType.WindowResize;
            this.width = width;
            this.height = height;
        }
    }

    public static class WindowFocusEvent extends Event{
        public WindowFocusEvent()
        {
            this.type = EventType.WindowFocus;
        }
    }
    public static class WindowLostFocusEvent extends Event{
        public WindowLostFocusEvent()
        {
            this.type = EventType.WindowLostFocus;
        }
    }

    public static class WindowMovedEvent extends Event{
        public int x, y;
        public WindowMovedEvent(int x, int y)
        {
            this.type = EventType.WindowMoved;
            this.x = x;
            this.y = y;
        }
    }

    public static class KeyPressedEvent extends Event{
        public KeyCode keyCode;
        public boolean repeat;

        public KeyPressedEvent(KeyCode keyCode, boolean repeat)
        {
            this.type = EventType.KeyPressed;
            this.keyCode = keyCode;
            this.repeat = repeat;
        }
    }

    public static class KeyReleasedEvent extends Event{
        public KeyCode keyCode;

        public KeyReleasedEvent(KeyCode keyCode)
        {
            this.type = EventType.KeyReleased;
            this.keyCode = keyCode;
        }
    }

    public static class KeyTypedEvent extends Event{
        public char keyCode;

        public KeyTypedEvent(int keyCode)
        {
            this.type = EventType.KeyTyped;
            this.keyCode = (char)keyCode;
        }
    }

    public static class MouseButtonPressedEvent extends Event{
        public KeyCode buttonCode;

        public MouseButtonPressedEvent(KeyCode buttonCode)
        {
            this.type = EventType.MouseButtonPressed;
            this.buttonCode = buttonCode;
        }
    }

    public static class MouseButtonReleasedEvent extends Event{
        public KeyCode buttonCode;

        public MouseButtonReleasedEvent(KeyCode buttonCode)
        {
            this.type = EventType.MouseButtonReleased;
            this.buttonCode = buttonCode;
        }
    }

    public static class MouseMovedEvent extends Event{
        public KeyCode buttonCode;
        public float x, y;

        public MouseMovedEvent(float x, float y)
        {
            this.type = EventType.MouseMoved;
            this.x = x;
            this.y = y;
        }
    }

    public static class MouseScrolledEvent extends Event{
        public float scrollX, scrollY;

        public MouseScrolledEvent(float x, float y)
        {
            this.type = EventType.MouseScrolled;
            this.scrollX = x;
            this.scrollY = y;
        }
    }

    public String toString()
    {
        return type.toString()+"Event";
    }

}
