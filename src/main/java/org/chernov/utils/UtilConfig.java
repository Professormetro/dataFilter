package org.chernov.utils;

public class UtilConfig {

    private String DEFAULT_PREFIX = "";
    private String DEFAULT_PATH = ".";

    private String outputPath = DEFAULT_PATH;
    private String prefix = DEFAULT_PREFIX;

    private boolean append = false;
    private boolean shortStats = false;
    private boolean fullStats = false;

    public void setConfigToDefault(){
        this.outputPath = DEFAULT_PATH;
        this.prefix = DEFAULT_PREFIX;
        this.append = false;
        this.shortStats = false;
        this.fullStats = false;
    }

    public String getDEFAULT_PREFIX() {
        return DEFAULT_PREFIX;
    }

    public void setDEFAULT_PREFIX(String DEFAULT_PREFIX) {
        this.DEFAULT_PREFIX = DEFAULT_PREFIX;
    }

    public String getDEFAULT_PATH() {
        return DEFAULT_PATH;
    }

    public void setDEFAULT_PATH(String DEFAULT_PATH) {
        this.DEFAULT_PATH = DEFAULT_PATH;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isAppend() {
        return append;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public boolean isShortStats() {
        return shortStats;
    }

    public void setShortStats(boolean shortStats) {
        this.shortStats = shortStats;
    }

    public boolean isFullStats() {
        return fullStats;
    }

    public void setFullStats(boolean fullStats) {
        this.fullStats = fullStats;
    }
}
