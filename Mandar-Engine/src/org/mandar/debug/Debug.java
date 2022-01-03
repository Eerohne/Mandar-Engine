package org.mandar.debug;

import org.ssLog.*;
public class Debug {

    private static String coreLoggerName;
    private static String appLoggerName;

    public static void init()
    {
        SSLog.init();
        //default names for now
        coreLoggerName = "MANDAR";
        appLoggerName = "APP";

        SSLog.addLogger(coreLoggerName);
        SSLog.addLogger(appLoggerName);
    }

    public static boolean coreLog(Object message, Object... parameters) {return SSLog.getLogger(coreLoggerName).log(message, parameters);}
    public static boolean coreWarn(Object message, Object... parameters) {return SSLog.getLogger(coreLoggerName).warn(message, parameters);}
    public static boolean coreError(Object message, Object... parameters) {return SSLog.getLogger(coreLoggerName).error(message, parameters);}

    public static boolean log(Object message, Object... parameters) {return SSLog.getLogger(appLoggerName).log(message, parameters);}
    public static boolean warn(Object message, Object... parameters) {return SSLog.getLogger(appLoggerName).warn(message, parameters);}
    public static boolean error(Object message, Object... parameters) {return SSLog.getLogger(appLoggerName).error(message, parameters);}

}
