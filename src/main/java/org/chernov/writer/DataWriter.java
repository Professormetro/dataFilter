package org.chernov.writer;

import org.chernov.utils.DeleteDataAndRestartApp;
import org.chernov.utils.FlagsConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataWriter implements WriteToFileByType {

    @Override
    public void writeToFile(String filename, List<?> data) {

        if (data.isEmpty()) return;

        createDirectoryIfNotExists(FlagsConfig.getOutputPath());

        StringBuilder pathToFile = new StringBuilder();
        pathToFile.append(FlagsConfig.getOutputPath() + File.separator + FlagsConfig.getPrefix() + filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile.toString(), FlagsConfig.isAppend()))) {
            for (Object item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("\n  Error: writing to file: " + pathToFile);
            System.out.println("\nTry with different files: ");
            DeleteDataAndRestartApp.deletePreviousArgsAndInputNew();
        }
    }

    private void createDirectoryIfNotExists(String outputPath) {

        Path path = Paths.get(outputPath);

        try{
            if(Files.notExists(path)) {
                Files.createDirectories(path);
            }
        }catch (IOException e){
            System.err.println("\n  Error creating directory: " + path);
            System.out.println("\nTry with different outputPath: ");
            DeleteDataAndRestartApp.deletePreviousArgsAndInputNew();
        }
    }
}
