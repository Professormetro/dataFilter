package org.chernov.validation;

import java.util.regex.Pattern;

public class FlagsInArgsValidator {

    private static final String OUTPUTPATH_AND_PREFIX_VALID = "^(?!/)(?!.*\\\\)(?!-p|-[o|f|s|a]).+(/.*)?$";

    public static boolean checkIfRightOutputPath(String outputPath) {
        if (outputPath == null || outputPath.trim().isEmpty()|| outputPath.endsWith(".txt")) {
            return false;
        }
        return Pattern.matches(OUTPUTPATH_AND_PREFIX_VALID, outputPath);

    }

    public static boolean checkIfRightPrefix(String prefix) {

        if (prefix == null || prefix.trim().isEmpty() || prefix.endsWith(".txt")) {
            return false;
        }

        return Pattern.matches(OUTPUTPATH_AND_PREFIX_VALID, prefix);
    }
}
