package ru.penzgtu.ponamorev.cipherVigener.utils;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleUtils extends OutputUtils {
    private static final Logger logger = new Logger();

    public static String readFromConsole(Scanner input,
                                         String typeOfCipher) {
        StringBuilder builder = new StringBuilder();
        System.out.print("Enter string for encoding/decoding here >>> ");
        String inputString = input.nextLine();
        builder.append(inputString).append("#").append(getKeyOrCode(typeOfCipher, input));
        return builder.toString();
    }

    public static void printToConsole(String initialText,
                                      String resultText,
                                      String code,
                                      String cipher,
                                      String action) {
        List<String> resultListForTable;
        List<String> initialTextList = Collections.singletonList(initialText);
        List<String> resultTextList = Collections.singletonList(resultText);

        // check that lists are correct in the next method
        int maxStringLength = checkIfBothListsCorrectAndGetMaxLineLength(initialTextList, resultTextList);

        if (maxStringLength == 0) {
            logger.error("There was some error during checking lists with initial and result texts.");
            return;
        }

        resultListForTable = prepareListOfStringsForWriting(initialTextList, resultTextList, code, cipher, action);
        logger.info("Result:");
        for (String line : resultListForTable) {
            System.out.println(line);
        }
    }
}