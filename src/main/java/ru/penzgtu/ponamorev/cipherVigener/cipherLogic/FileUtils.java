package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    private static final Logger logger = new Logger();
    /*public static String parseFile(File file) {
        if (file == null) {
            System.err.println("File is NULL, specify existing file!");
            return null;
        }
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String oneLine;
            if ((oneLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(oneLine);
            }
            if (stringBuilder.toString().equals("")) {
                System.err.println("File is empty!");
                return "";
            } else {
                return stringBuilder.toString();
            }
        } catch (FileNotFoundException fnfEx) {
            System.err.println("File is not exist! Create it or specify existing file!");
            return null;
        } catch (IOException ioEx) {
            System.err.println("There war an error during reading from file!");
            return null;
        }
    }
*/
    public static String parseFile(File file) {
        if (file != null && file.exists()) {
            if (!file.isDirectory()) {

            }
        }
        return "";
    }

    private static boolean isFileCorrectAndNotEmpty(File file) {
        boolean isFileCorrect = false;
        if (file != null) {
            if (file.exists()) {
                if (file.isFile()) {
                    if (file.length() != 0) {
                        logger.info("File is correct and it is not empty.");
                        isFileCorrect = true;
                    }
                }
            }
        }
        return isFileCorrect;
    }

    public static void writeTableIntoFile(String cipher, File file) {
        if (file == null) {
            System.err.println("File is NUll, specify existing file!");
            return;
        }
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
        } catch (IOException ioEx) {
            System.err.println("There was an error during writing into file!");
        }
    }
}