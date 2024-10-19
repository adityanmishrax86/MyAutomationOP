package Utils;

import java.time.format.DateTimeFormatter;

public class BasicUtilities {

    public static String generateHexColorString() {
        return String.format("#%06x", (int) (Math.random() * 0xFFFFFF));
    }

    public static String generateRandomDate(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(java.time.LocalDate.now());
    }
}
