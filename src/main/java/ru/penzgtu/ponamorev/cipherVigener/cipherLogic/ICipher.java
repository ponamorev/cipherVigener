package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

import java.util.Scanner;

public interface ICipher {
    String encode(String initialText,
                  Scanner scanner);

    String decode(String initialText);
}