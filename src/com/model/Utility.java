package model;

import java.util.Arrays;

public class Utility {

    public static String[] createHeadersArray(int n) {
        String[] header = new String[n];
        int asciiValue = 65; // ASCII value for 'A'

        for (int i = 0; i < n; i++) {
            header[i] = Character.toString((char) asciiValue);
            asciiValue++;
        }
        return header;
    }

    public static String arrayToString(String[] a) {
        String result = Arrays.toString(a)
                .replace("[", "")
                .replace("]", "")
                .replace(",", ", ");
        return result;
    }
}
