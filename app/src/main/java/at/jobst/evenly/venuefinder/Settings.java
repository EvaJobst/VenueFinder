package at.jobst.evenly.venuefinder;

import android.content.Context;
import android.util.DisplayMetrics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import at.jobst.evenly.venuefinder.data.Venue;

/**
 * Created by Eva on 30.09.2017.
 */

public class Settings {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    static final String VENUE_ID = "VENUE_ID";
    static final String VENUE_DISTANCE = "VENUE_DISTANCE";
    static final String GOOGLE_KEY = "AIzaSyB7Bx0NOgvmRdISBiR1GkaBR-pQhhpRDZw";
    private static String LATITUDE = "52.500342";
    private static String LONGITUDE = "13.425170";

    private static String createDate() {
        /*Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddmmyyyy");
        return df.format(c.getTime());*/
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public static final String BASE_URL = "https://api.foursquare.com/v2/";
    public static final String SEARCH_URL = "venues/search";
    public static final String VENUE_URL = "venues/";
    public static final String LL = LATITUDE + "," + LONGITUDE;
    public static final String CLIENT_ID = "SQYKGTJWFL3TZRMZJ3QDZJ3RAIAQO2BKOROVW3YQISALFHBV";
    public static final String CLIENT_SECRET = "ZQ4MBFCO5DYR5RZG1YGWX1TTNVF54JGMUI0TNX3P5AZUSHZ4";
    public static final String V = createDate();

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 250);
        return noOfColumns;
    }

    public static Comparator<Venue> venueComparator() {
        return new Comparator<Venue>() {
            @Override
            public int compare(Venue o1, Venue o2) {
                if(Integer.parseInt(o1.getLocation().getDistance()) <
                        Integer.parseInt(o2.getLocation().getDistance())) {
                    return -1;
                }

                else if(Integer.parseInt(o1.getLocation().getDistance()) >
                        Integer.parseInt(o2.getLocation().getDistance())) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        };
    }
}
