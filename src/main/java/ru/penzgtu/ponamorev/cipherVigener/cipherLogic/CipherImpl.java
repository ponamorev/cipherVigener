package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

import ru.penzgtu.ponamorev.cipherVigener.utils.ConsoleUtils;
import ru.penzgtu.ponamorev.cipherVigener.utils.FileUtils;
import ru.penzgtu.ponamorev.cipherVigener.utils.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

abstract class CipherImpl implements ICipher {
    private static final Logger logger = new Logger();

    public void workWithTextFromConsole(Scanner scanner,
                                        String action,
                                        String type,
                                        String outputPlace,
                                        File file,
                                        boolean createIfNotExist) {
        String inputString = ConsoleUtils.readFromConsole(scanner, type);
        String codeWord = type.equals("Caesar") ? "" : inputString.split("|")[1];
        inputString = type.equals("Caesar") ? inputString : inputString.split("|")[0];
        String resultString = action.equals("encode")
                ? encode(inputString, codeWord, scanner)
                : decode(inputString, codeWord);

        List<String> inputStringAsList = Collections.singletonList(inputString);
        List<String> resultStringAsList = Collections.singletonList(resultString);
        if (outputPlace.equals("file")) {
            if (FileUtils.writeResultTableIntoFile(inputStringAsList, resultStringAsList, action, file, createIfNotExist)) {
                logger.info("File saved successfully.");
            } else {
                logger.error("File wasn't saved. See log for details.");
            }
        } else {
            ConsoleUtils.printToConsole(inputString, resultString, action);
        }
    }

    public void workWithTextFromFile(Scanner scanner,
                                     String action,
                                     String type,
                                     String outputPlace,
                                     File file,
                                     boolean createIfNotExist) {
        List<String> initialList = FileUtils.readFromFile(file);
        List<String> resultList = new ArrayList<>();
        String codeWord = null;

        // set code word if it is necessary
        if (type.equals("Vigener")) {
            System.out.print("Enter code word for encoding/decoding here >>> ");
            codeWord = scanner.nextLine();
        }
        if (codeWord == null) {
            codeWord = "";
        }
        if (action.equals("encode")) {
            for (String line : initialList) {
                String resultLine = encode(line, codeWord, scanner);
                resultList.add(resultLine);
            }
        } else {
            for (String line : initialList) {
                String resultLine = decode(line, codeWord);
                resultList.add(resultLine);
            }
        }

        if (outputPlace.equals("file")) {
            if (FileUtils.writeResultTableIntoFile(initialList, resultList, action, file, createIfNotExist)) {
                logger.info("File saved successfully.");
            } else {
                logger.error("File wasn't saved. See log for details.");
            }
        } else {
            for (int i = 0; i < initialList.size(); i++) {
                ConsoleUtils.printToConsole(initialList.get(i), resultList.get(i), action);
            }
        }
    }
}