package com.chrissetiana.tictactoe.util;

public class StringUtility {

    public static String stringFromNumbers(int... numbers) {
        StringBuilder builder = new StringBuilder();
        for (int number : numbers) {
            builder.append(number);
        }

        return builder.toString();
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0;
    }
}