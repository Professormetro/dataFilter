package org.chernov.validation;

import org.chernov.utils.ArgsAndListsByTypes;
import org.chernov.utils.NewArgs;

import java.util.ArrayList;
import java.util.List;

public class ArgsFilesValidator {

    public static boolean containsTxtFile(List<String> args) {
        for (String arg : args) {
            if (arg.endsWith(".txt")) {
                return true;
            }
        }
        return false;
    }

    public static void userErrorIfNotContainsTxt(List<String> args) {
        if(!ArgsFilesValidator.containsTxtFile(args)){
            System.out.println("\n  Error: input data does not contains any .txt files: ");
            System.out.println("Try to enter data one more time(separate flags and fileNames by space): ");
            NewArgs.deletePreviousArgsAndInputNew();
        }
    }

}
