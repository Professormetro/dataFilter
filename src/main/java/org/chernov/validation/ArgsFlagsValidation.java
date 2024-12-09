package org.chernov.validation;

import java.util.regex.Pattern;

public class ArgsFlagsValidation {

    private static final String PATH_REGEX = "^(?!/)(?!.*\\\\).+(/.*)?$";
    private static final String PREFIX_REGEX = "^(?!-)[^<>:\"/\\\\|?*]+$";

    public static boolean checkIfRightOutputPath(String outputPath) {
        if (outputPath == null || outputPath.trim().isEmpty()) {
            return false;
        }
        return Pattern.matches(PATH_REGEX, outputPath);

    }

    public static boolean checkIfRightPrefix(String prefix) {

        if (prefix == null || prefix.trim().isEmpty()) {
            return false;
        }

        return Pattern.matches(PREFIX_REGEX, prefix);
    }
}
