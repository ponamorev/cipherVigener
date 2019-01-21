package ru.penzgtu.ponamorev.cipherVigener.bin;

import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CaesarCipher;
import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.VigenerCipher;

import java.io.File;
import java.util.Scanner;

public class CipherRunner {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input;
        String yn;

        MenuInformationPrinter.printIntroduction();

        do {
            MenuInformationPrinter.printEnterToMenu();

            System.out.print("Enter here: >>> ");
            input = scanner.nextLine();

            if (input.toLowerCase().equals("start")) {
                do {
                    startApp();
                    MenuInformationPrinter.printFinishAsking();
                } while (scanner.nextLine().toLowerCase().equals("y"));
                break;
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
        CaesarCipher caesar = new CaesarCipher();
        VigenerCipher vigener = new VigenerCipher();
        File fileFrom = null;
        File fileTo = null;
        boolean exitFromLoop, createFileIfItIsNotExist = false;
        String act, placeFrom, placeTo, cipher;
        int choice;

        /*
         *   ######### STEP 1 #########
         */
        do {
            // choose method
            choice = MenuInformationPrinter.printChoosingEncodeDecode(scanner);
            switch (choice) {
                case 1:
                    act = "encode";
                    exitFromLoop = true;
                    break;
                case 2:
                    act = "decode";
                    exitFromLoop = true;
                    break;
                default:
                    MenuInformationPrinter.printWrongNumberSelectingCipher();
                    System.out.print("Your choice >>> ");
                    exitFromLoop = scanner.nextLine().toLowerCase().equals("n");
                    act = null;
                    break;
            }
        } while (!exitFromLoop);

        if (act == null) return;

        /*
         *   ######### STEP 2 #########
         */
        do {
            // choose place for taking text from
            choice = MenuInformationPrinter.printChoosingTextPlace(scanner, act);
            switch (choice) {
                case 1:
                    placeFrom = "console";
                    exitFromLoop = true;
                    break;
                case 2:
                    placeFrom = "file";
                    String fileName = MenuInformationPrinter.printChooseFileName(scanner, act, "in");
                    fileFrom = new File(fileName);
                    exitFromLoop = true;
                    break;
                default:
                    MenuInformationPrinter.printWrongNumberSelectingCipher();
                    System.out.print("Your choice >>> ");
                    exitFromLoop = scanner.nextLine().toLowerCase().equals("n");
                    placeFrom = null;
                    break;
            }
        } while (!exitFromLoop);

        if (placeFrom == null) return;

        /*
         *   ######### STEP 3 #########
         */
        do {
            // choose cipher for encoding/decoding
            choice = MenuInformationPrinter.printChoosingCipher(scanner);
            switch (choice) {
                case 1:
                    cipher = "Caesar";
                    exitFromLoop = true;
                    break;
                case 2:
                    cipher = "Vigener";
                    exitFromLoop = true;
                    break;
                default:
                    MenuInformationPrinter.printWrongNumberSelectingCipher();
                    System.out.print("Your choice >>> ");
                    exitFromLoop = scanner.nextLine().toLowerCase().equals("n");
                    cipher = null;
                    break;
            }
        } while (!exitFromLoop);

        if (cipher == null) return;

        /*
         *   ######### STEP 4 #########
         */
        do {
            choice = MenuInformationPrinter.printChoosingPlaceForOutputResults(scanner, act);
            switch (choice) {
                case 1:
                    placeTo = "console";
                    exitFromLoop = true;
                    break;
                case 2:
                    placeTo = "file";
                    String fileName = MenuInformationPrinter.printChooseFileName(scanner, act, "out");
                    fileTo = new File(fileName);
                    createFileIfItIsNotExist = MenuInformationPrinter.printQuestionAboutCreatingNonExistingFile(scanner);
                    exitFromLoop = true;
                    break;
                default:
                    MenuInformationPrinter.printWrongNumberSelectingCipher();
                    System.out.print("Your choice >>> ");
                    exitFromLoop = scanner.nextLine().toLowerCase().equals("n");
                    placeTo = null;
                    break;
            }
        } while (!exitFromLoop);

        if (placeTo == null) return;

        if (cipher.equals("Caesar")) {
            if (placeFrom.equals("console")) {
                caesar.workWithTextFromConsole(scanner, act, cipher, placeTo, fileTo, createFileIfItIsNotExist);
            } else {
                caesar.workWithTextFromFile(scanner, act, cipher, placeTo, fileFrom, fileTo, createFileIfItIsNotExist);
            }
        } else {
            if (placeFrom.equals("console")) {
                vigener.workWithTextFromConsole(scanner, act, cipher, placeTo, fileTo, createFileIfItIsNotExist);
            } else {
                vigener.workWithTextFromFile(scanner, act, cipher, placeTo, fileFrom, fileTo, createFileIfItIsNotExist);
            }
        }
    }
}