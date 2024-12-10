package org.chernov.dataTypeFilter;

public interface DataTypeFilter {

    boolean isInteger(String line);
    boolean isFloat(String line);
    boolean isArray(String line);
    boolean isImage(String line);
    String detectDataType(String line);
}
