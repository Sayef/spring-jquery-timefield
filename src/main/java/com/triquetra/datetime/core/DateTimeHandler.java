package com.triquetra.datetime.core;


import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.TimeZone;

/**
 * Created by sayef on 11/14/16.
 */
public class DateTimeHandler {

    private String addSeconds(String timeFromJS, String hourFormat){
        String time = "";
        if (hourFormat.equals("12")) {
            time = timeFromJS.substring(0, 5) + ":00" + timeFromJS.substring(5, timeFromJS.length());
        } else if (hourFormat.equals("24")) {
            time = timeFromJS.substring(0, 5) + ":00";
        }
        return time;
    }
    /**
     * checks format validity of the time
     * @param timeFromJS (No seconds are there)
     * @param timeZone
     * @param hourFormat
     * @throws Exception
     */
    private void parseTimeFormatFromJS(String timeFromJS, TimeZone timeZone, String hourFormat) throws Exception {
        DateTimeFormatter formatter = null;
        if (hourFormat.equals("12")) {
            formatter = DateTimeFormat.forPattern("hh:mm:ss a").withZone(DateTimeZone.forTimeZone(timeZone));
        } else if (hourFormat.equals("24")) {
            formatter = DateTimeFormat.forPattern("HH:mm:ss").withZone(DateTimeZone.forTimeZone(timeZone));
        }
        formatter.parseDateTime(timeFromJS);
        LocalTime localTime = LocalTime.parse(timeFromJS, formatter);
    }


    /**
     * checks format validity of the date and time
     * @param dateTimeFromJS
     * @param timeZone
     * @param hourFormat
     */
    private void parseDateTimeFormatFromJS(String dateTimeFromJS, TimeZone timeZone, String hourFormat) {
        DateTimeFormatter formatter = null;
        if (hourFormat.equals("12")) {
            formatter = DateTimeFormat.forPattern("dd.MM.yyyy hh:mm:ss a").withZone(DateTimeZone.forTimeZone(timeZone));
        } else if (hourFormat.equals("24")) {
            formatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss").withZone(DateTimeZone.forTimeZone(timeZone));
        }
        formatter.parseDateTime(dateTimeFromJS);
        LocalTime localTime = LocalTime.parse(dateTimeFromJS, formatter);
    }

    /**
     * checks format validity of the date
     * @param date
     * @param timeZone
     */
    private void parseDateFormatFromJS(String date, TimeZone timeZone) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy").withZone(DateTimeZone.forTimeZone(timeZone));
        formatter.parseDateTime(date);
    }

    /**
     *
     * @param date
     * @param time
     * @param timeZone
     * @param hourFormat
     * @return concatenated date and time which is also a valid format
     * @throws Exception
     */
    public String concatDateTime(String date, String time, TimeZone timeZone, String hourFormat, boolean hasSeconds) throws Exception {

        if (!hasSeconds) {
            time = addSeconds(time, hourFormat);
        }

        String date_time = date + " " + time;
        /*Check whether date is not malformed*/
        parseDateFormatFromJS(date, timeZone);
        /*Check whether time is not malformed*/
        parseTimeFormatFromJS(time, timeZone, hourFormat);
        /*Check whether date_time is not malformed*/
        parseDateTimeFormatFromJS(date_time, timeZone, hourFormat);

        return date_time;
    }

    /**
     *
     * @param dateTime
     * @param hourFormat
     * @return date and time with expected timeFormat ("12"/"24")
     * note: seconds will be truncated
     */
    public String[] splitDateTime(String dateTime, String hourFormat, boolean expectSeconds) {


        DateTimeFormatter formatter = null;
        DateTimeFormatter convertedFormatter = null;
        LocalTime localTime = LocalTime.parse(dateTime, formatter);
        String time = "", date = "", ss = "";

        if(expectSeconds) ss = ":ss";


        if (hourFormat.equals("12")) {
            convertedFormatter = DateTimeFormat.forPattern("hh:mm"+ss+" a");
            time = localTime.toString(convertedFormatter);
        } else if (hourFormat.equals("24")) {
            convertedFormatter = DateTimeFormat.forPattern("HH:mm"+ss);
            time = localTime.toString(convertedFormatter);
        }


        convertedFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        date = localTime.toString(convertedFormatter);

        return new String[]{time, date};
    }

    /**
     *
     * @param time
     * @param convertedFormat ("12"/"24")
     * @param withSeconds
     * @return
     */
    private String convertTimeFormat(String time, String convertedFormat, boolean withSeconds){
        String ss;
        if(withSeconds){
            ss = ":ss";
        }else{
            ss = "";
        }
        DateTimeFormatter convertedFormatter = null;
        if(convertedFormat.equals("12")) {
            convertedFormatter = DateTimeFormat.forPattern("hh:mm"+ss+" a");
        }else {
            convertedFormatter = DateTimeFormat.forPattern("HH:mm"+ss);
        }
        LocalTime localTime = LocalTime.parse(time, convertedFormatter);
        return localTime.toString(convertedFormatter);
    }

}


