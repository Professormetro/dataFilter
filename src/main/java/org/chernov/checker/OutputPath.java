package org.chernov.checker;

import java.util.regex.Pattern;

public class OutputPath {
    private static final String PATH_REGEX = "^(?!/)(?!.*\\\\).+(/.*)?$";

    public static boolean checkIfRightOutputPath(String outputPath) {

        Pattern pattern = Pattern.compile(PATH_REGEX);
        return pattern.matcher(outputPath).matches();

    }

}
