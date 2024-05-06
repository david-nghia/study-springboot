package com.tech.springboot.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DateTimeFormat {
    DATETIME_FULL_FORMAT("yyyy-MM-dd HH:mm:ss"),
    YEAR_MONTH_DAY("yyyy-MM-dd"),
    SORT_YEAR_MONTH_DAY("yyyyMMdd"),
    DAY_MONTH_YEAR("dd/MM/yyyy"),
    MONTH_DAY_YEAR("MM/dd/yyyy"),
    LOCAL_DATE_TIME_RAW_VALUE("yyyy-MM-ddTHH:mm:ss.SSSZ");
    private final String value;
}
