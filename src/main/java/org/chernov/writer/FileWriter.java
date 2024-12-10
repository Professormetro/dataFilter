package org.chernov.writer;

import org.chernov.progressBar.LoadingProgressbar;
import org.chernov.utils.UtilConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileWriter implements WriteToFileByType {

    @Override
    public void writeToFile(String filename, List<?> data) {

        if (data.isEmpty()) return;

        createDirectoryIfNotExists(UtilConfig.getOutputPath());

        StringBuilder pathToFile = new StringBuilder();
        pathToFile.append(UtilConfig.getOutputPath() + File.separator + UtilConfig.getPrefix() + filename);

        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(pathToFile.toString(), UtilConfig.isAppend()))) {
            for (Object item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + pathToFile);
        }

    }

    private void createDirectoryIfNotExists(String outputPath) {

        Path path = Paths.get(outputPath);

        try{
            if(Files.notExists(path)) {
                Files.createDirectories(path);
            }
        }catch (IOException e){
            System.err.println("Error creating directory: " + path);
            e.printStackTrace();
        }
    }
}
