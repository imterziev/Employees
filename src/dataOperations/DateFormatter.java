package dataOperations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateFormatter {
    public static LocalDate getDate(String dateInfo) {

        List<String> dateFormats = List.of(
                "dd-MM-yyyy",
                "dd-MMM-yyyy",
                "dd.MM.yyyy",
                "dd.MMM.yyyy",
                "dd/MM/yyyy",
                "dd/MMM/yyyy",
                "MMM-dd-yyyy",
                "MMM.dd.yyyy",
                "MMM/dd/yyyy",
                "yyyy-MMM-dd",
                "yyyy.MMM.dd",
                "yyyy/MMM/dd",
                "yyyy-MM-dd"
        );

        LocalDate date = null;

        for (String dateFormat : dateFormats) {

            DateTimeFormatter formatter =
                    new DateTimeFormatterBuilder().parseCaseInsensitive()
                            .appendPattern(dateFormat).toFormatter();

            try {
                date = LocalDate.parse(dateInfo, formatter);
                break;
            } catch (DateTimeParseException e) {
                // handle exception
            }
        }

        return date;
    }
}