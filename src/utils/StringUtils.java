package utils;

public abstract class StringUtils {

    static public String stringArrayToString(String[] strArray) {
        return String.join("", strArray);
    }

    static  public String getLetterFromNumber(int n) {
        return String.valueOf((char) ((int) n + 33));
    }

    static  public String getRandomLetter() {
        return getLetterFromNumber((int) Math.round(Math.random() * 94));
    }
}
