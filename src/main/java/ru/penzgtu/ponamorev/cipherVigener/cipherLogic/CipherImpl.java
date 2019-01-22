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
        String[] inputStringAndCode = ConsoleUtils.readFromConsole(scanner, type).split("#");
        String inputString = inputStringAndCode[0];
        String code = inputStringAndCode[1];
        String resultString = action.equals("encode")
                ? encode(inputString, code)
                : decode(inputString, code);

        List<String> inputStringAsList = Collections.singletonList(inputString);
        List<String> resultStringAsList = Collections.singletonList(resultString);
        if (outputPlace.equals("file")) {
            if (FileUtils.writeResultTableIntoFile(inputStringAsList, resultStringAsList, code, type,
                    action, file, createIfNotExist)) {
                logger.info("File saved successfully.");
            } else {
                logger.error("File wasn't saved. See log for details.");
            }
        } else {
            ConsoleUtils.printToConsole(inputString, resultString, code, type, action);
        }
    }

    public void workWithTextFromFile(Scanner scanner,
                                     String action,
                                     String type,
                                     String outputPlace,
                                     File fileFrom,
                                     File fileTo,
                                     boolean createIfNotExist) {
        List<String> initialList = FileUtils.readFromFile(fileFrom, type, scanner);
        List<String> resultList = new ArrayList<>();

        if (initialList == Collections.EMPTY_LIST) {
            return;
        }
        String code = initialList.get(0).split("#")[1];

        if (action.equals("encode")) {
            for (String line : initialList) {
                String resultLine = encode(line, code);
                resultList.add(resultLine);
            }
        } else {
            for (String line : initialList) {
                String resultLine = decode(line, code);
                resultList.add(resultLine);
            }
        }

        if (outputPlace.equals("file")) {
            if (FileUtils.writeResultTableIntoFile(initialList, resultList, code, type, action, fileTo, createIfNotExist)) {
                logger.info("File saved successfully.");
            } else {
                logger.error("File wasn't saved. See log for details.");
            }
        } else {
            for (int i = 0; i < initialList.size(); i++) {
                ConsoleUtils.printToConsole(initialList.get(i), resultList.get(i), code, type, action);
            }
        }
    }
}