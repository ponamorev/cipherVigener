package ru.penzgtu.ponamorev.cipherVigener.bin;

import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CaesarCipher;
import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.VigenerCipher;
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
        CaesarCipher caesar = new CaesarCipher();
        VigenerCipher vigener = new VigenerCipher();

        MenuInformationPrinter.printMenu();

        do {
            System.out.print("Enter here: >>> ");
            input = scanner.nextLine();

            try {
                switch (Integer.parseInt(input)) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
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