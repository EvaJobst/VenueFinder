package at.jobst.evenly.venuefinder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Eva on 30.09.2017.
 */

public class Settings {
    private static String LATITUDE = "52.500342";
    private static String LONGITUDE = "13.425170";

    private static String createDate() {
        /*Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddmmyyyy");
        return df.format(c.getTime());*/
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public static final String BASE_URL = "https://api.foursquare.com/v2/";
    public static final String OPTIONAL_URL = "venues/search";
    public static final String LL = LATITUDE + "," + LONGITUDE;
    public static final String CLIENT_ID = "SQYKGTJWFL3TZRMZJ3QDZJ3RAIAQO2BKOROVW3YQISALFHBV";
    public static final String CLIENT_SECRET = "ZQ4MBFCO5DYR5RZG1YGWX1TTNVF54JGMUI0TNX3P5AZUSHZ4";
    public static final String V = createDate();
}
