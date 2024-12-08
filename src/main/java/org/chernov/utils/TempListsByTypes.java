package org.chernov.utils;

import java.util.ArrayList;
import java.util.List;

public class TempListsByTypes {

    private List<Integer> integers = new ArrayList<>();
    private List<Float> floats = new ArrayList<>();
    private List<String> strings = new ArrayList<>();

    public void setListsToDefault(){
        integers.clear();
        floats.clear();
        strings.clear();
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    public List<Float> getFloats() {
        return floats;
    }

    public List<String> getStrings() {
        return strings;
    }

}
