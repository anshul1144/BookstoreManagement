package util;

public class ValidationUtil {

    private ValidationUtil() {}

    public static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isPhone(String phone) {
        return phone != null && phone.matches("^[0-9]{10,15}$");
    }

    public static boolean isPositiveInt(int value) {
        return value > 0;
    }

    public static boolean isPositivePrice(double value) {
        return value >= 0;
    }
}
