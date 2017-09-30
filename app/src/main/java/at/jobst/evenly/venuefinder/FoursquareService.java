package at.jobst.evenly.venuefinder;

import at.jobst.evenly.venuefinder.data.VenueResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Eva on 30.09.2017.
 */

public interface FoursquareService {
    @GET(Settings.OPTIONAL_URL)
    Call<VenueResponse> getResponse(
            @Query("v") String value,
            @Query("ll") String ll,
            @Query("client_id") String id,
            @Query("client_secret") String secret);
}
