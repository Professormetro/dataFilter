package org.chernov.starter;

import org.chernov.utils.DeleteDataAndRestartApp;
import org.chernov.validation.IfWannaProceedValidator;
import org.chernov.dataTypesFilter.DataTypeFilter;
import org.chernov.dataTypesFilter.DataTypeFilterImplementation;
import org.chernov.utils.LoadingProgressbar;
import org.chernov.utils.ArgsAndListsByTypes;
import org.chernov.argsFilter.ArgsFilter;
import org.chernov.argsFilter.FilterArgsAndDeleteFlags;
import org.chernov.filesFilter.ProcessFiles;
import org.chernov.filesFilter.FileFilter;
import org.chernov.statistics.PrintStatistics;
import org.chernov.statistics.Statistics;
import org.chernov.writer.DataWriter;
import org.chernov.writer.WriteToFileByType;
import java.util.List;
import java.util.NoSuchElementException;

public class ApplicationStarter {
    private static final ArgsAndListsByTypes argsAndListsByTypes = new ArgsAndListsByTypes();

    private static final DataTypeFilter dataTypeFilter = new DataTypeFilterImplementation();
    private static final ArgsFilter argsFilter = new FilterArgsAndDeleteFlags();
    private static final ProcessFiles processFiles = new FileFilter(argsAndListsByTypes, dataTypeFilter);
    private static final PrintStatistics statistics = new Statistics(argsAndListsByTypes);
    private static final IfWannaProceedValidator proceed = new IfWannaProceedValidator();
    private static final WriteToFileByType writeToFileByType = new DataWriter();


    private static void filterFiles(List<String> args){
        processFiles.filterFilesOrThrowException(argsFilter.parseArgs(args));
    }

    private static void writeToFiles(){
        writeToFileByType.writeToFile("integers.txt", argsAndListsByTypes.getIntegers());
        writeToFileByType.writeToFile("floats.txt", argsAndListsByTypes.getFloats());
        writeToFileByType.writeToFile("strings.txt", argsAndListsByTypes.getStrings());
        LoadingProgressbar.printProgressBar("Writing filtered data to files...", 70, 1000);
    }

    public static void startApplication() {

        try {
            filterFiles(ArgsAndListsByTypes.getArgs());
            writeToFiles();
            statistics.printStatistics();
            ifUserWantToProceed();
        } catch (NoSuchElementException | IllegalStateException e){
            System.out.println("\n  DataFilter was forced to be terminated!\n\nSee you next time...)");
            System.exit(0);
        }
    }

    public static void ifUserWantToProceed() {
        boolean wantToProceed = proceed.checkIfProceedFilteringFiles();
        if(wantToProceed) {
            System.out.println("Enter new args to proceed filtering: ");
            DeleteDataAndRestartApp.deletePreviousArgsAndInputNew();
        }
    }
}

