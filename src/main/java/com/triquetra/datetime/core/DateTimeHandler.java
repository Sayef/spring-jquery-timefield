package com.triquetra.datetime.core;


import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by sayef on 11/14/16.
 */
public class DateTimeHandler {

    private String parse12HoursTimeFormatFromJs(String temp, TimeZone timeZone){
        String time = null;
        try {
            //Our 12-Hours format checker, which is also accepted by Dynamo
            time = temp.substring(0, 5) + ":00" + temp.substring(5, temp.length());
            org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.
                    forPattern("hh:mm:ss a")
                    .withZone(DateTimeZone.forTimeZone(timeZone));
            LocalTime localTime = LocalTime.parse(time, formatter); //formatter.parseDateTime(time);
        }catch (Exception e){
            System.out.println("Exception: " + e + " @parse12HoursTimeFormatFromJs in DateTimeController.");
        }

        return time;
    }

    private String parse24HoursTimeFormatFromJs(String temp, TimeZone timeZone) {
        String time = null;
        try {
            //Our 24-Hours format checker
            time = temp.substring(0, 5) + ":00";
            org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat
                    .forPattern("HH:mm:ss")
                    .withZone(DateTimeZone.forTimeZone(timeZone));
            LocalTime localTime = LocalTime.parse(time, formatter);
        } catch (Exception e) {
            System.out.println("Exception: " + e + " @parse24HoursTimeFormatFromJs in DateTimeController.");
        }

        return time;
    }

    /*
    private String convert24HoursFromatTo12HoursFormat(String time){
        try {
            org.joda.time.format.DateTimeFormatter convertedFormatter = DateTimeFormat.forPattern("hh:mm:ss a");
            LocalTime localTime = LocalTime.parse(time, convertedFormatter);
            time = localTime.toString(convertedFormatter);
        }catch (Exception e) {
            System.out.println("Exception: " + e + " @convert24HoursFromatTo12HoursFormat in DateTimeController.");
        }
        return time;
    }

    private String convert12HoursFromatTo24HoursFormat(String time){
        try {
            org.joda.time.format.DateTimeFormatter convertedFormatter = DateTimeFormat.forPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.parse(time, convertedFormatter);
            time = localTime.toString(convertedFormatter);
        }catch (Exception e) {
            System.out.println("Exception: " + e + " @convert12HoursFromatTo24HoursFormat in DateTimeController.");
        }
        return time;
    }
*/
    private String get12HoursDateTimeFormat(String temp, TimeZone timeZone){
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat
                .forPattern("dd.MM.yyyy hh:mm:ss a")
                .withZone(DateTimeZone.forTimeZone(timeZone));
        formatter.parseDateTime(temp);
        return temp;
    }

    private String get24HoursDateTimeFormat(String temp, TimeZone timeZone){
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat
                .forPattern("dd.MM.yyyy HH:mm:ss")
                .withZone(DateTimeZone.forTimeZone(timeZone));
        formatter.parseDateTime(temp);
        return temp;
    }

    public String resetDateTimeFormat(String date, String time, TimeZone timeZone, String timeFormat) {
        String date_time = date + " " + time;

        /*Check whether date is not malformed*/
        try {
            org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat
                    .forPattern("dd.MM.yyyy")
                    .withZone(DateTimeZone.forTimeZone(timeZone));
            formatter.parseDateTime(date);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e + " while formatting date @getDateTimeFormatToSaveInDB in DateTimeController.");
        }

        /*Check whether time is not malformed*/
        try{
            if (timeFormat.equals("24")) {
                time = parse24HoursTimeFormatFromJs(time, timeZone);
            } else if (timeFormat.equals("12")) {
                time = parse12HoursTimeFormatFromJs(time, timeZone);
            }
        }catch (Exception e){
            System.out.println("Exception: " + e + " while formatting time @getDateTimeFormatToSaveInDB in DateTimeController.");
        }

        /*Check whether date_time is not malformed*/
        try {
            if (timeFormat.equals("24")) {
                date_time = get24HoursDateTimeFormat(date_time, timeZone);
            } else if (timeFormat.equals("12")) {
                date_time = get12HoursDateTimeFormat(date_time, timeZone);
            }
        }catch (Exception e){
            System.out.println("Exception: " + e + " while formatting date_time @getDateTimeFormatToSaveInDB in DateTimeController.");
        }

        return date_time;
    }


    public String[] splitDateTimeFromFromatFrom_ddMMyyy_hhmmss_a(String ddMMyyy_hhmmss_a, String timeFormat){
        String hhmmss_a = null;
        String ddMMyy = null;
        if(!ddMMyyy_hhmmss_a.isEmpty()) {
            try {
                org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat
                        .forPattern("dd.MM.yyyy hh:mm:ss a");
                LocalTime localTime = LocalTime.parse(ddMMyyy_hhmmss_a, formatter);

                if(timeFormat.equals("12")) {
                    org.joda.time.format.DateTimeFormatter convertedFormatter = DateTimeFormat.forPattern("hh:mm a");
                    //LocalDateTime dateTime = formatter.parseLocalDateTime(someTime);
                    hhmmss_a = localTime.toString(convertedFormatter);

                    convertedFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
                    ddMMyy = localTime.toString(convertedFormatter);
                }
                else if(timeFormat.equals("24")) {
                    org.joda.time.format.DateTimeFormatter convertedFormatter = DateTimeFormat.forPattern("HH:mm");
                    //LocalDateTime dateTime = formatter.parseLocalDateTime(someTime);
                    hhmmss_a = localTime.toString(convertedFormatter);

                    convertedFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
                    ddMMyy = localTime.toString(convertedFormatter);
                }

            } catch (IllegalArgumentException ignored) {
                    System.out.println("IllegalArgumentException occured @splitDateTimeFromFromat_ddMMyyy_hhmmss_a in DateTimeHandler");
            }
        }
        return new String[]{hhmmss_a, ddMMyy};
    }

    public String[] splitDateTimeFromFromatFrom_ddMMyyy_hhmmss(String ddMMyyy_hhmmss_a, String timeFormat){
        String hhmmss = null;
        String ddMMyy = null;
        if(!ddMMyyy_hhmmss_a.isEmpty()) {
            try {
                org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat
                        .forPattern("dd.MM.yyyy hh:mm:ss");
                LocalTime localTime = LocalTime.parse(ddMMyyy_hhmmss_a, formatter);

                if(timeFormat.equals("12")) {
                    org.joda.time.format.DateTimeFormatter convertedFormatter = DateTimeFormat.forPattern("hh:mm");
                    //LocalDateTime dateTime = formatter.parseLocalDateTime(someTime);
                    hhmmss = localTime.toString(convertedFormatter);

                    convertedFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
                    ddMMyy = localTime.toString(convertedFormatter);
                }
                else if(timeFormat.equals("24")) {
                    org.joda.time.format.DateTimeFormatter convertedFormatter = DateTimeFormat.forPattern("HH:mm");
                    //LocalDateTime dateTime = formatter.parseLocalDateTime(someTime);
                    hhmmss = localTime.toString(convertedFormatter);

                    convertedFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
                    ddMMyy = localTime.toString(convertedFormatter);
                }

            } catch (IllegalArgumentException ignored) {
                System.out.println("IllegalArgumentException occured @splitDateTimeFromFromat_ddMMyyy_hhmmss in DateTimeHandler");
            }
        }
        return new String[]{hhmmss, ddMMyy};
    }
}
