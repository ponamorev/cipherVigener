package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

import ru.penzgtu.ponamorev.cipherVigener.utils.Logger;

import java.util.Scanner;

public class CaesarCipher extends CipherImpl {
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
        int key = Integer.parseInt(code);

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
                index += key;
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
        int key = Integer.parseInt(code);

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
                index -= key;
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
}