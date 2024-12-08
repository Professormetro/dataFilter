package org.chernov;

import org.chernov.checker.InputCheck;
import org.chernov.config.ApplicationStarter;

import java.util.Scanner;

public class StartApplication {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("\nError: you entered a blank line!");
            System.out.println("Try to enter data one more time(separate flags and fileNames by space): ");
            String input = StartApplication.scanner.nextLine();
            String[] newArgs = input.split("\\s+");
            args = newArgs;
        }

        ApplicationStarter starter = new ApplicationStarter();
        starter.filterFiles(args);
        starter.writeToFiles();

        System.out.println("\nFiles was successfully filtered!");

        boolean proceed = InputCheck.checkIfProceedFilteringFiles();
        while(proceed) {
            System.out.println("Enter new fileNames to proceed filtering: ");
            String newFileInput = scanner.nextLine();
            String[] newArgs = newFileInput.split("\\s+");
            starter.getTempListsByTypes().setListsToDefault();

            starter.filterFiles(newArgs);
            starter.writeToFiles();

            System.out.println("Added files was successfully filtered!");
            proceed = InputCheck.checkIfProceedFilteringFiles();
        }

        starter.printStatistics();

        System.out.println("\nDataFilter has finished...");
        scanner.close();
    }

}
