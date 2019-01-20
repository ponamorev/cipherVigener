package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

import ru.penzgtu.ponamorev.cipherVigener.utils.ConsoleUtils;
import ru.penzgtu.ponamorev.cipherVigener.utils.FileUtils;
import ru.penzgtu.ponamorev.cipherVigener.utils.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CipherCaesarEncode {
    private static final Logger logger = new Logger();

    public static void encodeTextFromConsole(Scanner scanner) {
        String inputString = ConsoleUtils.readFromConsole(scanner);
        String resultString = encode(inputString, scanner);

        ConsoleUtils.printToConsole(inputString, resultString, true);
    }

    public static void encodeTextFromFile(File file,
                                          Scanner scanner,
                                          boolean createIfNotExist) {
        List<String> initialList = FileUtils.readFromFile(file);
        List<String> resultList = new ArrayList<>();

        for (String line : initialList) {
            String resultLine = encode(line, scanner);
            resultList.add(resultLine);
        }

        if (FileUtils.writeResultTableIntoFile(initialList, resultList, file, createIfNotExist, true)) {

        }
    }

    private static String encode(String initialText,
                                 Scanner scanner) {
        char[] inputChars = initialText.toCharArray();
        char[] resultChars = new char[inputChars.length];
        Alphabet[] symbols = Alphabet.values();

        for (int i = 0; i < inputChars.length; i++) {
            char initialSymbol = inputChars[i];
            char resultSymbol = 0;
            int index = -1;
            for (Alphabet symbol : symbols) {
                if (symbol.getValue() == initialSymbol) {
                    index = symbol.getIndex();
                }
            }
            if (index != -1) {
                index += 3;
                for (Alphabet symbol : symbols) {
                    if (index == symbol.getIndex()) {
                        resultSymbol = symbol.getValue();
                    }
                }
            } else {
                logger.error("You enter some symbols from another alphabet! This symbol will be " +
                        "encoded as first element of latin alphabet!");
                logger.error("It can be follow incorrect decoding!");
                System.out.print("You can exit from this area and try again from menu. Would you like? (Y/N)" +
                        " - Enter here >>> ");
                String yn = scanner.nextLine();
                if (yn.toLowerCase().equals("y")) {
                    return null;
                }
                logger.info("You choose case with first element. Symbol was replaced on \'a\'.");
                resultSymbol = 'a';
            }

            resultChars[i] = resultSymbol;
        }

        return String.valueOf(resultChars);
    }
}