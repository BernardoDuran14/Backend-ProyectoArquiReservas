package com.example.reservas.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String FORMAT_DATE_TIME="dd-MM-yyyy hh:mm:ss";
    public static final String FORMAT_DATE_TIME_PROCESO="dd/MM/yyyy hh:mm:ss";
    public static final String FORMAT_DATE_TIME_24HRS="yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE="yyyy-MM-dd";
    public static final String FORMAT_DATE_PARAM_URL="dd-MM-yyyy";
    public static final String FORMAT_FILE="yyyy-MM-dd hhmmss";
    public static final String FORMAT_KEY="yyyyMMddHHmmssSSS";
    public static final String FORMAT_ANGULAR="dd/MM/yyyy";
    public static final String FORMAT_DATE_FOLDER_NAME = "dd_MM_yyyy";

    public static Date toDate(String format, String valor){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(format);
            return sdf.parse(valor);
        }
        catch (Exception e){
            return null;
        }
    }

    public static String toString(String format,Date valor){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(format);
            return sdf.format(valor);
        }
        catch (Exception e){
            return null;
        }
    }

    public static boolean between(Date fecha,Date fechaInicio,Date fechaFin){
        if(fecha==null || fechaInicio==null || fechaFin==null){
            return false;
        }
        if(fecha.compareTo(fechaInicio)<0){
            return false;
        }
        if(fecha.compareTo(fechaFin)>0){
            return false;
        }
        return true;
    }



    public static Date formatToStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String format(Date date, String stringFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(stringFormat);
        return dateFormat.format(date);
    }

    public static Date plusMinutes(Date date, int minutesToAdd) {
        Calendar calDateStart = Calendar.getInstance();
        calDateStart.setTime(date);
        calDateStart.add(Calendar.MINUTE, minutesToAdd);

        return calDateStart.getTime();
    }

    public static Date plusMonth(Date date, int monthToAdd) {
        Calendar calDateStart = Calendar.getInstance();
        calDateStart.setTime(date);
        calDateStart.add(Calendar.MONTH, monthToAdd);

        return calDateStart.getTime();
    }
}
