package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

import ru.penzgtu.ponamorev.cipherVigener.utils.Logger;

import java.util.Arrays;
import java.util.Scanner;

public class VigenerCipher extends CipherImpl {
    private static final Logger logger = new Logger();
    private final Alphabet[] symbols = Alphabet.values();
    private final int alphabetCapacity = symbols.length;

    @Override
    public String encode(String initialText,
                         String code,
                         Scanner scanner) {
        char[] inputChars = initialText.toCharArray();
        char[] resultChars = new char[inputChars.length];
        char initialSymbol;
        char resultSymbol;
        int codeCounter = 0;

        // get indexes of code word
        char[] codeArray = code.toCharArray();
        int[] codeArrayIndexes = getCodeArrayIndexes(codeArray);
        int[] fault = new int[]{-1};
        if (Arrays.equals(codeArrayIndexes, fault)) {
            return null;
        }

        for (int i = 0; i < inputChars.length; i++) {
            initialSymbol = inputChars[i];
            resultSymbol = 0;
            int index = -1;
            for (Alphabet symbol : symbols) {
                if (symbol.getValue() == initialSymbol) {
                    index = symbol.getIndex();
                }
            }
            if (index != -1) {
                index += codeArrayIndexes[codeCounter++];
                if (codeCounter == codeArrayIndexes.length) codeCounter = 0;
                if (index >= alphabetCapacity) {
                    index -= alphabetCapacity;
                }
                for (Alphabet symbol : symbols) {
                    if (index == symbol.getIndex()) {
                        resultSymbol = symbol.getValue();
                    }
                }
            } else {
                logger.warn("Your ciphered text has unsupported symbol - {}", String.valueOf(initialSymbol));
                logger.warn("It will be decoded as the first symbol of alphabet - \'a\'");
                resultSymbol = 'a';
            }

            resultChars[i] = resultSymbol;
        }

        return String.valueOf(resultChars);
    }

    @Override
    public String decode(String initialText,
                         String code) {
        char[] inputChars = initialText.toCharArray();
        char[] resultChars = new char[inputChars.length];
        char initialSymbol;
        char resultSymbol;
        int codeCounter = 0;

        // get indexes of code word
        char[] codeArray = code.toCharArray();
        int[] codeArrayIndexes = getCodeArrayIndexes(codeArray);
        int[] fault = new int[]{-1};
        if (Arrays.equals(codeArrayIndexes, fault)) {
            return null;
        }

        for (int i = 0; i < inputChars.length; i++) {
            initialSymbol = inputChars[i];
            resultSymbol = 0;
            int index = -1000;
            for (Alphabet symbol : symbols) {
                if (symbol.getValue() == initialSymbol) {
                    index = symbol.getIndex();
                }
            }
            if (index != -1000) {
                index -= codeArrayIndexes[codeCounter++];
                if (codeCounter == codeArrayIndexes.length) codeCounter = 0;
                if (index < 0) {
                    index += alphabetCapacity;
                }
                for (Alphabet symbol : symbols) {
                    if (index == symbol.getIndex()) {
                        resultSymbol = symbol.getValue();
                    }
                }
            } else {
                logger.warn("Your ciphered text has unsupported symbol - {}", String.valueOf(initialSymbol));
                logger.warn("It will be decoded as the first symbol of alphabet - \'a\'");
                resultSymbol = 'a';
            }

            resultChars[i] = resultSymbol;
        }

        return String.valueOf(resultChars);
    }

    private int[] getCodeArrayIndexes(char[] codeArray) {
        int[] codeArrayIndexes = new int[codeArray.length];
        for (int i = 0; i < codeArray.length; i++) {
            codeArrayIndexes[i] = -1;
            for (Alphabet symbol : symbols) {
                if (codeArray[i] == symbol.getValue()) {
                    codeArrayIndexes[i] = symbol.getIndex();
                }
            }
            if (codeArrayIndexes[i] == -1) {
                logger.error("Symbol {} wasn't found!", String.valueOf(codeArray[i]));
                logger.warn("Returning to menu...");
                return new int[]{-1};
            }
        }

        return codeArrayIndexes;
    }
}