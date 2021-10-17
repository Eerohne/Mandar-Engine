package org.jlog;

public class JLogExample {

    public static void main(String[] args)
    {
        JLog.init();

        JLog.addLogger("MyLogger");
        JLog.addLogger("MySecondLogger");

        JLog.getLogger("MyLogger").log("I wanna buy {1} for {0}$", 10.0, "apples");

        /*JLog.getLogger("MyLogger").log("A simple {0} with a parameter {1} for you!", "message", "just");
        JLog.getLogger("MyLogger").warn("Careful with the bread, it's still hot!");
        JLog.getLogger("MySecondLogger").error("Task #{1} failed succesfully!", 23, 3, "aaa");*/

    }
}
