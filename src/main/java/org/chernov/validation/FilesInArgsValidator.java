package org.chernov.validation;

import org.chernov.utils.DeleteDataAndRestartApp;
import org.chernov.utils.FlagsConfig;

import java.util.List;

public class FilesInArgsValidator {

    public static boolean containsTxtFile(List<String> args) {
        for (String arg : args) {
            if (arg.endsWith(".txt")) {
                return true;
            }
        }
        return false;
    }

    public static String containsFilesWithDifferentExtension(List<String> args) {

        for (String arg : args) {
             if (arg.endsWith(".txt")) {
                continue;
            }else if(arg.matches(".*\\.\\p{L}{2,5}")){
                return arg;
            }
        }
        return "NotContains";
    }

    public static void userErrorIfNotContainsTxt(List<String> args) {
        if(!FilesInArgsValidator.containsTxtFile(args)){
            System.out.println("\n  Error: input data does not contains any .txt files: ");
            System.out.println("\nTry to enter data one more time(separate flags and fileNames by space): ");
            FlagsConfig.setAppend(false);
            DeleteDataAndRestartApp.deletePreviousArgsAndInputNew();
        }
    }

    public static void userErrorIfContainsFilesWithDifferentExtension(List<String> args) {

        String fileNameIfContains = containsFilesWithDifferentExtension(args);

        if(!fileNameIfContains.equals("NotContains")){
            System.err.println("\n  Error: File " + fileNameIfContains + " has incorrect extension, our app is working only with .txt files");
            System.out.println("\nTry to enter data one more time(separate flags and fileNames by space): ");
            FlagsConfig.setAppend(false);
            DeleteDataAndRestartApp.deletePreviousArgsAndInputNew();
        }
    }

}
