package ua.com.serzh.utils;

/**
 * Created by Serzh on 10/25/16.
 */
public class Validation {

    private final static String PHONE_REGEX = "^((\\+38)-?\\s?)(\\(?0\\d{2}\\)?)?-?\\s?\\d{3}-?\\s?\\d{2}-?\\s?\\d{2}$";

    private final static String THREE_LETTERS = "[A-z]{3,}";
    private final static String FOUR_LETTERS = "[A-z]{4,}";
    private final static String FIVE_LETTERS = "[A-z]{5,}";

    private final static String FIVE_LETTERS_OR_DIGITS = "\\w{5,}";

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validate(String str, String regex) {
        boolean isCorrect = str.matches(regex);
        return isCorrect;
    }

    public static String getPhoneRegex() {
        return PHONE_REGEX;
    }

    public static String getEmailPattern() {
        return EMAIL_PATTERN;
    }

    public static String getThreeLetters() {
        return THREE_LETTERS;
    }

    public static String getFourLettersPattern() {
        return FOUR_LETTERS;
    }

    public static String getFiveLettersPattern() {
        return FIVE_LETTERS;
    }

    public static String getFiveLettersOrDigits() {
        return FIVE_LETTERS_OR_DIGITS;
    }
}
