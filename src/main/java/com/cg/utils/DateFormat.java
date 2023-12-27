package com.cg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormat {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat formatterOut = new SimpleDateFormat("yyyy-MM-dd");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static String format(Date date) {
        return formatter.format(date);
    }

    public static String formatOut(Date date) {return formatterOut.format(date);}
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

    // Hàm kiểm tra xem thời gian đặt lịch có sau thời gian hiện tại không
    public static boolean isAfterBookingTime(String timeBook) {
        LocalTime timeBookLocal = LocalTime.parse(timeBook, timeFormatter);
        return LocalTime.now().isAfter(timeBookLocal);
    }

    // Hàm kiểm tra xem ngày đặt lịch có sau ngày hiện tại không
    public static boolean isAfterBookingDate(String takeDate) {
        LocalDateTime takeDateTime = LocalDateTime.parse(takeDate + " 00:00", dateFormatter);
        return LocalDateTime.now().isAfter(takeDateTime);
    }

    // Hàm kiểm tra xem ngày đặt lịch có trước ngày hiện tại không
    public static boolean isBeforeBookingDate(String takeDate) {
        LocalDateTime takeDateTime = LocalDateTime.parse(takeDate + " 00:00", dateFormatter);
        return LocalDateTime.now().isBefore(takeDateTime);
    }

    // Hàm kiểm tra xem ngày đặt lịch có bằng ngày hiện tại không
    public static boolean isEqualBookingDate(String takeDate) {
        LocalDateTime takeDateTime = LocalDateTime.parse(takeDate + " 00:00", dateFormatter);
        return LocalDateTime.now().isEqual(takeDateTime);
    }

    // Hàm kiểm tra xem thời gian đặt lịch có trước thời gian hiện tại không
    public static boolean isBeforeBookingTime(String timeBook) {
        LocalTime timeBookLocal = LocalTime.parse(timeBook, timeFormatter);
        return LocalTime.now().isBefore(timeBookLocal);
    }
}

