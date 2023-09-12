package org.lwjglb.log;

public class Logger {

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
        System.out.print(stackTrace[3]);

        StringBuilder message = new StringBuilder("::");
        for (int i = 0, messagesLength = messages.length; i < messagesLength; i++) {
            Object arg = messages[i];
            message.append(arg.toString());
            if (i < messagesLength - 1) {
                message.append("_");
            }
        }
        System.out.println(message);
    }
}
