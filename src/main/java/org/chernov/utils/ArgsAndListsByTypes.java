package org.chernov.utils;


import java.util.ArrayList;
import java.util.List;

public class ArgsAndListsByTypes {

    private static List<String> args = new ArrayList<>();
    private List<Integer> integers = new ArrayList<>();
    private List<Float> floats = new ArrayList<>();
    private List<String> strings = new ArrayList<>();

    public static List<String> getArgs() {
        return args;
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
