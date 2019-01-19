package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

import java.util.Date;

public final class Logger {
    public void info(String message, String... listOfParameters) {
        printPrefix("INFO");
        message = replaceParameters(message, listOfParameters);
        System.out.println(message);
    }

    public void warn(String message, String... listOfParameters) {
        printPrefix("WARN");
        message = replaceParameters(message, listOfParameters);
        System.out.println(message);
    }

    public void error(String message, String... listOfParameters) {
        printPrefix("ERROR");
        message = replaceParameters(message, listOfParameters);
        System.err.println(message);
    }

    private void printPrefix(String prefix) {
        String currentDate = new Date().toString();
        System.out.print(String.format("%s - %s: ", currentDate, prefix));
    }

    private String replaceParameters(String message, String... listOfParameters) {
        String resultString = message;
        if (listOfParameters.length != 0) {
            for (String parameter : listOfParameters) {
                resultString = resultString.replace("{}", parameter);
            }
        }
        return resultString;
    }
}