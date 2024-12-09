package org.chernov.process;

import java.util.ArrayList;
import java.util.List;

public interface ProcessFiles {

    void validateInput(ArrayList<String> filteredArgs);
    void processFileByName(String filename);

}
