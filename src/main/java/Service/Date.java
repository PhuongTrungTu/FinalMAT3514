package Service;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Represents a date and provides various date-related utility methods.
 */
public class Date implements Comparable<Date> {
    private int day = 1;
    private int month = 1;
    private int year = 2023;

    /**
     * Constructs a Date object with the specified day, month, and year.
     *
     * @param day   The day of the date.
     * @param month The month of the date.
     * @param year  The year of the date.
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Checks if a given year is a leap year.
     *
     * @param year The year to check for leap year.
     * @return {@code true} if the year is a leap year, {@code false} otherwise.
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * Returns the maximum number of days in a given month for a specific year.
     *
     * @param month The month.
     * @param year  The year.
     * @return The maximum number of days in the month.
     */
    public static int maxDayInMonth(int month, int year) {
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            }
            return 28;
        }

        int[] months = {4, 6, 9, 11};
        for (int day : months) {
            if (day == month) {
                return 30;
            }
        }
        return 31;
    }

    /**
     * Converts a date to the total number of days from the beginning of the year 0.
     *
     * @param date The date to convert.
     * @return The total number of days from the beginning of the year 0 to the given date.
     */
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

    /**
     * Calculates the number of days between two dates.
     *
     * @param startDate The start date.
     * @param endDate   The end date.
     * @return The number of days between the start and end dates.
     */
    public static long calculateDaysBetween(Date startDate, Date endDate) {
        return convertDateToNumber(endDate) - convertDateToNumber(startDate);
    }

    /**
     * Returns the current date.
     *
     * @return A Date object representing the current date.
     */
    public static Date today() {
        LocalDate today = LocalDate.now();
        return new Date(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
    }

    /**
     * Calculates the number of days between this date and another date.
     *
     * @param endDate The end date.
     * @return The number of days between this date and the end date.
     */
    public long dayBetween(Date endDate) {
        if (compareTo(endDate) < 0) {
            return calculateDaysBetween(this, endDate);
        } else if (compareTo(endDate) > 0) {
            return calculateDaysBetween(endDate, this);
        }
        return 0;
    }

    /**
     * Compares this date to another date.
     *
     * @param o The date to compare to.
     * @return A negative integer, zero, or a positive integer as this date is less than, equal to, or greater than the specified date.
     */
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

    /**
     * Returns a string representation of the date in the format "dd/MM/yyyy".
     *
     * @return A string representation of the date.
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", day, month, year);
    }

    /**
     * Gets the day of the date.
     *
     * @return The day of the date.
     */
    @JsonProperty("day")
    public int day() {
        return day;
    }

    /**
     * Gets the month of the date.
     *
     * @return The month of the date.
     */
    @JsonProperty("month")
    public int month() {
        return month;
    }

    /**
     * Gets the year of the date.
     *
     * @return The year of the date.
     */
    @JsonProperty("year")
    public int year() {
        return year;
    }

    /**
     * Creates a copy of this date.
     *
     * @return A new Date object with the same day, month, and year.
     */
    public Date copy() {
        return new Date(day, month, year);
    }

    /**
     * Maps the day, month, and year of this date to a HashMap.
     *
     * @return A HashMap containing the day, month, and year as keys with their respective values.
     */
    public HashMap<String, Integer> mapping() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Day", day);
        map.put("Month", month);
        map.put("Year", year);
        return map;
    }
}
