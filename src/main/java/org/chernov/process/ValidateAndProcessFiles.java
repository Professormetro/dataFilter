package org.chernov.process;

import org.chernov.dataTypeFilter.DataTypeFilter;
import org.chernov.progressBar.LoadingProgressbar;
import org.chernov.utils.ArgsAndListsByTypes;
import org.chernov.utils.NewArgs;
import org.chernov.utils.TempListsByTypes;
import org.chernov.utils.UtilConfig;
import org.chernov.fileFilter.ArgsFilter;
import org.chernov.validation.ArgsFilesValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ValidateAndProcessFiles  implements ProcessFiles{
    private final ArgsAndListsByTypes listsByType;
    private final TempListsByTypes tempListsByTypes;
    private final DataTypeFilter dataTypeFilter;
    private final ArgsFilter argsFilter;
    private final UtilConfig utilConfig;

    public ValidateAndProcessFiles(ArgsAndListsByTypes listsByType, TempListsByTypes tempListsByTypes, DataTypeFilter dataTypeFilter,
                                   ArgsFilter argsFilter, UtilConfig utilConfig) {
        this.listsByType = listsByType;
        this.tempListsByTypes = tempListsByTypes;
        this.dataTypeFilter = dataTypeFilter;
        this.argsFilter = argsFilter;
        this.utilConfig = utilConfig;
    }

    @Override
    public void validateInput(ArrayList<String> filteredArgs){

        boolean validInput = false;

        while (!validInput) {
            validInput = true;

            for (String filename : filteredArgs) {
                if (filename.endsWith(".txt")) {
                    try {
                        processFileByName(filename);
                        LoadingProgressbar.printProgressBar("Filtering file " + filename + " by types...", 70, 1000);
                        validInput = true;
                    } catch (Exception e) {
                        System.err.println("\n  Error: while reading and processing file " + filename + ": " + e.getMessage());
                        utilConfig.setConfigToDefault();

                        validInput = false;
                    }
                } else if (filename.matches(".*\\.\\p{L}{2,5}")) {
                    System.err.println("\n  Error: File " + filename + " has incorrect extension, our app is only working with .txt files");
                    validInput = false;
                }else{
                    System.err.println("\n  Error: You entered an invalid file " + filename + ", please try again.");
                    validInput = false;
                }
            }

            if (!validInput) {
                System.out.println("\nTry to enter data one more time(separate flags and fileNames by space): ");
                NewArgs.deletePreviousArgsAndInputNew();
                argsFilter.parseArgs(ArgsAndListsByTypes.getArgs());
            }

        }

    }


    @Override
    public void processFileByName(String filename)  {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    if (dataTypeFilter.isInteger(line)) {
                        listsByType.getIntegers().add(Integer.parseInt(line));
                        tempListsByTypes.getIntegers().add(Integer.parseInt(line));
                    } else if (dataTypeFilter.isFloat(line)) {
                        listsByType.getFloats().add(Float.parseFloat(line));
                        tempListsByTypes.getFloats().add(Float.parseFloat(line));
                    } else {
                        listsByType.getStrings().add(line);
                        tempListsByTypes.getStrings().add(line);
                    }
                } catch (NumberFormatException e) {
                    // Пропускаем строку, если не можем преобразовать ее к нужному типу
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("It may not exist!", e);
        }
    }
}
