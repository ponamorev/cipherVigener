package ru.penzgtu.ponamorev.cipherVigener.utils;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final Logger logger = new Logger();
    private static final String INITIAL_TEXT_NAME = "INITIAL TEXT";
    private static final String CIPHERED_TEXT_NAME = "CIPHERED TEXT";

    public static List<String> readFromFile(File file) {
        List<String> linesFromFile = null;
        if (isFileCorrectAndNotEmpty(file)) {
            try (FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                linesFromFile = new ArrayList<>();
                String oneLine;
                while ((oneLine = bufferedReader.readLine()) != null) {
                    linesFromFile.add(oneLine);
                }
            } catch (IOException ex) {
                ErrorHandling.printErrorDescriptionToConsole(ex, "There was an I/O error, during while reading from file.");
            }
        }
        // TODO: note that this method can return null list of some another list with strings - it should be handled
        return linesFromFile;
    }

    public static boolean writeTableIntoFile(List<String> initialText,
                                          List<String> cipheredText,
                                          File file,
                                          boolean createIfNotExist) {
        List<String> resultListForTable;
        int maxStringLength = 0;

        if (initialText != null && cipheredText != null) {
            if (initialText.size() == cipheredText.size() && initialText.size() != 0) {
                for (int i = 0; i < initialText.size(); i++) {
                    int initTextLineSize = initialText.get(i).length();
                    int ciphTextLineSize = cipheredText.get(i).length();
                    if (initTextLineSize == ciphTextLineSize && initTextLineSize > maxStringLength) {
                        maxStringLength = initTextLineSize;
                    } else if (initTextLineSize != ciphTextLineSize) {
                        logger.error("You got ciphered text with another size, which is not equal with initial text size");
                        logger.error("If this error will be fallen - write developer to e-mail: penzayk@mail.ru");
                        logger.error("And describe your error as explicit as you can");
                        return false;
                    }
                }
            } else {
                logger.error("Probably, some string were not ciphered or you specified empty string for ciphering.");
                logger.error("Try again, please.");
                logger.error("If this error will be fallen - write developer to e-mail: penzayk@mail.ru");
                logger.error("And describe your error as explicit as you can");
                return false;
            }
        } else {
            logger.error("You specified empty of not existing strings for ciphering!");
            return false;
        }

        if (isFileCorrectAndNotEmpty(file)) {
            // prepare list with strings for writing
            resultListForTable = prepareListOfStringsForWriting(initialText, cipheredText);

            if (writeToFile(file, resultListForTable)) {
                logger.info("You can look result in file - {}", file.getAbsolutePath());
            } else {
                logger.error("There was an error during writing to file. See logs for details.");
                return false;
            }
        } else if (file != null && createIfNotExist) {
            // prepare list with strings for writing
            resultListForTable = prepareListOfStringsForWriting(initialText, cipheredText);

            String[] fileName = file.getName().split(".");
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

    private static boolean isFileCorrectAndNotEmpty(File file) {
        boolean isFileCorrect = false;
        if (file != null) {
            if (file.exists()) {
                if (file.isFile()) {
                    String[] fileName = file.getName().split(".");
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
                logger.error("File is not exist. Specify existing file!");
            }
        } else {
            logger.error("File is null. You should specify another file!");
        }
        return isFileCorrect;
    }

    private static List<String> prepareListOfStringsForWriting(List<String> initialText,
                                                               List<String> cipheredText) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < initialText.size(); i++) {
            String name = "Line " + (i + 1);
            String edgeLine = StringUtils.repeat("=", initialText.get(i).length() + 23);
            String spacesLine = String.format("##%s## %s ##", StringUtils.repeat(" ", 15),
                    StringUtils.repeat(" ", initialText.get(i).length()));
            String initialTextLine = String.format("## %s  ## %s ##", INITIAL_TEXT_NAME, initialText.get(i));
            String cipheredTextLine = String.format("## %s ## %s ##", CIPHERED_TEXT_NAME, cipheredText.get(i));

            // add all strings to list and add two empty string for separating
            result.add(name);
            result.add(edgeLine);
            result.add(spacesLine);
            result.add(initialTextLine);
            result.add(spacesLine);
            result.add(edgeLine);
            result.add(spacesLine);
            result.add(cipheredTextLine);
            result.add(spacesLine);
            result.add(edgeLine);
            // add two empty strings if there is not last ciphered line of text
            String emptyString = "";
            if (i != initialText.size() - 1) {
                result.add(emptyString);
                result.add(emptyString);
            }
        }

        return result;
    }

    private static boolean writeToFile(File file,
                                    List<String> resultList) {
        boolean isWritingFinishedSuccessfully = false;
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            for (String line : resultList) {
                fileWriter.write(line);
            }
            isWritingFinishedSuccessfully = true;
        } catch (IOException ex) {
            ErrorHandling.printErrorDescriptionToConsole(ex, "There was an I/O error during writing lines to file");
        }

        return isWritingFinishedSuccessfully;
    }
}