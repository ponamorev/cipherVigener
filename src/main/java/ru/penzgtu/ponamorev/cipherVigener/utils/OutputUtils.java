package ru.penzgtu.ponamorev.cipherVigener.utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

abstract class OutputUtils {
    private static final Logger logger = new Logger();
    private static final String INITIAL_TEXT_NAME = "INITIAL TEXT";
    private static final String CIPHERED_TEXT_NAME = "CIPHERED TEXT";
    private static final String DECODED_TEXT_NAME = "DECODED TEXT";

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
            String initialTextLine;
            String resultTextLine;

            StringBuilder builder = new StringBuilder();
            // change lines to necessary format
            for (char s : initialText.get(i).toCharArray()) builder.append("|").append(s);
            String formattedInitialTextLine = builder.append("|").toString();
            builder = new StringBuilder();
            for (char s : resultText.get(i).toCharArray()) builder.append("|").append(s);
            String formattedResultTextLine = builder.append("|").toString();
            String edgeLine = StringUtils.repeat("=", formattedInitialTextLine.length() + 23);

            if (action.equals("encode")) {
                initialTextLine = String.format("## %s  ## %s ##", INITIAL_TEXT_NAME, formattedInitialTextLine);
                resultTextLine = String.format("## %s ## %s ##", CIPHERED_TEXT_NAME, formattedResultTextLine);
            } else {
                initialTextLine = String.format("## %s ## %s ##", CIPHERED_TEXT_NAME, formattedInitialTextLine);
                resultTextLine = String.format("## %s  ## %s ##", DECODED_TEXT_NAME, formattedResultTextLine);
            }

            // add all strings to list and add empty string for separating
            result.add(name);
            result.add(edgeLine);
            result.add(initialTextLine);
            result.add(edgeLine);
            result.add(resultTextLine);
            result.add(edgeLine);
            String emptyString = "";
            result.add(emptyString);
            result.add(emptyString);
        }

        return result;
    }
}