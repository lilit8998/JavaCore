package medicalCenter.dateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat SDTM = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    public static String dateTimeToString(Date dateTime) {
        return SDTM.format(dateTime);
    }

    public static Date stringToDateTime(String dateStr) throws ParseException {
        return SDTM.parse(dateStr);
    }

    public static boolean isSameDay(Date today, Date registerDate) {

        return SDF.format(today).equals(SDF.format(registerDate));

    }
}
