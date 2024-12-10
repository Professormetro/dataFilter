package org.chernov.filesFilter;

import java.util.ArrayList;

public interface ProcessFiles {

    void filterFilesOrThrowException(ArrayList<String> filteredArgs);
    void filterFileByName(String filename);

}
