package ru.penzgtu.ponamorev.cipherVigener.bin;

import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CipherCaesarDecode;
import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CipherCaesarEncode;
import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CipherVigenerDecode;
import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CipherVigenerEncode;
import ru.penzgtu.ponamorev.cipherVigener.utils.ErrorHandling;

import java.io.File;
import java.util.Scanner;

public class CipherRunner {
    private static Scanner scanner = new Scanner(System.in);
    private static String input;
    private static String yn;

    public static void main(String[] args) {
        MenuInformationPrinter.printIntroduction();

        do {
            MenuInformationPrinter.printEnterToMenu();

            System.out.print("Enter here: >>> ");
            input = scanner.nextLine();

            if (input.toLowerCase().equals("start")) {
                startApp();
                MenuInformationPrinter.printFinishAsking();
                yn = scanner.nextLine();
                if (yn.toLowerCase().equals("n")) {
                    break;
                }
            } else if (!input.toLowerCase().equals("exit")) {
                MenuInformationPrinter.printWrongInputFromMenu();
                System.out.print(" >>> ");
                yn = scanner.nextLine();
                if (yn.toLowerCase().equals("n")) {
                    break;
                }
            }
        } while (!input.toLowerCase().equals("exit"));

        MenuInformationPrinter.printParting();
    }

    private static void startApp() {
        boolean finishWork = false;

        MenuInformationPrinter.printMenu();

        do {
            System.out.print("Enter here: >>> ");
            input = scanner.nextLine();

            try {
                switch (Integer.parseInt(input)) {
                    case 1:
                        CipherCaesarEncode.encodeTextFromConsole(scanner);
                        break;
                    case 2:
                        MenuInformationPrinter.printChooseFileName(true);
                        System.out.println("Remember that file should be *.txt");
                        System.out.print("Enter file name here >>> ");
                        String fileName = scanner.nextLine();
                        MenuInformationPrinter.printQuestionAboutCreatingNonExistingFile();
                        System.out.print("Enter your answer here >>> ");
                        boolean createFileIfItIsNotExist = scanner.nextLine().toLowerCase().equals("y");
                        CipherCaesarEncode.encodeTextFromFile(new File(fileName), scanner, createFileIfItIsNotExist);
                        break;
                    case 3:
                        CipherCaesarDecode.decodeTextFromConsole();
                        break;
                    case 4:
                        CipherCaesarDecode.decodeTextFromFile();
                        break;
                    case 5:
                        CipherVigenerEncode.encodeTextFromConsole();
                        break;
                    case 6:
                        CipherVigenerEncode.encodeTextFromFile();
                        break;
                    case 7:
                        CipherVigenerDecode.decodeTextFromConsole();
                        break;
                    case 8:
                        CipherVigenerDecode.decodeTextFromFile();
                        break;
                    default:
                        MenuInformationPrinter.printWrongNumberSelectingCipher();
                        System.out.print(" >>> ");
                        yn = scanner.nextLine();
                        if (yn.toLowerCase().equals("y"))
                            continue;
                        break;
                }
            } catch (NumberFormatException ex) {
                ErrorHandling.printErrorDescriptionToConsole(ex, "There was an error during parsing " +
                        "number from string " + input);
            }
            MenuInformationPrinter.printExitQuestion();
            System.out.print(" >>> ");
            yn = scanner.nextLine();
            if (yn.toLowerCase().equals("y")) finishWork = true;
            if (!finishWork) {
                MenuInformationPrinter.printDisplayMenuAgain();
                System.out.print(" >>> ");
                yn = scanner.nextLine();
                if (yn.toLowerCase().equals("y")) MenuInformationPrinter.printMenu();
            }
        } while (finishWork);
    }
}