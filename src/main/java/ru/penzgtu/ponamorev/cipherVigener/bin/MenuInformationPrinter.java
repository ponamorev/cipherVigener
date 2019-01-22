package ru.penzgtu.ponamorev.cipherVigener.bin;

import ru.penzgtu.ponamorev.cipherVigener.utils.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

class MenuInformationPrinter {
    private static final Logger logger = new Logger();
    private static final String SIGNS_EQUALS = "=========================================================" +
            "====================================================";
    private static final String SIGNS_EQUALS_WITH_ENTER_ABOVE = "\n" + SIGNS_EQUALS;
    private static final String SIGNS_EQUALS_WITH_ENTER_BELOW = SIGNS_EQUALS + "\n";

    static void printIntroduction() {
        System.out.println(SIGNS_EQUALS);
        System.out.println("=== Welcome to the application which can encode/decode your text by cipher of Caesar or cipher of Vigener ===");
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);

        System.out.println(SIGNS_EQUALS);
        System.out.println("===     For using this application you can use your keyboard or file with text for decoding/encoding      ===");
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);
    }

    static void printEnterToMenu() {
        System.out.println(SIGNS_EQUALS);
        System.out.println("===     For starting encode/decode enter word \'START\' (not key-sensitive) and press ENTER                 ===");
        System.out.println("===     For exiting from application enter word \'EXIT\' (not key-sensitive) and press ENTER                ===");
        System.out.println("===     Or just close the window :)                                                                       ===");
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);
    }

    static void printFinishAsking() {
        System.out.println(SIGNS_EQUALS_WITH_ENTER_ABOVE);
        System.out.println("===     You finished. Would you like to make something else again? (Y/N)                                  ===");
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);
    }

    static void printWrongInputFromMenu() {
        System.out.println(SIGNS_EQUALS_WITH_ENTER_ABOVE);
        System.out.println("===     You entered something wrong. Would you like to try again? (Y/N)                                   ===");
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);
    }

    static int printChoosingEncodeDecode(Scanner scanner) {
        System.out.println(SIGNS_EQUALS_WITH_ENTER_ABOVE);
        System.out.println("===     What would you like to do? Choose a number:                                                       ===");
        System.out.println(SIGNS_EQUALS);
        System.out.println("===     1. Encode text                                                                                    ===");
        System.out.println("===     2. Decode text                                                                                    ===");
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);

        System.out.print(" Enter you choice here >>> ");
        return numberEntering(scanner);
    }

    static int printChoosingTextPlace(Scanner scanner,
                                         String action) {
        String insert;
        if (action.equals("encode")) {
            insert = "encoding";
        } else {
            insert = "decoding";
        }
        System.out.println(SIGNS_EQUALS_WITH_ENTER_ABOVE);
        System.out.println("===     Where should we take a text for " + insert + "? Choose a number:                                        ===");
        System.out.println(SIGNS_EQUALS);
        System.out.println("===     1. Console                                                                                        ===");
        System.out.println("===     2. File                                                                                           ===");
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);

        System.out.print(" Enter your choice here >>> ");
        return numberEntering(scanner);
    }

    static int printChoosingCipher(Scanner scanner) {
        System.out.println(SIGNS_EQUALS_WITH_ENTER_ABOVE);
        System.out.println("===     What cipher would you like to use? Choose a number:                                               ===");
        System.out.println(SIGNS_EQUALS);
        System.out.println("===     1. Caesar                                                                                         ===");
        System.out.println("===     2. Vigener                                                                                        ===");
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);

        System.out.print(" Enter your choice here >>> ");
        return numberEntering(scanner);
    }

    static String printChoosingCode(Scanner scanner,
                                    String cipher) {
        System.out.print(" Enter your code word (word, for Vigener cipher) or key (number, for Caesar cipher) here >>> ");
        if (cipher.equals("Caesar")) {
            return String.valueOf(numberEntering(scanner));
        } else {
            return scanner.nextLine();
        }
    }

    static int printChoosingPlaceForOutputResults(Scanner scanner,
                                                     String action) {
        String insert;
        if (action.equals("encode")) {
            insert = "encoding";
        } else {
            insert = "decoding";
        }
        System.out.println(SIGNS_EQUALS_WITH_ENTER_ABOVE);
        System.out.println("===     Where should we take a text for " + insert + "? Choose a number:                                        ===");
        System.out.println(SIGNS_EQUALS);
        System.out.println("===     1. Console                                                                                        ===");
        System.out.println("===     2. File                                                                                           ===");
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);

        System.out.print(" Enter your choice here >>> ");
        return numberEntering(scanner);
    }

    static String printChooseFileName(Scanner scanner,
                                      String action,
                                      String inOut) {
        String insertInput;
        String insertOutput;
        if (action.equals("encode")) {
            insertInput = "encoding";
            insertOutput = "encoded";
        } else {
            insertInput = "decoding";
            insertOutput = "decoded";
        }
        System.out.println(SIGNS_EQUALS_WITH_ENTER_ABOVE);
        if (inOut.equals("in")) {
            System.out.println("===     Enter file name with absolute path for " + insertInput + " text from it                                      ===");
        } else {
            System.out.println("===     Enter file name with absolute path for inserting " + insertOutput + " text there                              ===");
        }
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);

        System.out.print(" Enter file name here >>> ");
        return scanner.nextLine();
    }

    static boolean printQuestionAboutCreatingNonExistingFile(Scanner scanner) {
        System.out.println(SIGNS_EQUALS_WITH_ENTER_ABOVE);
        System.out.println("===     Create file is it is not exist? (Y/N)                                                             ===");
        System.out.println(SIGNS_EQUALS);
        System.out.print(" >>> ");
        String answer = scanner.nextLine();
        return answer.toLowerCase().equals("y");
    }

    static void printWrongNumberSelectingCipher() {
        System.out.println(SIGNS_EQUALS);
        System.out.println("===     You enter wrong number. Would you like to try again? (Y/N)                                        ===");
        System.out.println(SIGNS_EQUALS_WITH_ENTER_BELOW);
    }

    static void printParting() {
        System.out.println(SIGNS_EQUALS_WITH_ENTER_ABOVE);
        System.out.println("===     Thank you for using our application! Best wishes. <<<                                             ===");
        System.out.println(SIGNS_EQUALS);
    }

    private static int numberEntering(Scanner scanner) {
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException ex) {
            logger.error("You entered wrong! Restart the app...");
            return 0;
        }
        scanner.nextLine();
        return choice;
    }
}