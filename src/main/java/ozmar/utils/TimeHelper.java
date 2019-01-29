package ozmar.utils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TimeHelper {

    private TimeHelper() {

    }


    /**
     * Gets the difference between the two passed times and
     * returns the difference in the format DD:HH:MM:SS
     *
     * @param start Calendar object with desired start time
     * @param end   Calendar object with desired end time
     * @return String
     */
    public static String getTimeDiff(Calendar start, Calendar end) {
        long timeDiff = end.getTimeInMillis() - start.getTimeInMillis();
        System.out.println("MILLIS " + timeDiff);

        long days = (timeDiff / TimeUnit.DAYS.toMillis(1)) % 24;
        long hours = (timeDiff / TimeUnit.HOURS.toMillis(1)) % 60;
        long minutes = (timeDiff / TimeUnit.MINUTES.toMillis(1)) % 60;
        long seconds = (timeDiff / TimeUnit.SECONDS.toMillis(1)) % 60;

        return convertMillisToReadableTime(days, hours, minutes, seconds);
    }


    /**
     * Converts the passed in values in to DD:HH:MM:SS format
     * Format changes slightly if there are zeros for some of the passed in data
     * i.e passing in (0,1,1,1) will return the time in HH:MM:SS
     *
     * @param days    number of days
     * @param hours   number of hours
     * @param minutes number of minutes
     * @param seconds number of seconds
     * @return String
     */
    private static String convertMillisToReadableTime(long days, long hours, long minutes, long seconds) {
        StringBuilder output = new StringBuilder();
        if (days != 0) {
            boolean single = (days == 1);
            output.append(days);
            output.append((single) ? " day, " : " days, ");
        }

        if (days > 0 || hours != 0) {
            boolean single = (hours == 1);
            output.append(hours);
            output.append(single ? " hour, " : " hours, ");
        }

        if (hours > 0 || minutes != 0) {
            boolean single = (minutes == 1);
            output.append(minutes);
            output.append(single ? " minute, and " : " minutes, and ");
        }

        if (minutes > 0 || seconds != 0) {
            boolean single = (seconds == 1);
            output.append(seconds);
            output.append(single ? " second" : " seconds");
        }

        return output.toString();
    }
}
