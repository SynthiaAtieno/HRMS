package com.example.savannahrms.adapters;

import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;

public class DateUtils {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String convertStringToDate(String dateString, String inputFormat, String outputFormat) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

        try {
            Date date = inputDateFormat.parse(dateString);
            return outputDateFormat.format(date);
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }

        return null; // Return null if parsing fails
    }
}
