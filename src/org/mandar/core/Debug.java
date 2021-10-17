package org.mandar.core;

import org.jlog.*;
public class Debug {

    private static String coreLoggerName;
    private static String appLoggerName;

    public static void init()
    {
        JLog.init();
        //default names for now
        coreLoggerName = "MANDAR";
        appLoggerName = "APP";

        JLog.addLogger(coreLoggerName);
        JLog.addLogger(appLoggerName);

        //JLog.Log_Level l = JLog.Log_Level.LOW;
    }

    public static boolean coreLog(String message, Object... parameters) {return JLog.getLogger(coreLoggerName).log(message, parameters);}
    public static boolean coreWarn(String message, Object... parameters) {return JLog.getLogger(coreLoggerName).warn(message, parameters);}
    public static boolean coreError(String message, Object... parameters) {return JLog.getLogger(coreLoggerName).error(message, parameters);}

    public static boolean log(String message, Object... parameters) {return JLog.getLogger(appLoggerName).log(message, parameters);}
    public static boolean warn(String message, Object... parameters) {return JLog.getLogger(appLoggerName).warn(message, parameters);}
    public static boolean error(String message, Object... parameters) {return JLog.getLogger(appLoggerName).error(message, parameters);}

}
