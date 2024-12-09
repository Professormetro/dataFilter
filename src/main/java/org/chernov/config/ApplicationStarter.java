package org.chernov.config;
import org.chernov.utils.NewArgs;
import org.chernov.validation.ArgsFilesValidator;
import org.chernov.validation.Proceed;
import org.chernov.dataTypeFilter.DataTypeFilter;
import org.chernov.dataTypeFilter.DataTypeFilterImplementation;
import org.chernov.progressBar.LoadingProgressbar;
import org.chernov.utils.ArgsAndListsByTypes;
import org.chernov.utils.TempListsByTypes;
import org.chernov.utils.UtilConfig;
import org.chernov.fileFilter.ArgsFilter;
import org.chernov.fileFilter.FilterArgsAndDeleteFlags;
import org.chernov.process.ProcessFiles;
import org.chernov.process.ValidateAndProcessFiles;
import org.chernov.statistics.PrintStatisticsByType;
import org.chernov.statistics.StatisticsPrinter;
import org.chernov.writer.FileWriter;
import org.chernov.writer.WriteToFileByType;
import java.util.List;

public class ApplicationStarter {
    private ArgsAndListsByTypes listsByTypes = new ArgsAndListsByTypes();
    private TempListsByTypes tempListsByTypes = new TempListsByTypes();
    private UtilConfig utilConfig = new UtilConfig();

    private DataTypeFilter dataTypeFilter = new DataTypeFilterImplementation();
    private ArgsFilter argsFilter = new FilterArgsAndDeleteFlags(utilConfig);
    private ProcessFiles processFiles = new ValidateAndProcessFiles(listsByTypes, tempListsByTypes, dataTypeFilter, argsFilter, utilConfig);
    private PrintStatisticsByType printStatisticsByType = new StatisticsPrinter(listsByTypes, utilConfig);
    private Proceed proceed = new Proceed(printStatisticsByType);
    private WriteToFileByType writeToFileByType = new FileWriter(utilConfig);
    private static Thread currentThread;

    public TempListsByTypes getTempListsByTypes() {
        return tempListsByTypes;
    }

    private void filterFiles(List<String> args)throws InterruptedException{
        processFiles.validateInput(argsFilter.parseArgs(args));
    }

    private void writeToFiles(){
        writeToFileByType.writeToFile("integers.txt", tempListsByTypes.getIntegers());
        writeToFileByType.writeToFile("floats.txt", tempListsByTypes.getFloats());
        writeToFileByType.writeToFile("strings.txt", tempListsByTypes.getStrings());
        LoadingProgressbar.printProgressBar("Writing filtered data to files...", 70, 1000);
    }

    public synchronized void startApplication(List<String> args) {
        if (currentThread != null && currentThread.isAlive()) {
            currentThread.interrupt();
            try {
                currentThread.join();
            } catch (InterruptedException e) {
                System.err.println("Текущий поток был прерван во время ожидания завершения другого потока.");

                Thread.currentThread().interrupt();
                return;
            }
        }

        currentThread = new Thread(() -> {
            getTempListsByTypes().setListsToDefault();
            try {
                filterFiles(args);
                writeToFiles();
                ifUserWantToProceed();
            } catch (InterruptedException e) {
                System.err.println("Current stream was interrupted " + e.getMessage());
            }
        });

        currentThread.start();
    }

    public void ifUserWantToProceed() throws InterruptedException{
        boolean wantToProceed = proceed.checkIfProceedFilteringFiles();
        while(wantToProceed) {
            System.out.println("Enter new fileNames to proceed filtering: ");

            NewArgs.deletePreviousArgsAndInputNew();

            Thread newThread = new Thread(() ->{
                try {
                    startApplication(ArgsAndListsByTypes.getArgs());
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            });
            newThread.start();
            newThread.join();



            System.out.println("Added files was successfully filtered!");
            wantToProceed = proceed.checkIfProceedFilteringFiles();
        }
    }
}
