package org.chernov.validation;

import org.chernov.statistics.PrintStatistics;

import java.util.Scanner;

public class IfWannaProceedValidator {

    private final Scanner scanner = new Scanner(System.in);

    public boolean checkIfProceedFilteringFiles() {

        boolean wannaProceed = false;
        boolean inputValid = false;

        while (!inputValid) {

            System.out.print("\nDo you want to filtered more files in this session [Y/N]:");
            String userInput = scanner.nextLine().trim().toUpperCase();

            switch (userInput) {
                case "Y":
                    wannaProceed = true;
                    inputValid = true;
                    break;
                case "N":
                    System.out.println("\n  Files were successfully filtered!");
                    System.out.println("\nDataFilter has finished...");
                    System.exit(0);
                    break;
                default:
                    System.out.print("You entered incorrect char. Please enter [Y/N]:");
            }

        }
        return wannaProceed;
    }
}
