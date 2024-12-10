package org.chernov.utils;

import java.util.Scanner;

public class UtilConfig {
    public static Scanner scanner = new Scanner(System.in);
    private final static String DEFAULT_PREFIX = "";
    private final static String DEFAULT_PATH = ".";

    private static String outputPath = DEFAULT_PATH;
    private static String prefix = DEFAULT_PREFIX;

    private static boolean append = false;
    private static boolean shortStats = false;
    private static boolean fullStats = false;

    public static void setConfigToDefault(){
        outputPath = DEFAULT_PATH;
        prefix = DEFAULT_PREFIX;
        append = false;
        shortStats = false;
        fullStats = false;
    }

    public static String getOutputPath() {
        return outputPath;
    }

    public static void setOutputPath(String newOutputPath) {
        outputPath = newOutputPath;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String newPrefix) {
        prefix = newPrefix;
    }

    public static boolean isAppend() {
        return append;
    }

    public static void setAppend(boolean changedAppend) {
        append = changedAppend;
    }

    public static boolean isShortStats() {
        return shortStats;
    }

    public static void setShortStats(boolean changedShortStats) {shortStats = changedShortStats;
    }

    public static boolean isFullStats() {
        return fullStats;
    }

    public static void setFullStats(boolean changedFullStats) {fullStats = changedFullStats;
    }
}
