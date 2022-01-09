package org.mandar.renderer;

public class RendererAPI {
    public enum RenderingAPI { NONE, OPENGL }

    private static RenderingAPI rendererAPI = RenderingAPI.OPENGL;

    public static RenderingAPI getRendererAPI(){
        return rendererAPI;
    }

    public static void setRendererAPI(RenderingAPI api){
        rendererAPI = api;
    }
}
