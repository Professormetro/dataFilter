package org.chernov.utils;

public class LoadingProgressbar {

    public static void printProgressBar(String message, int length, long totalTime) {
        char incomplete = '░';
        char complete = '█';

        System.out.println("\n" + message);

        for (int i = 0; i <= length; i++) {
            StringBuilder bar = new StringBuilder();
            for (int j = 0; j < length; j++) {
                if (j < i) {
                    bar.append(complete);
                } else {
                    bar.append(incomplete);
                }
            }
            System.out.print("\r" + bar.toString());
            try {
                Thread.sleep(totalTime / length);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}
