package ru.penzgtu.ponamorev.cipherVigener;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CaesarCipher;
import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.VigenerCipher;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Scanner;

public class CipherRunTest {
    private String wordToEncodeFromConsole;
    private String wordToDecodeCaesarFromConsole;
    private String wordToDecodeVigenerFromConsole;
    private String vigenerCode;
    private String caesarCode;
    private String root;
    private String resourcesFolder;
    private File text_txt;
    private File caesar_encoded_txt;
    private File vigener_encoded_txt;
    private CaesarCipher caesar;
    private VigenerCipher vigener;
    private Scanner console;

    @BeforeClass
    public void setUp() {
        wordToEncodeFromConsole = "karamba1995ABC\n";
        wordToDecodeCaesarFromConsole = "OEVEQFE8DDBdef";
        vigenerCode = "aloha0O";
        caesarCode = "7";
        root = new File(".").getAbsolutePath();
        resourcesFolder = root + "\\src\\main\\resources\\";
        text_txt = new File(resourcesFolder + "text.txt");
        caesar_encoded_txt = new File(resourcesFolder + "caesarEncoded.txt");
        vigener_encoded_txt = new File(resourcesFolder + "vigenerEncoded.txt");
        caesar = new CaesarCipher();
        vigener = new VigenerCipher();
    }

    @Test
    // @Ignore
    public void encodeCaesarLineFromConsoleToConsole() {
        String input = wordToEncodeFromConsole + "\n" + caesarCode + "\n";
        console = new Scanner(new ByteArrayInputStream(input.getBytes()));
        caesar.workWithTextFromConsole(console, "encode", "Caesar", "console", null, false);
    }

    @Test
    // @Ignore
    public void encodeCaesarLineFromConsoleToFile() {
        String input = wordToEncodeFromConsole + "\n" + caesarCode + "\n";
        console = new Scanner(new ByteArrayInputStream(input.getBytes()));
        String filePath = root + "\\src\\test\\resources\\";
        File file = new File(filePath + "encodeCaesarLineFromConsoleToFile.txt");
        caesar.workWithTextFromConsole(console, "encode", "Caesae", "file", file, true);
    }
}