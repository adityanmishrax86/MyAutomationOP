package Utils;

public class BasicUtilities {

    public static String generateHexColorString() {
        return String.format("#%06x", (int) (Math.random() * 0xFFFFFF));
    }
}
