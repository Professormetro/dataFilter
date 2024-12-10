package org.chernov.validation;

import org.chernov.statistics.PrintStatisticsByType;

import java.util.Scanner;

public class IfWannaProceedValidator {

    private final PrintStatisticsByType printer;
    private final Scanner scanner = new Scanner(System.in);

    public IfWannaProceedValidator(PrintStatisticsByType printer) {
        this.printer = printer;
    }

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
                    printer.printStatisticsByType();
                    System.out.println("\nFiles were successfully filtered!");
                    System.out.println("\nDataFilter has finished...");
                    wannaProceed = false;
                    inputValid = true;
                    break;
                default:
                    System.out.print("You entered incorrect char. Please enter [Y/N]:");
            }

        }
        return wannaProceed;
    }
}
