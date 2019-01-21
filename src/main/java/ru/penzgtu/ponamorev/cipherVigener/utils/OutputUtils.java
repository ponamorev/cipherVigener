package ru.penzgtu.ponamorev.cipherVigener.utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

abstract class OutputUtils {
    private static final Logger logger = new Logger();
    private static final String INITIAL_TEXT_NAME = "INITIAL TEXT";
    private static final String CIPHERED_TEXT_NAME = "CIPHERED TEXT";
    private static final String DECODED_TEXT_NAME = "DECODE TEXT";

    static int checkIfBothListsCorrectAndGetMaxLineLength(List<String> firstList,
                                                          List<String> secondList) {
        int maxStringLength = 0;

        if (firstList != null && secondList != null) {
            if (firstList.size() == secondList.size() && firstList.size() != 0) {
                for (int i = 0; i < firstList.size(); i++) {
                    int initTextLineSize = firstList.get(i).length();
                    int ciphTextLineSize = secondList.get(i).length();
                    if (initTextLineSize == ciphTextLineSize && initTextLineSize > maxStringLength) {
                        maxStringLength = initTextLineSize;
                    } else if (initTextLineSize != ciphTextLineSize) {
                        logger.error("You got ciphered text with another size, which is not equal with initial text size");
                        logger.error("If this error will be fallen - write developer to e-mail: penzayk@mail.ru");
                        logger.error("And describe your error as explicit as you can");
                    }
                }
            } else {
                logger.error("Probably, some string were not ciphered or you specified empty string for ciphering.");
                logger.error("Try again, please.");
                logger.error("If this error will be fallen - write developer to e-mail: penzayk@mail.ru");
                logger.error("And describe your error as explicit as you can");
            }
        } else {
            logger.error("You specified empty of not existing strings for ciphering!");
        }

        return maxStringLength;
    }

    static List<String> prepareListOfStringsForWriting(List<String> initialText,
                                                       List<String> resultText,
                                                       String action) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < initialText.size(); i++) {
            String name = "Line " + (i + 1);
            String edgeLine = StringUtils.repeat("=", initialText.get(i).length() + 23);
            String spacesLine = String.format("##%s## %s ##", StringUtils.repeat(" ", 15),
                    StringUtils.repeat(" ", initialText.get(i).length()));
            String initialTextLine;
            String resultTextLine;
            if (action.equals("encode")) {
                initialTextLine = String.format("## %s  ## %s ##", INITIAL_TEXT_NAME, initialText.get(i));
                resultTextLine = String.format("## %s ## %s ##", CIPHERED_TEXT_NAME, resultText.get(i));
            } else {
                initialTextLine = String.format("## %s ## %s ##", CIPHERED_TEXT_NAME, initialText.get(i));
                resultTextLine = String.format("## %s  ## %s ##", DECODED_TEXT_NAME, resultText.get(i));
            }

            // add all strings to list and add two empty string for separating
            result.add(name);
            result.add(edgeLine);
            result.add(spacesLine);
            result.add(initialTextLine);
            result.add(spacesLine);
            result.add(edgeLine);
            result.add(spacesLine);
            result.add(resultTextLine);
            result.add(spacesLine);
            result.add(edgeLine);
            // add two empty strings if there is not last ciphered line of text
            String emptyString = "";
            result.add(emptyString);
            result.add(emptyString);
        }

        return result;
    }
}