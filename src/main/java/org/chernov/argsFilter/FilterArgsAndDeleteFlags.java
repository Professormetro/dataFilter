package org.chernov.argsFilter;

import org.chernov.utils.DeleteDataAndRestartApp;
import org.chernov.validation.FlagsInArgsValidator;
import org.chernov.utils.ArgsAndListsByTypes;
import org.chernov.utils.FlagsConfig;
import java.util.ArrayList;
import java.util.List;

public class FilterArgsAndDeleteFlags implements ArgsFilter {

    @Override
    public ArrayList<String> parseArgs(List<String> args) {

        for (int i = 0; i < args.size(); i++) {
            switch(args.get(i)){
                case "-o":
                    if(i + 1 < args.size() && !FlagsInArgsValidator.checkIfRightOutputPath(args.get(i + 1))){
                        System.out.println("\n  Error: The output path is incorrect. Output path cannot be empty, starts with [/], ends with [.txt] or flags [-p, -o, -f, -s, -a].");
                        System.out.println("\nTry again with path in format [-o newDir/newDir] or [-o newDir]: ");
                        DeleteDataAndRestartApp.deletePreviousArgsAndInputNew();
                        parseArgs(ArgsAndListsByTypes.getArgs());

                    }else if (i + 1 < args.size()) {
                        FlagsConfig.setOutputPath(args.get(i + 1));
                        args.remove(i);
                        args.remove(i);
                        i--;
                    }
                    break;
                case "-p":
                    if(i + 1 < args.size() && !FlagsInArgsValidator.checkIfRightPrefix(args.get(i + 1))){
                        System.out.println("\n  Error: The prefix is incorrect. Prefix cannot be empty, ends with .txt or flags(-p, -o, -f, -s, -a).");
                        System.out.println("\nTry again with correct prefix provided: ");
                        DeleteDataAndRestartApp.deletePreviousArgsAndInputNew();
                        parseArgs(ArgsAndListsByTypes.getArgs());

                    }else if (i + 1 < args.size()){
                        FlagsConfig.setPrefix(args.get(i + 1));
                        args.remove(i);
                        args.remove(i);
                        i--;
                    }
                    break;
                case "-a":
                    FlagsConfig.setAppend(true);
                    args.remove(i);
                    i--;
                    break;
                case "-s":
                    FlagsConfig.setShortStats(true);
                    args.remove(i);
                    i--;
                    break;
                case "-f":
                    FlagsConfig.setFullStats(true);
                    args.remove(i);
                    i--;
                    break;
            }
        }
        return (ArrayList<String>) args;

    }
}
