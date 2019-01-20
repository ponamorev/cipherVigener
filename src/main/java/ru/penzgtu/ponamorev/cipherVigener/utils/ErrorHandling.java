package ru.penzgtu.ponamorev.cipherVigener.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorHandling {
    private static final Logger logger = new Logger();

    public static void printErrorDescriptionToConsole(Exception ex,
                                                      String descriptionLine) {
        logger.error(descriptionLine);
        logger.error(ex.getMessage());
        StringWriter sw = new StringWriter();
        try {
            ex.printStackTrace(new PrintWriter(sw));
            logger.error("Stack trace:\n{}", sw.toString());
            sw.close();
        } catch (IOException closeEx) {
            logger.error("There was an error during closing object StringWriter.class");
        }
    }
}