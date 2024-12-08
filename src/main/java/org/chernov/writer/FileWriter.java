package org.chernov.writer;

import org.chernov.utils.UtilConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileWriter implements WriteToFileByType {

    private final UtilConfig config;

    public FileWriter(UtilConfig config) {
        this.config = config;
    }

    @Override
    public void writeToFile(String filename, List<?> data) {
        if (data.isEmpty()) return;

        createDirectoryIfNotExists(config.getOutputPath());

        StringBuilder pathToFile = new StringBuilder();
        pathToFile.append(config.getOutputPath() + File.separator + config.getPrefix() + filename);

        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(pathToFile.toString(), config.isAppend()))) {
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
