package org.chernov.dataTypeFilter;

public interface DataTypeFilter {

    boolean isInteger(String line);
    boolean isFloat(String line);

}
