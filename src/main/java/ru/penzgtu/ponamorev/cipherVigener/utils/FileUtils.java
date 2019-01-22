package ru.penzgtu.ponamorev.cipherVigener.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class FileUtils extends OutputUtils {
    private static final Logger logger = new Logger();

    public static List<String> readFromFile(File file,
                                            String type,
                                            Scanner scanner) {
        List<String> linesFromFile = null;
        if (isFileCorrectAndNotEmpty(file, "read")) {
            try (FileReader fileReader = new FileReader(file);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                linesFromFile = new ArrayList<>();
                String key = getKeyOrCode(type, scanner);
                String oneLine;
                StringBuilder builderLineWithCode = new StringBuilder();
                while ((oneLine = bufferedReader.readLine()) != null) {
                    builderLineWithCode.append(oneLine).append("#").append(key);
                    linesFromFile.add(builderLineWithCode.toString());
                    builderLineWithCode = new StringBuilder();
                }
            } catch (IOException ex) {
                ErrorHandling.printErrorDescriptionToConsole(ex, "There was an I/O error, during while reading from file.");
            }
        }
        if (linesFromFile == null) {
            linesFromFile = Collections.emptyList();
        }
        return linesFromFile;
    }

    public static boolean writeResultTableIntoFile(List<String> initialText,
                                                   List<String> resultText,
                                                   String code,
                                                   String cipher,
                                                   String action,
                                                   File file,
                                                   boolean createIfNotExist) {
        List<String> resultListForTable;
        int maxStringLength = checkIfBothListsCorrectAndGetMaxLineLength(initialText, resultText);

        if (maxStringLength == 0) {
            logger.error("There was some error during checking lists with initial and result texts");
            return false;
        }

        if (isFileCorrectAndNotEmpty(file, "write")) {
            // prepare list with strings for writing
            resultListForTable = prepareListOfStringsForWriting(initialText, resultText, code, cipher, action);

            if (writeToFile(file, resultListForTable)) {
                logger.info("You can look result in file - {}", file.getAbsolutePath());
            } else {
                logger.error("There was an error during writing to file. See logs for details.");
                return false;
            }
        } else if (file != null && createIfNotExist) {
            // prepare list with strings for writing
            resultListForTable = prepareListOfStringsForWriting(initialText, resultText, code, cipher, action);

            String[] fileName = file.getName().split("\\.");
            String extension = fileName[fileName.length - 1];
            if (extension.equals("txt")) {
                try {
                    Files.createDirectories(file.toPath().getParent());
                    Files.createFile(file.toPath());
                } catch (IOException ex) {
                    ErrorHandling.printErrorDescriptionToConsole(ex, "There was an error during creating file!");
                    return false;
                }
            } else {
                logger.error("You specified not text file! Try again and specify correct file!");
                return false;
            }

            if (writeToFile(file, resultListForTable)) {
                logger.info("You can look result in file - {}", file.getAbsolutePath());
            } else {
                logger.error("There was an error during writing to file. See logs for details.");
                return false;
            }
        } else {
            logger.error("Specified file is not correct and you set that file should not be created! Try again!");
            return false;
        }

        return true;
    }

    private static boolean isFileCorrectAndNotEmpty(File file,
                                                    String action) {
        boolean isFileCorrect = false;
        if (file != null) {
            if (file.exists()) {
                if (file.isFile()) {
                    String[] fileName = file.getName().split("\\.");
                    String extension = fileName[fileName.length - 1];
                    if (extension.equals("txt")) {
                        if (file.length() != 0) {
                            logger.info("File is correct and it is not empty.");
                            isFileCorrect = true;
                        } else {
                            logger.error("File is empty. Specify another not empty file!");
                        }
                    } else {
                        logger.error("File is not a text file. Specify text file!");
                    }
                } else {
                    logger.error("Specified file is not a file. Specify another file!");
                }
            } else {
                if (action.equals("read")) {
                    logger.error("File is not exist. Specify existing file!");
                }
            }
        } else {
            logger.error("File is null. You should specify another file!");
        }
        return isFileCorrect;
    }

    private static boolean writeToFile(File file,
                                       List<String> resultList) {
        boolean isWritingFinishedSuccessfully = false;
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            for (String line : resultList) {
                fileWriter.write(line);
                fileWriter.write("\n");
            }
            isWritingFinishedSuccessfully = true;
        } catch (IOException ex) {
            ErrorHandling.printErrorDescriptionToConsole(ex, "There was an I/O error during writing lines to file");
        }

        return isWritingFinishedSuccessfully;
    }
}