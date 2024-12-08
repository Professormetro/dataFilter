package org.chernov.process;

import org.chernov.StartApplication;
import org.chernov.dataTypeFilter.DataTypeFilter;
import org.chernov.utils.ListsByTypes;
import org.chernov.utils.TempListsByTypes;
import org.chernov.utils.UtilConfig;
import org.chernov.fileFilter.InputFilesFilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ValidateAndProcessFiles  implements ProcessFiles{
    private final ListsByTypes listsByType;
    private final TempListsByTypes tempListsByTypes;
    private final DataTypeFilter dataTypeFilter;
    private final InputFilesFilter inputFilesFilter;
    private final UtilConfig utilConfig;

    public ValidateAndProcessFiles(ListsByTypes listsByType, TempListsByTypes tempListsByTypes, DataTypeFilter dataTypeFilter,
                                   InputFilesFilter inputFilesFilter, UtilConfig utilConfig) {
        this.listsByType = listsByType;
        this.tempListsByTypes = tempListsByTypes;
        this.dataTypeFilter = dataTypeFilter;
        this.inputFilesFilter = inputFilesFilter;
        this.utilConfig = utilConfig;
    }



    @Override
    public void validateInput() {
        String[] args = inputFilesFilter.getUpdatedArgs();
        boolean validInput = false;

        while (!validInput) {
            validInput = true;

            for (String filename : args) {
                if (filename.endsWith(".txt")) {
                    try {
                        processFileByName(filename);
                    } catch (Exception e) {
                        System.err.println("\nError while reading and processing file " + filename + ": " + e.getMessage());
                        utilConfig.setConfigToDefault();
                        validInput = false;
                    }
                } else if (filename.matches(".*\\.\\p{L}{2,5}")) {
                    System.err.println("\n  File " + filename + " has incorrect extension, our app is only working with .txt files(((");
                    validInput = false;
                }else{
                    System.err.println("\n  You entered an invalid file, please try again.");
                    validInput = false;
                }
            }

            if (!validInput) {
                System.out.println("\nTry to enter data one more time(separate flags and fileNames by space): ");
                String input = StartApplication.scanner.nextLine();
                String[] newArgs = input.split("\\s+");
                inputFilesFilter.parseArgs(newArgs);
                args = newArgs;
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
                    // Пропускаем строку, если она не может быть преобразована в нужный тип
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("It may not exist!", e);
        }
    }
}
