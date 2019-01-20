package ru.penzgtu.ponamorev.cipherVigener.bin;

import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CipherCaesarDecode;
import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CipherCaesarEncode;
import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CipherVigenerDecode;
import ru.penzgtu.ponamorev.cipherVigener.cipherLogic.CipherVigenerEncode;

import java.util.Scanner;

public class CipherRunner {
    private static Scanner scanner = new Scanner(System.in);
    private static String input;
    private static String yn;

    public static void main(String[] args) {
        System.out.println("=============================================================================================================");
        System.out.println("=== Welcome to the application which can encode/decode your text by cipher of Caesar or cipher of Vigener ===");
        System.out.println("=============================================================================================================\n");

        System.out.println("=============================================================================================================");
        System.out.println("===     For using this application you can use your keyboard or file with text for decoding/encoding      ===");
        System.out.println("=============================================================================================================\n");

        do {
            System.out.println("=============================================================================================================");
            System.out.println("===     For starting encode/decode enter word \'START\' (not key-sensitive) and press ENTER                 ===");
            System.out.println("===     For exiting from application enter word \'EXIT\' (not key-sensitive) and press ENTER                ===");
            System.out.println("===     Or just close the window :)                                                                       ===");
            System.out.println("=============================================================================================================\n");

            System.out.print("Enter here: >>> ");
            input = scanner.nextLine();

            if (input.toLowerCase().equals("start")) {
                startApp();
                System.out.println("\n=============================================================================================================");
                System.out.println("===     You finished. Would you make something else again? (Y/N)                                          ===");
                System.out.println("=============================================================================================================\n");
                yn = scanner.nextLine();
                if (yn.toLowerCase().equals("n")) {
                    break;
                }
            } else if (!input.toLowerCase().equals("exit")) {
                System.out.println("\n=============================================================================================================");
                System.out.println("===     You entered something wrong. Would you try again? (Y/N)                                           ===");
                System.out.println("=============================================================================================================\n");
                System.out.print(" >>> ");
                yn = scanner.nextLine();
                if (yn.toLowerCase().equals("n")) {
                    break;
                }
            }
        } while (!input.toLowerCase().equals("exit"));

        System.out.println("\n=============================================================================================================");
        System.out.println("===     Thank you for using our application! Best wishes. <<<                                             ===");
        System.out.println("=============================================================================================================");
    }

    private static void startApp() {
        boolean finishWork = false;

        printMenu();

        do {
            System.out.print("Enter here: >>> ");
            input = scanner.nextLine();

            try {
                switch (Integer.parseInt(input)) {
                    case 1:
                        CipherCaesarEncode.encodeTextFromConsole();
                        break;
                    case 2:
                        CipherCaesarEncode.encodeTextFromFile();
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
                        System.out.println("=============================================================================================================");
                        System.out.println("===     You enter wrong number. Would you like to try again? (Y/N)                                        ===");
                        System.out.println("=============================================================================================================\n");
                        System.out.print(" >>> ");
                        yn = scanner.nextLine();
                        if (yn.toLowerCase().equals("y"))
                            continue;
                        break;
                }
            } catch (NumberFormatException nfEx) {
                System.err.println("There was an error during parsing number from string " + input);
                System.err.println(nfEx.getMessage());
            }
            System.out.println("=============================================================================================================");
            System.out.println("===     Would you like to exit? (Y/N)                                                                     ===");
            System.out.println("=============================================================================================================\n");
            System.out.print(" >>> ");
            yn = scanner.nextLine();
            if (yn.toLowerCase().equals("y")) finishWork = true;
            if (!finishWork) {
                System.out.println("=============================================================================================================");
                System.out.println("===     Print menu again? (Y/N)                                                                           ===");
                System.out.println("=============================================================================================================\n");
                System.out.print(" >>> ");
                yn = scanner.nextLine();
                if (yn.toLowerCase().equals("y")) printMenu();
            }
        } while (finishWork);
    }

    private static void printMenu() {
        System.out.println("=============================================================================================================");
        System.out.println("===     START MENU                                                                                        ===");
        System.out.println("=============================================================================================================");
        System.out.println("===     Enter a number accord list below:                                                                 ===");
        System.out.println("===     1. Encode text from console by Caesar cipher                                                      ===");
        System.out.println("===     2. Encode text from file by Caesar cipher                                                         ===");
        System.out.println("===     3. Decode text from console by Caesar cipher                                                      ===");
        System.out.println("===     4. Decode text from file by Caesar cipher                                                         ===");
        System.out.println("===     5. Encode text from console by Vigener cipher                                                     ===");
        System.out.println("===     6. Encode text from file by Vigener cipher                                                        ===");
        System.out.println("===     7. Decode text from console by Vigener cipher                                                     ===");
        System.out.println("===     8. Decode text from file by Vigener cipher                                                        ===");
        System.out.println("=============================================================================================================\n");
    }
}