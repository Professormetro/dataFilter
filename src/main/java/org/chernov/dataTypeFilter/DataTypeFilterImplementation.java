package org.chernov.dataTypeFilter;

public class DataTypeFilterImplementation implements DataTypeFilter {

    public boolean isInteger(String line){
        try{
            Integer.parseInt(line);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public boolean isFloat(String line) {
        try{
            Float.parseFloat(line);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}
