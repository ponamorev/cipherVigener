package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

import ru.penzgtu.ponamorev.cipherVigener.utils.Logger;

import java.util.Scanner;

public class CaesarCipher extends CipherImpl {
    private static final Logger logger = new Logger();
    private final Alphabet[] symbols = Alphabet.values();

    @Override
    public String encode(String initialText,
                         String code,
                         Scanner scanner) {
        char[] inputChars = initialText.toCharArray();
        char[] resultChars = new char[inputChars.length];
        char initialSymbol;
        char resultSymbol;

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
                index += 3;
                for (Alphabet symbol : symbols) {
                    if (index == symbol.getIndex()) {
                        resultSymbol = symbol.getValue();
                    }
                }
            } else {
                logger.warn("You enter some symbols from another alphabet! This symbol will be " +
                        "encoded as first element of latin alphabet!");
                logger.warn("It can be follow incorrect decoding!");
                System.out.print("You can exit from this area and try again from menu. Would you like? (Y/N)" +
                        " - Enter here >>> ");
                String yn = scanner.nextLine();
                if (yn.toLowerCase().equals("y")) {
                    return null;
                }
                logger.warn("You choose case with first element. Symbol was replaced on \'a\'.");
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
                index -= 3;
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