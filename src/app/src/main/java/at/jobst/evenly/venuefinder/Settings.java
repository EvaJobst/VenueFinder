package at.jobst.evenly.venuefinder;

/**
 * Contains static final String values
 */
public final class Settings {
    // FOURSQUARE ACCESS TOKEN
    public static final String CLIENT_ID = "SQYKGTJWFL3TZRMZJ3QDZJ3RAIAQO2BKOROVW3YQISALFHBV";
    public static final String CLIENT_SECRET = "ZQ4MBFCO5DYR5RZG1YGWX1TTNVF54JGMUI0TNX3P5AZUSHZ4";

    // DEFAULT LOCATION
    public static final String LATITUDE = "52.500342";
    public static final String LONGITUDE = "13.425170";

    // KEYS
    static final String VENUE_DETAILS = "VENUE_DETAILS";
    static final String VENUE_DISTANCE = "VENUE_DISTANCE";

    // RETROFIT PARAMETER
    static final String BASE_URL = "https://api.foursquare.com/v2/";
    public static final String SEARCH_URL = "venues/search";
    public static final String VENUE_URL = "venues/";
    static final String LL = LATITUDE + "," + LONGITUDE;
    public static final String VERSION = Utility.createDate();
}
