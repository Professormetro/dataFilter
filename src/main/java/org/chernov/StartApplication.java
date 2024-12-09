package org.chernov;

import org.chernov.utils.NewArgs;
import org.chernov.utils.UtilConfig;
import org.chernov.validation.ArgsFilesValidator;
import org.chernov.validation.Proceed;
import org.chernov.config.ApplicationStarter;
import org.chernov.utils.ArgsAndListsByTypes;

import java.util.Arrays;

public class StartApplication {


    public static void main(String[] args){

        ArgsAndListsByTypes.getArgs().addAll(Arrays.asList(args));

        if (ArgsAndListsByTypes.getArgs() == null || ArgsAndListsByTypes.getArgs().isEmpty()) {
            System.out.println("\n  Error: you entered a blank line!");
            System.out.println("Try to enter data one more time(separate flags and fileNames by space): ");
            NewArgs.deletePreviousArgsAndInputNew();
        }

        ArgsFilesValidator.userErrorIfNotContainsTxt(ArgsAndListsByTypes.getArgs());

        ApplicationStarter starter = new ApplicationStarter();
        starter.startApplication(ArgsAndListsByTypes.getArgs());
    }

}
