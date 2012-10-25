package com.adg.weather;


import java.util.GregorianCalendar;

import android.util.Log;


public class DateFinder {
	
    public DateFinder() {}
	
    public String dayOfWeek(String date) {
        String dayIs = "fail";
        String line = date;
        String[] parts = line.split("-");

        for (int i = 0; i < parts.length; i++) {
            Log.i("DateFinder" + i, parts[i]);
        }
        int y = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int d = Integer.parseInt(parts[2]);
		
        int number = y - 1900;

        number = (number * 365) + (number / 4);
		
        GregorianCalendar cal = new GregorianCalendar();

        if (cal.isLeapYear(y) && (m == 1 || m == 2)) {
            number--;
        }
        switch (m) {
        case 1:
            number += 0;
            break;

        case 2:
            number += 31;
            break;

        case 3:
            number += 59;
            break;

        case 4:
            number += 90;
            break;

        case 5:
            number += 120;
            break;

        case 6:
            number += 151;
            break;

        case 7:
            number += 181;
            break;

        case 8:
            number += 212;
            break;

        case 9:
            number += 243;
            break;

        case 10:
            number += 273;
            break;

        case 11:
            number += 304;
            break;

        case 12:
            number += 334;
        }
		
        number += d;
        number = number % 7;
		
        switch (number) {
        case 0:
            dayIs = "Sunday";
            break;

        case 1:
            dayIs = "Monday";
            break;

        case 2:
            dayIs = "Tuesday";
            break;

        case 3:
            dayIs = "Wednesday";
            break;

        case 4:
            dayIs = "Thursday";
            break;

        case 5:
            dayIs = "Friday";
            break;

        case 6:
            dayIs = "Saturday";
        }
		
        return dayIs;
    }
}
