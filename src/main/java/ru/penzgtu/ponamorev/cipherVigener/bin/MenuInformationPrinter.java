package ru.penzgtu.ponamorev.cipherVigener.bin;

class MenuInformationPrinter {

    static void printIntroduction() {
        System.out.println("=============================================================================================================");
        System.out.println("=== Welcome to the application which can encode/decode your text by cipher of Caesar or cipher of Vigener ===");
        System.out.println("=============================================================================================================\n");

        System.out.println("=============================================================================================================");
        System.out.println("===     For using this application you can use your keyboard or file with text for decoding/encoding      ===");
        System.out.println("=============================================================================================================\n");
    }

    static void printEnterToMenu() {
        System.out.println("=============================================================================================================");
        System.out.println("===     For starting encode/decode enter word \'START\' (not key-sensitive) and press ENTER                 ===");
        System.out.println("===     For exiting from application enter word \'EXIT\' (not key-sensitive) and press ENTER                ===");
        System.out.println("===     Or just close the window :)                                                                       ===");
        System.out.println("=============================================================================================================\n");
    }

    static void printFinishAsking() {
        System.out.println("\n=============================================================================================================");
        System.out.println("===     You finished. Would you like to make something else again? (Y/N)                                  ===");
        System.out.println("=============================================================================================================\n");
    }

    static void printWrongInputFromMenu() {
        System.out.println("\n=============================================================================================================");
        System.out.println("===     You entered something wrong. Would you like to try again? (Y/N)                                   ===");
        System.out.println("=============================================================================================================\n");
    }

    static void printMenu() {
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

    static void printWrongNumberSelectingCipher() {
        System.out.println("=============================================================================================================");
        System.out.println("===     You enter wrong number. Would you like to try again? (Y/N)                                        ===");
        System.out.println("=============================================================================================================\n");
    }

    static void printExitQuestion() {
        System.out.println("=============================================================================================================");
        System.out.println("===     Would you like to exit? (Y/N)                                                                     ===");
        System.out.println("=============================================================================================================\n");
    }

    static void printDisplayMenuAgain() {
        System.out.println("=============================================================================================================");
        System.out.println("===     Print menu again? (Y/N)                                                                           ===");
        System.out.println("=============================================================================================================\n");
    }

    static void printParting() {
        System.out.println("\n=============================================================================================================");
        System.out.println("===     Thank you for using our application! Best wishes. <<<                                             ===");
        System.out.println("=============================================================================================================");
    }
}