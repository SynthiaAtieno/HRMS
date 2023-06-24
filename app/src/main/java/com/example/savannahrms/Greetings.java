package com.example.savannahrms;

import java.util.Calendar;

public class Greetings {

    public static String getGreeting() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        String greeting;
        if (hourOfDay < 12) {
            greeting = "Good morning, ";
        } else if (hourOfDay < 17) {
            greeting = "Good afternoon, ";
        } else {
            greeting = "Good evening, ";
        }

        return greeting;
    }
}
