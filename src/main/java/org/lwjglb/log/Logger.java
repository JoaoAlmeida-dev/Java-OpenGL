package org.lwjglb.log;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger instance;
    public List<String> logs = new ArrayList<>(100);

    public static List<String> getLogs() {
        return getInstance().logs;
    }

    private static synchronized Logger getInstance() {
        if (instance == null)
            instance = new Logger();

        return instance;
    }

    public static void debug(Object... messages) {
        log(messages);

//        StringBuilder message = new StringBuilder();
//        for (Object arg : messages) {
//            message.append(arg.toString());
//        }
//        System.out.println(message);
    }

    public static void log(Object... messages) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        StringBuilder message = new StringBuilder();

        message.append(stackTrace[3].toString());
        for (int i = 0, messagesLength = messages.length; i < messagesLength; i++) {
            Object arg = messages[i];
            message.append("[");
            message.append(arg.toString());
            message.append("]");
        }
        getInstance().logs.add(message.toString());
        System.out.println(message);

    }
}
