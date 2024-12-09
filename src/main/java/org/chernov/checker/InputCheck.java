package org.chernov.checker;

import org.chernov.StartApplication;

public class InputCheck {

    public static boolean checkIfProceedFilteringFiles() {
        System.out.print("\nDo you want to filtered more files in this session [Y/N]:");

        boolean wannaProceed = false;
        boolean inputValid = false;

        while(!inputValid) {

            String userInput = StartApplication.scanner.nextLine().trim().toUpperCase();

            switch (userInput) {
                case "Y":
                    wannaProceed = true;
                    inputValid = true;
                    break;

                case "N":
                    wannaProceed = false;
                    inputValid = true;
                    break;
                default:
                    System.out.println("You entered incorrect char. Please enter [Y/N]:");
            }
        }

        return wannaProceed;
    }
}
