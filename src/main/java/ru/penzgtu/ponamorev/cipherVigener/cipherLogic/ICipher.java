package ru.penzgtu.ponamorev.cipherVigener.cipherLogic;

public interface ICipher {
    String encode(String initialText,
                  String code);

    String decode(String initialText,
                  String code);
}