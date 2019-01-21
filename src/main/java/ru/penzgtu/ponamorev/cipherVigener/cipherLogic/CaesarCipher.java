package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

import ru.penzgtu.ponamorev.cipherVigener.utils.Logger;

import java.util.Scanner;

public class CaesarCipher extends CipherImpl {
    private static final Logger logger = new Logger();
    private final Alphabet[] symbols = Alphabet.values();

    @Override
    public String encode(String initialText,
                         Scanner scanner) {
        char[] inputChars = initialText.toCharArray();
        char[] resultChars = new char[inputChars.length];

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

    @Override
    public String decode(String initialText) {
        char[] inputChars = initialText.toCharArray();
        char[] resultChars = new char[inputChars.length];
        return "";
    }
}