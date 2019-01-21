package ru.penzgtu.ponamorev.cipherVigener.utils;

import java.util.Date;

public final class Logger {
    public void info(String message,
                     String... listOfParameters) {
        log("INFO", message, listOfParameters);
    }

    public void warn(String message,
                     String... listOfParameters) {
        log("WARN", message, listOfParameters);
    }

    public void error(String message,
                      String... listOfParameters) {
        log("ERROR", message, listOfParameters);
    }

    private void log(String prefix,
                     String message,
                     String... listOfParameters) {
        String currentDate = new Date().toString();
        System.out.print(String.format("%s - %s: ", currentDate, prefix));
        message = replaceParameters(message, listOfParameters);
        System.out.println(message);
    }

    private String replaceParameters(String message,
                                     String... listOfParameters) {
        String resultString = message;
        if (listOfParameters.length != 0) {
            for (String parameter : listOfParameters) {
                resultString = resultString.replace("{}", parameter);
            }
        }
        return resultString;
    }
}