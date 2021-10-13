package com.mandar;

import com.mandar.core.GameEngine;

public class Mandar {
    private static String ENGINE_NAME = "Mandar Engine";



    public static void main(String[] args) {
        System.out.println("Mandar Engine");

        try{
            GameEngine e = new GameEngine(ENGINE_NAME, new GameLogic());
            e.run();

        } catch(Exception e){
            System.out.println("ERROR ENGINE START");
        }
    }
}
