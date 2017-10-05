package at.jobst.evenly.venuefinder;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import at.jobst.evenly.venuefinder.adapter.VenueListAdapter;
import at.jobst.evenly.venuefinder.data.Venue;
import at.jobst.evenly.venuefinder.data.VenueLocation;
import at.jobst.evenly.venuefinder.data.VenuePhoto;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eva on 30.09.2017.
 */

public class FactoryManager {
    public static Venue createVenue() {
        return new Venue();
    }

    public static VenueLocation createVenueLocation() {
        return new VenueLocation();
    }

    public static VenuePhoto createVenuePhotos() {
        return new VenuePhoto();
    }

    public static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Settings.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static FoursquareService createService(Retrofit retrofit) {
        return retrofit.create(FoursquareService.class);
    }

    static VenueListAdapter createVenueListAdapter(Activity activity) {
        return new VenueListAdapter(activity);
    }

    static VenueListAdapter createVenueListAdapter(ArrayList<Venue> venues, Activity activity) {
        return new VenueListAdapter(venues, activity);
    }
}
