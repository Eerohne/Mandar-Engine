package org.jlog;

import java.util.HashMap;

public class JLog {

    static HashMap<String, Logger> loggers;

    public static void init()
    {
        loggers = new HashMap<String, Logger>();
    }

    public static void addLogger(String name)
    {
        loggers.put(name, new Logger(name));
    }

    public static Logger getLogger(String name)
    {
        return loggers.get(name);
    }

    public enum Log_Level {
        LOW,
        MEDIUM,
        HIGH
    }
}
