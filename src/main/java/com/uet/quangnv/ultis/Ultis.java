package com.uet.quangnv.ultis;

import com.uet.quangnv.exception.domain.DataFormatWrong;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Ultis {
    public static Date convertStringToDate(String dateString, String format) {
        try {
            return new SimpleDateFormat(format).parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException("Ngày tháng sai định dạng!");
        }
    }

    public interface DateFormat {
        String DDMMYYYY = "dd/mm/yyyy";
        String YYYYMMDD = "yyyy-mm-dd";
    }
}
