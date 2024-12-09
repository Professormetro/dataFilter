package org.chernov.utils;

import org.chernov.validation.ArgsFilesValidator;
import org.chernov.validation.Proceed;

import java.util.Arrays;

public class NewArgs {

    public static void deletePreviousArgsAndInputNew(){

        ArgsAndListsByTypes.getArgs().clear();
        String newInput = UtilConfig.scanner.nextLine();
        String[] newArgs = newInput.split("\\s+");
        ArgsAndListsByTypes.getArgs().addAll(Arrays.asList(newArgs));
        ArgsFilesValidator.userErrorIfNotContainsTxt(ArgsAndListsByTypes.getArgs());
    }

}
