package com.thenews.utils;

import java.sql.Date;
import java.time.LocalDate;

public class DateUtils {
    // Phương thức để lấy ngày hiện tại dưới dạng java.sql.Date
    public static Date getCurrentDate() {
        LocalDate localDate = LocalDate.now(); // Lấy ngày hiện tại
        return Date.valueOf(localDate); // Chuyển đổi sang java.sql.Date
    }
}
