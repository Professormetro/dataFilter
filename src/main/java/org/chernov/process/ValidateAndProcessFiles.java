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

    public ValidateAndProcessFiles(ArgsAndListsByTypes listsByType, TempListsByTypes tempListsByTypes, DataTypeFilter dataTypeFilter) {
        this.listsByType = listsByType;
        this.tempListsByTypes = tempListsByTypes;
        this.dataTypeFilter = dataTypeFilter;
    }

    @Override
    public void validateInput(ArrayList<String> filteredArgs) {
        for (String filename : filteredArgs) {
            try {
                filterFileByName(filename);
                LoadingProgressbar.printProgressBar("Filtering file " + filename + " by types...", 70, 1000);
            } catch (Exception e) {
                System.err.println("\n  Error: while reading and processing file " + filename + ": " + e.getMessage());
                NewArgs.deletePreviousArgsAndInputNew();
            }
        }
    }

    @Override
    public void filterFileByName(String filename)  {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String dataType = dataTypeFilter.detectDataType(line);

                switch (dataType) {
                    case "Integer":
                        listsByType.getIntegers().add(Integer.parseInt(line));
                        tempListsByTypes.getIntegers().add(Integer.parseInt(line));
                        break;
                    case "Float":
                        listsByType.getFloats().add(Float.parseFloat(line));
                        tempListsByTypes.getFloats().add(Float.parseFloat(line));
                        break;
                    case "Array":
                        System.out.println("\n  File " + filename + " contains an array inside, DataFilter will just skip it.");
                        break;
                    case "Image":
                        System.out.println("\n  File " + filename + " contains an image inside, data filter will just skip it.");
                        break;
                    default:
                        listsByType.getStrings().add(line);
                        tempListsByTypes.getStrings().add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("It may not exist!", e);
        }
    }
}

