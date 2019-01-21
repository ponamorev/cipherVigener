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
                                        String outputPlace,
                                        File file,
                                        boolean createIfNotExist) {
        String inputString = ConsoleUtils.readFromConsole(scanner);
        String resultString = action.equals("encode")
                ? encode(inputString, scanner)
                : decode(inputString);

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
                                     String outputPlace,
                                     File file,
                                     boolean createIfNotExist) {
        List<String> initialList = FileUtils.readFromFile(file);
        List<String> resultList = new ArrayList<>();

        if (action.equals("encode")) {
            for (String line : initialList) {
                String resultLine = encode(line, scanner);
                resultList.add(resultLine);
            }
        } else {
            for (String line : initialList) {
                String resultLine = decode(line);
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