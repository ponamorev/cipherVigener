package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

import ru.penzgtu.ponamorev.cipherVigener.utils.ConsoleUtils;
import ru.penzgtu.ponamorev.cipherVigener.utils.FileUtils;
import ru.penzgtu.ponamorev.cipherVigener.utils.Logger;

import java.io.File;
import java.util.*;

abstract class CipherImpl implements ICipher {
    private static final Logger logger = new Logger();

    public void workWithTextFromConsole(Scanner scanner,
                                        String action,
                                        String type,
                                        String outputPlace,
                                        File file,
                                        boolean createIfNotExist) {
        String[] inputStringAndCode = ConsoleUtils.readFromConsole(scanner, type).split("|");
        String inputString = inputStringAndCode[0];
        String code = inputStringAndCode[1];
        String resultString = action.equals("encode")
                ? encode(inputString, code, scanner)
                : decode(inputString, code);

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
                                     File fileFrom,
                                     File fileTo,
                                     boolean createIfNotExist) {
        List<String> initialList = FileUtils.readFromFile(fileFrom);
        List<String> resultList = new ArrayList<>();
        String code;

        // set code word if it is necessary
        if (type.equals("Vigener")) {
            System.out.print("Enter code word for encoding/decoding here >>> ");
            code = scanner.nextLine();
        } else {
            System.out.print("Enter key (number) for encoding/decoding Caesar cipher >>> ");
            int key;
            try {
                key = scanner.nextInt();
            } catch (InputMismatchException ex) {
                logger.warn("You entered not a number!");
                logger.warn("Key was set by default!");
                key = 1;
            }
            code = String.valueOf(key);
        }
        if (action.equals("encode")) {
            for (String line : initialList) {
                String resultLine = encode(line, code, scanner);
                resultList.add(resultLine);
            }
        } else {
            for (String line : initialList) {
                String resultLine = decode(line, code);
                resultList.add(resultLine);
            }
        }

        if (outputPlace.equals("file")) {
            if (FileUtils.writeResultTableIntoFile(initialList, resultList, action, fileTo, createIfNotExist)) {
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