package twitch4j_packages.common.util;

import java.util.Calendar;

public class TimeUtils {

    /**
     * Gets the current time in millis
     *
     * @return long
     */
    public static long getCurrentTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

}
