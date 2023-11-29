package Service.components;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Date implements Comparable<Date>, Components {
    private final int day;
    private final int month;
    private final int year;

    public static boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    public static int maxDayInMonth(int month, int year){
        if (month == 2){
            if (isLeapYear(year)){
                return 29;
            }return 28;
        }

        int[] months = { 4 , 6 , 9 , 11};
        for (int day: months){
            if (day == month){
                return 30;
            }
        }
        return 31;
    }
    public Date(int day , int month , int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static long convertDateToNumber(Date date) {
        long days = 0;
        for (int y = 0; y < date.year; y++) {
            days += isLeapYear(y) ? 366 : 365;
        }
        for (int m = 1; m < date.month; m++) {
            days += maxDayInMonth(date.year, m);
        }
        days += date.day;

        return days;
    }

    public static long calculateDaysBetween(Date startDate, Date endDate) {
        return convertDateToNumber(endDate) - convertDateToNumber(startDate);
    }
    public long dayBetween(Date endDate) {
        if (compareTo(endDate) < 0){
            return calculateDaysBetween(this, endDate);
        }else if (compareTo(endDate) > 0){
            return calculateDaysBetween(endDate, this);
        }return 0;

    }
    @Override
    public int compareTo(Date o) {
        if (this.year < o.year){
            return -1;
        }else if(this.year > o.year){
            return 1;
        } if (this.month < o.month){
            return -1;
        } else if(this.month > o.month){
            return 1;
        } if (this.day < o.day){
            return -1;
        }else if(this.day > o.day){
            return 1;
        }return 0;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", day, month, year);
    }
    @JsonProperty("day")
    public int getDay() {
        return day;
    }

    @JsonProperty("month")
    public int getMonth() {
        return month;
    }

    @JsonProperty("year")
    public int getYear() {
        return year;
    }

    @Override
    public HashMap<String> mapping() {
        HashMap<String> map = new HashMap<>();
        map.add("Day", day + "");
        map.add("Month", month + "");
        map.add("Year", year + "");
        return map;
    }
}
