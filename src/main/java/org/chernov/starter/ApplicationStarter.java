package org.chernov.starter;

import org.chernov.utils.DeleteDataAndRestartApp;
import org.chernov.validation.IfWannaProceedValidator;
import org.chernov.dataTypesFilter.DataTypeFilter;
import org.chernov.dataTypesFilter.DataTypeFilterImplementation;
import org.chernov.utils.LoadingProgressbar;
import org.chernov.utils.ArgsAndListsByTypes;
import org.chernov.utils.TempListsByTypes;
import org.chernov.argsFilter.ArgsFilter;
import org.chernov.argsFilter.FilterArgsAndDeleteFlags;
import org.chernov.filesFilter.ProcessFiles;
import org.chernov.filesFilter.FileFilter;
import org.chernov.statistics.PrintStatisticsByType;
import org.chernov.statistics.StatisticsPrinter;
import org.chernov.writer.DataWriter;
import org.chernov.writer.WriteToFileByType;
import java.util.List;
import java.util.NoSuchElementException;

public class ApplicationStarter {
    private static ArgsAndListsByTypes argsAndListsByTypes = new ArgsAndListsByTypes();
    private static TempListsByTypes tempListsByTypes = new TempListsByTypes();

    private static DataTypeFilter dataTypeFilter = new DataTypeFilterImplementation();
    private static ArgsFilter argsFilter = new FilterArgsAndDeleteFlags();
    private static ProcessFiles processFiles = new FileFilter(argsAndListsByTypes, tempListsByTypes, dataTypeFilter);
    private static PrintStatisticsByType printStatisticsByType = new StatisticsPrinter(argsAndListsByTypes);
    private static IfWannaProceedValidator proceed = new IfWannaProceedValidator(printStatisticsByType);
    private static WriteToFileByType writeToFileByType = new DataWriter();

    public static TempListsByTypes getTempListsByTypes() {
        return tempListsByTypes;
    }

    private static void filterFiles(List<String> args){
        processFiles.filterFilesOrThrowException(argsFilter.parseArgs(args));
    }

    private static void writeToFiles(){
        writeToFileByType.writeToFile("integers.txt", tempListsByTypes.getIntegers());
        writeToFileByType.writeToFile("floats.txt", tempListsByTypes.getFloats());
        writeToFileByType.writeToFile("strings.txt", tempListsByTypes.getStrings());
        LoadingProgressbar.printProgressBar("Writing filtered data to files...", 70, 1000);
    }

    public static void startApplication() {

        try {
            getTempListsByTypes().setListsToDefault();
            filterFiles(ArgsAndListsByTypes.getArgs());
            writeToFiles();
            ifUserWantToProceed();
        } catch (NoSuchElementException e){

            System.out.println("\n  DataFilter was forced to be terminated!\n\nSee you next time...)");
        }catch (IllegalStateException exception){
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

