package Service;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record Date(int day, int month, int year) implements Comparable<Date> {
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int maxDayInMonth(int month, int year) {
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            }
            return 28;
        }

        int[] months = { 4, 6, 9, 11 };
        for (int day : months) {
            if (day == month) {
                return 30;
            }
        }
        return 31;
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

    public static Date today() {
        LocalDate today = LocalDate.now();
        return new Date(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
    }

    public long dayBetween(Date endDate) {
        if (compareTo(endDate) < 0) {
            return calculateDaysBetween(this, endDate);
        } else if (compareTo(endDate) > 0) {
            return calculateDaysBetween(endDate, this);
        }
        return 0;

    }

    @Override
    public int compareTo(Date o) {
        if (this.year < o.year) {
            return -1;
        } else if (this.year > o.year) {
            return 1;
        }
        if (this.month < o.month) {
            return -1;
        } else if (this.month > o.month) {
            return 1;
        }
        if (this.day < o.day) {
            return -1;
        } else if (this.day > o.day) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", day, month, year);
    }

    @Override
    @JsonProperty("day")
    public int day() {
        return day;
    }

    @Override
    @JsonProperty("month")
    public int month() {
        return month;
    }

    @Override
    @JsonProperty("year")
    public int year() {
        return year;
    }

    public Date copy() {
        return new Date(day, month, year);
    }

    public HashMap<String, Integer> mapping() {
        HashMap<String, Integer> map = new HashMap<>();
        map.add("Day", day);
        map.add("Month", month);
        map.add("Year", year);
        return map;
    }
}
