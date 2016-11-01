package ua.com.serzh.validation;

/**
 * Created by Serzh on 10/25/16.
 */
public class Validation {

    private final static String phoneRegex = "^((\\+38)-?\\s?)(\\(?0\\d{2}\\)?)?-?\\s?\\d{3}-?\\s?\\d{2}-?\\s?\\d{2}$";
    private final static String fourLetters = "\\w{4,}";

    private final static String fiveLetters = "\\w{5,}";

    public static boolean validate(String str, String regex) {
        boolean isCorrect = str.matches(regex);
        return isCorrect;
    }

    public static String getPhoneRegex() {
        return phoneRegex;
    }

    public static String getFourLetters() {
        return fourLetters;
    }

    public static String getFiveLetters() {
        return fiveLetters;
    }
}
