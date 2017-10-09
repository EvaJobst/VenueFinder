package at.jobst.evenly.venuefinder;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import at.jobst.evenly.venuefinder.data.Venue;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Contains static final methods.
 */
public final class Utility {
    public static final void showError(Context context, Throwable throwable) {
        if(throwable instanceof SocketTimeoutException) { // Slow internet
            Toast.makeText(context, "Error: Timeout", Toast.LENGTH_SHORT).show();
        }

        else if(throwable instanceof UnknownHostException) { // No internet
            Toast.makeText(context, "Error: Unable to connect", Toast.LENGTH_SHORT).show();
        }

        else { // Generic error
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public static final Comparator<Venue> createComparator() {
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

    static final String createDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public static final Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Settings.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 250);
        return noOfColumns;
    }
}
