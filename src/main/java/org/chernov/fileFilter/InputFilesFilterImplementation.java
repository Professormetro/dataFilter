package org.chernov.fileFilter;

import org.chernov.process.ProcessFiles;
import org.chernov.utils.UtilConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputFilesFilterImplementation implements InputFilesFilter {


    private String[] updatedArgs;

    private final UtilConfig config;

    public InputFilesFilterImplementation(UtilConfig config) {
        this.config = config;

    }

    @Override
    public void parseArgs(String[] args) {

        List<String> argsList = new ArrayList<>(Arrays.asList(args));

        for (int i = 0; i < argsList.size(); i++) {

            switch(argsList.get(i)){
                case "-o":
                    config.setOutputPath(argsList.get(i + 1));
                    argsList.remove(i);
                    argsList.remove(i);
                    i--;
                    break;
                case "-p":
                    config.setPrefix(argsList.get(i + 1));
                    argsList.remove(i);
                    argsList.remove(i);
                    i--;
                    break;
                case "-a":
                    config.setAppend(true);
                    argsList.remove(i);
                    i--;
                    break;
                case "-s":
                    config.setShortStats(true);
                    argsList.remove(i);
                    i--;
                    break;
                case "-f":
                    config.setFullStats(true);
                    argsList.remove(i);
                    i--;
                    break;
            }

        }
        updatedArgs = argsList.toArray(new String[0]);

    }

    public String[] getUpdatedArgs() {
        return updatedArgs;
    }



}