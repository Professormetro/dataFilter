package org.chernov.argsFilter;

import java.util.ArrayList;
import java.util.List;

public interface ArgsFilter {

    ArrayList<String> parseArgs(List<String> args);

}
