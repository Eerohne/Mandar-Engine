package org.jlog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {

    //ANSI color codes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private String name;

    public Logger(String name)
    {
      this.name = name;
    }

    private String constructMessage(String type, String message, Object... parameters)
    {
        String patternString = ".*\\{.\\}.*"; //{0}
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(message);

        int i = 0;
        while(matcher.matches()){

            try {
                //replace "{i}" by argument object at index i
                message = message.replaceAll(("\\{" + i + "\\}"), parameters[i].toString());
                i++;

                matcher = pattern.matcher(message);
            } catch (ArrayIndexOutOfBoundsException e) {
                if(i <=0) {
                    System.out.println(ANSI_YELLOW+"JLog: no param were given"+ANSI_RESET);
                    return "[" + this.name + "] [" + Thread.currentThread().getStackTrace()[3] + "] " + type + message;
                }else
                    System.err.println("JLog Error: Bad parameters {"+i+"} for message");
                return null;
            }

        }
        return "["+this.name+"] [" + Thread.currentThread().getStackTrace()[3] + "] " + type + message;
    }

    public boolean log(String message, Object... parameters)
    {
        String constructedMessage = constructMessage("",message, parameters);

        if(constructedMessage == null) return false;

        System.out.println(constructedMessage);
        return true;
    }

    public boolean warn(String message, Object... parameters)
    {
        String constructedMessage = constructMessage("Warning: ", message, parameters);

        if(constructedMessage == null) return false;

        System.out.println(ANSI_YELLOW+constructedMessage+ANSI_RESET);
        return true;
    }

    public boolean error(String message, Object... parameters)
    {
        String constructedMessage = constructMessage("Error: ", message, parameters);

        if(constructedMessage == null) return false;

        System.out.println(ANSI_RED+constructedMessage+ANSI_RESET);
        return true;
    }
}
