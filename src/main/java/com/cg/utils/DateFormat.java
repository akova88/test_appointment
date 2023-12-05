package com.cg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormat {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static String format(Date date) {
        return formatter.format(date);
    }
    public static Date parse(String strDate) {
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static LocalDate convertToLocalDate(String date){
        DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //convert String to LocalDate
        return LocalDate.parse(date, formatterLocalDate);
    }
}

