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
    ListsByTypes listsByTypes = new ListsByTypes();
    TempListsByTypes tempListsByTypes = new TempListsByTypes();
    UtilConfig utilConfig = new UtilConfig();

    DataTypeFilter dataTypeFilter = new DataTypeFilterImplementation();
    InputFilesFilter inputFilesFilter = new InputFilesFilterImplementation(utilConfig);
    ProcessFiles processFiles = new ValidateAndProcessFiles(listsByTypes, tempListsByTypes, dataTypeFilter, inputFilesFilter, utilConfig);

    PrintStatisticsByType printStatisticsByType = new StatisticsPrinter(listsByTypes, utilConfig);
    WriteToFileByType writeToFileByType = new FileWriter(utilConfig);
    LoadingProgressbar progressbar = new LoadingProgressbar();

    public TempListsByTypes getTempListsByTypes() {
        return tempListsByTypes;
    }

    public void filterFiles(String[] args){
        progressbar.printProgressBar("Filtering files: ", 80, 1000);
        inputFilesFilter.parseArgs(args);
        processFiles.validateInput();
    }

    public void writeToFiles(){
        progressbar.printProgressBar("Writing filtered data to files", 80, 1000);
        writeToFileByType.writeToFile("integers.txt", tempListsByTypes.getIntegers());
        writeToFileByType.writeToFile("floats.txt", tempListsByTypes.getFloats());
        writeToFileByType.writeToFile("strings.txt", tempListsByTypes.getStrings());
    }
    public void printStatistics(){
        printStatisticsByType.printStatisticsByType();
    }
}
