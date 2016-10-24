package com.iva.validation;

public class Validation {
    public Validation() {
    }

    public static boolean validate(String str, String regex) {
        boolean isCorrect = str.matches(regex);
        return isCorrect;
    }
}
