package com.tech.springboot.until;


import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateTimeUtils {

//    public static OffsetDateTime convertToOffsetDateTime(String date) {
//        LocalDateTime localDateTime = parseLocalDateTime(date);
//        return localDateTime.atOffset(ZoneOffset.of(ZoneOffset.systemDefault().getId()));
//    }

//    private static LocalDateTime parseLocalDateTime(String date) {
//        return LocalDate.parse(date,
//                DateTimeFormatter.ofPattern(DateTimeFormat.DATETIME_FULL_FORMAT.getValue()))
//            .atStartOfDay();
//    }

    public static OffsetDateTime convertDateToDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+07:00"));
    }

}
