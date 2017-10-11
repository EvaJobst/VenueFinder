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

    /**
     * Shows a Toast-message displaying the error that has occured
     * @param context Application context, usually an activity
     * @param throwable The error that has been thrown
     */
    public static final void showError(Context context, Throwable throwable) {
        // Timeout
        if(throwable instanceof SocketTimeoutException) {
            Toast.makeText(context, "Error: Timeout", Toast.LENGTH_SHORT).show();
        }

        // No internet
        else if(throwable instanceof UnknownHostException) {
            Toast.makeText(context, "Error: Unable to connect", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Creates a new comparator in order to sort the Venues ascending, dependent on the distance.
     * @return
     */
    public static final Comparator<Venue> createComparator() {
        return new Comparator<Venue>() {
            /**
             * The distances of the two values are being compared
             * @param v1 First value to be compared
             * @param v2 Second value to be compared
             * @return Whether the first value is bigger than the second one as a number
             */
            @Override
            public int compare(Venue v1, Venue v2) {
                int distanceV1 = Integer.parseInt(v1.getLocation().getDistance());
                int distanceV2 = Integer.parseInt(v2.getLocation().getDistance());

                if(distanceV1 < distanceV2) {
                    return -1;
                }
                else if(distanceV1 > distanceV2) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        };
    }

    /**
     * Today in the String representation
     * @return The Date as a String
     */
    static final String createDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     *
     * @return
     */
    public static final Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Settings.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Calculates the possible number of columns for a CardView
     * @param context The application context, usually the activity
     * @return The number of columns that can be used for the CardView
     */
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 250);
        return noOfColumns;
    }
}
