package com.br.god.father.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static Date fromString(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String fromDate(Date date) {
        if (date != null) return sdf.format(date);

        return null;
    }
}
