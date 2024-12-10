package org.chernov.utils;

import org.chernov.starter.ApplicationStarter;
import org.chernov.validation.FilesInArgsValidator;

import java.util.Arrays;
import java.util.Scanner;


public class DeleteDataAndRestartApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void deletePreviousArgsAndInputNew(){

            ArgsAndListsByTypes.getArgs().clear();
            FlagsConfig.setConfigToDefault();

            String newInput = scanner.nextLine();
            String[] newArgs = newInput.split("\\s+");
            ArgsAndListsByTypes.getArgs().addAll(Arrays.asList(newArgs));
            FilesInArgsValidator.userErrorIfContainsFilesWithDifferentExtension(ArgsAndListsByTypes.getArgs());

            FilesInArgsValidator.userErrorIfNotContainsTxt(ArgsAndListsByTypes.getArgs());
            ApplicationStarter.startApplication();



    }

}
