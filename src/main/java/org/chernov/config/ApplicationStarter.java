package org.chernov.config;

import org.chernov.dataTypeFilter.DataTypeFilter;
import org.chernov.dataTypeFilter.DataTypeFilterImplementation;
import org.chernov.progressBar.LoadingProgressbar;
import org.chernov.utils.ListsByTypes;
import org.chernov.utils.TempListsByTypes;
import org.chernov.utils.UtilConfig;
import org.chernov.fileFilter.InputFilesFilter;
import org.chernov.fileFilter.InputFilesFilterImplementation;
import org.chernov.process.ProcessFiles;
import org.chernov.process.ValidateAndProcessFiles;
import org.chernov.statistics.PrintStatisticsByType;
import org.chernov.statistics.StatisticsPrinter;
import org.chernov.writer.FileWriter;
import org.chernov.writer.WriteToFileByType;

public class ApplicationStarter {
    static ListsByTypes listsByTypes = new ListsByTypes();
    static TempListsByTypes tempListsByTypes = new TempListsByTypes();
    static UtilConfig utilConfig = new UtilConfig();

    static DataTypeFilter dataTypeFilter = new DataTypeFilterImplementation();
    static InputFilesFilter inputFilesFilter = new InputFilesFilterImplementation(utilConfig);
    static ProcessFiles processFiles = new ValidateAndProcessFiles(listsByTypes, tempListsByTypes, dataTypeFilter, inputFilesFilter, utilConfig);

    static PrintStatisticsByType printStatisticsByType = new StatisticsPrinter(listsByTypes, utilConfig);
    static WriteToFileByType writeToFileByType = new FileWriter(utilConfig);
    static LoadingProgressbar progressbar = new LoadingProgressbar();

    public static TempListsByTypes getTempListsByTypes() {
        return tempListsByTypes;
    }

    public static void filterFiles(String[] args){
        inputFilesFilter.parseArgs(args);
        processFiles.validateInput();
        progressbar.printProgressBar("Filtering files: ", 80, 1000);
    }

    public static void writeToFiles(){

        writeToFileByType.writeToFile("integers.txt", tempListsByTypes.getIntegers());
        writeToFileByType.writeToFile("floats.txt", tempListsByTypes.getFloats());
        writeToFileByType.writeToFile("strings.txt", tempListsByTypes.getStrings());
        progressbar.printProgressBar("Writing filtered data to files", 80, 1000);
    }
    public static void printStatistics(){
        printStatisticsByType.printStatisticsByType();
    }
}
