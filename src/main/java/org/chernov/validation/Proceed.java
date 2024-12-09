package org.chernov.validation;

import org.chernov.config.ApplicationStarter;
import org.chernov.statistics.PrintStatisticsByType;
import org.chernov.statistics.StatisticsPrinter;
import org.chernov.utils.UtilConfig;

public class Proceed {

    private final PrintStatisticsByType printer;

    public Proceed(PrintStatisticsByType printer) {
        this.printer = printer;
    }

    public boolean checkIfProceedFilteringFiles() {
        System.out.print("\nDo you want to filtered more files in this session [Y/N]:");

        boolean wannaProceed = false;
        boolean inputValid = false;

        while(!inputValid) {

            String userInput = UtilConfig.scanner.nextLine().trim().toUpperCase();

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
