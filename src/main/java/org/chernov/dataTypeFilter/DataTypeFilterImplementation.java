package org.chernov.dataTypeFilter;

import java.util.regex.Pattern;

public class DataTypeFilterImplementation implements DataTypeFilter {

    private static final Pattern INTEGER = Pattern.compile("^-?\\d+$");
    private static final Pattern FLOAT = Pattern.compile("^-?\\d+\\.\\d+$");
    private static final Pattern ARRAY = Pattern.compile("^\\[.*\\]$");
    private static final Pattern IMAGE = Pattern.compile(".*\\.(jpg|jpeg|png|gif)$", Pattern.CASE_INSENSITIVE);

    public boolean isInteger(String line) {
        return INTEGER.matcher(line).matches();
    }

    public boolean isFloat(String line) {
        return FLOAT.matcher(line).matches();
    }

    public boolean isArray(String line) {
        return ARRAY.matcher(line).matches();
    }

    public boolean isImage(String line) {
        return IMAGE.matcher(line).matches();
    }

    public String detectDataType(String line) {
        if (isInteger(line)) {
            return "Integer";
        } else if (isFloat(line)) {
            return "Float";
        } else if (isArray(line)) {
            return "Array";
        } else if (isImage(line)) {
            return "Image";
        } else {
            return "String";
        }
    }




}
