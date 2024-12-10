package org.chernov.statistics;

import org.chernov.utils.ArgsAndListsByTypes;
import org.chernov.utils.FlagsConfig;
import java.util.Collections;


public class StatisticsPrinter implements PrintStatisticsByType{

    private final ArgsAndListsByTypes listsByTypes;

    public StatisticsPrinter(ArgsAndListsByTypes listsByTypes) {
        this.listsByTypes = listsByTypes;
    }

    @Override
    public void printStatisticsByType() {
        if (FlagsConfig.isShortStats()) {
            printShortStatistics();
        } else if (FlagsConfig.isFullStats()) {
            printFullStatistics();
        }
    }

    @Override
    public void printShortStatistics() {
        System.out.println("\nShort Statistics:");
        System.out.println("Integers: " + listsByTypes.getIntegers().size());
        System.out.println("Floats: " + listsByTypes.getFloats().size());
        System.out.println("Strings: " + listsByTypes.getStrings().size());
    }

    @Override
    public void printFullStatistics() {
        System.out.println("\nFull Statistics:");

        if (!listsByTypes.getIntegers().isEmpty()) {

            int min = Collections.min(listsByTypes.getIntegers());
            int max = Collections.max(listsByTypes.getIntegers());
            int sum = listsByTypes.getIntegers().stream().mapToInt(Integer::intValue).sum();
            double avg = listsByTypes.getIntegers().stream().mapToInt(Integer::intValue).average().orElse(0.0);

            System.out.println("Integers: amount = " + listsByTypes.getIntegers().size() + ", min = " + min + ", max = " + max + ", sum = " + sum + ", avg = " + avg);
        }

        if (!listsByTypes.getFloats().isEmpty()) {

            float min = Collections.min(listsByTypes.getFloats());
            float max = Collections.max(listsByTypes.getFloats());
            float sum = (float) listsByTypes.getFloats().stream().mapToDouble(Float::doubleValue).sum();
            double avg = listsByTypes.getFloats().stream().mapToDouble(Float::doubleValue).average().orElse(0.0);

            System.out.println("Floats: amount = " + listsByTypes.getFloats().size() + ", min = " + min + ", max = " + max + ", sum = " + sum + ", avg = " + avg);
        }

        if (!listsByTypes.getStrings().isEmpty()) {

            int minLength = listsByTypes.getStrings().stream().mapToInt(String::length).min().orElse(0);
            int maxLength = listsByTypes.getStrings().stream().mapToInt(String::length).max().orElse(0);

            System.out.println("Strings: amount = " + listsByTypes.getStrings().size() + ", shortest = " + minLength + " characters, longest = " + maxLength + " characters");
        }
    }

}
