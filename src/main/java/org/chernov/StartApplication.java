package org.chernov;

import org.chernov.utils.DeleteDataAndRestartApp;
import org.chernov.utils.FlagsConfig;
import org.chernov.validation.FilesInArgsValidator;
import org.chernov.starter.ApplicationStarter;
import org.chernov.utils.ArgsAndListsByTypes;

import java.util.Arrays;

public class StartApplication {


    public static void main(String[] args){

        ArgsAndListsByTypes.getArgs().addAll(Arrays.asList(args));

        if (ArgsAndListsByTypes.getArgs() == null || ArgsAndListsByTypes.getArgs().isEmpty()) {
            System.out.println("\n  Error: you entered a blank line!");
            System.out.println("\nTry to enter data one more time(separate args by space): ");
            FlagsConfig.setAppend(false);
            DeleteDataAndRestartApp.deletePreviousArgsAndInputNew();
        }

        FilesInArgsValidator.userErrorIfContainsFilesWithDifferentExtension(ArgsAndListsByTypes.getArgs());
        FilesInArgsValidator.userErrorIfNotContainsTxt(ArgsAndListsByTypes.getArgs());

        ApplicationStarter.startApplication();
    }

}
