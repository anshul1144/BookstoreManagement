package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter DB_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DateUtil() {}

    public static String today() {
        return LocalDate.now().format(DB_FORMAT);
    }
}
