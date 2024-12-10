package org.chernov.filesFilter;

import org.chernov.dataTypesFilter.DataTypeFilter;
import org.chernov.utils.LoadingProgressbar;
import org.chernov.utils.ArgsAndListsByTypes;
import org.chernov.utils.DeleteDataAndRestartApp;
import org.chernov.utils.TempListsByTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileFilter implements ProcessFiles{
    private final ArgsAndListsByTypes listsByType;
    private final TempListsByTypes tempListsByTypes;
    private final DataTypeFilter dataTypeFilter;

    public FileFilter(ArgsAndListsByTypes listsByType, TempListsByTypes tempListsByTypes, DataTypeFilter dataTypeFilter) {
        this.listsByType = listsByType;
        this.tempListsByTypes = tempListsByTypes;
        this.dataTypeFilter = dataTypeFilter;
    }

    @Override
    public void filterFilesOrThrowException(ArrayList<String> filteredArgs) {
        for (int i =0; i < filteredArgs.size(); i++) {
            try {
                filterFileByName(filteredArgs.get(i));
            } catch (Exception e) {
                System.err.println("\n  Error: while reading and processing file " + filteredArgs.get(i) + ": " + e.getMessage());
                System.out.println("\nTry to enter data one more time(separate flags and fileNames by space): ");
                DeleteDataAndRestartApp.deletePreviousArgsAndInputNew();
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
        LoadingProgressbar.printProgressBar("Filtering file " + filename + " by types...", 70, 1000);
    }
}

