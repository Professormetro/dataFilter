package org.chernov.fileFilter;

import org.chernov.utils.NewArgs;
import org.chernov.validation.ArgsFilesValidator;
import org.chernov.validation.ArgsFlagsValidation;
import org.chernov.validation.Proceed;
import org.chernov.utils.ArgsAndListsByTypes;
import org.chernov.utils.UtilConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterArgsAndDeleteFlags implements ArgsFilter {

    private final UtilConfig config;

    public FilterArgsAndDeleteFlags(UtilConfig config) {
        this.config = config;

    }

    @Override
    public ArrayList<String> parseArgs(List<String> args) {

        for (int i = 0; i < args.size(); i++) {
            switch(args.get(i)){
                case "-o":

                    if(i + 1 < args.size() && !ArgsFlagsValidation.checkIfRightOutputPath(args.get(i + 1))){
                        System.out.println("\n  Error: The output path is incorrect.");
                        System.out.println("\nTry again with path in format [-o newDir/newDir] or [-o newDir]: ");
                        NewArgs.deletePreviousArgsAndInputNew();
                        parseArgs(ArgsAndListsByTypes.getArgs());

                    }else if (i + 1 < args.size()) {
                        config.setOutputPath(args.get(i + 1));
                        args.remove(i);
                        args.remove(i);
                        i--;
                    }
                    break;
                case "-p":
                    if(i + 1 < args.size() && !ArgsFlagsValidation.checkIfRightPrefix(args.get(i + 1))){
                        System.out.println("\n  Error: The prefix is incorrect.");
                        System.out.println("\nTry again with prefix provided: ");
                        NewArgs.deletePreviousArgsAndInputNew();
                        parseArgs(ArgsAndListsByTypes.getArgs());

                    }else if (i + 1 < args.size()){
                        config.setPrefix(args.get(i + 1));
                        args.remove(i);
                        args.remove(i);
                        i--;
                    }
                    break;
                case "-a":
                    config.setAppend(true);
                    args.remove(i);
                    i--;
                    break;
                case "-s":
                    config.setShortStats(true);
                    args.remove(i);
                    i--;
                    break;
                case "-f":
                    config.setFullStats(true);
                    args.remove(i);
                    i--;
                    break;
            }

        }
        return (ArrayList<String>) args;

    }
}
