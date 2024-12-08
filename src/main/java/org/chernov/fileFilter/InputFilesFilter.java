package org.chernov.fileFilter;

public interface InputFilesFilter {

    void parseArgs(String[] args);

    String[] getUpdatedArgs();
}
