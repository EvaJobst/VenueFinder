package at.jobst.evenly.venuefinder;

import at.jobst.evenly.venuefinder.retrofit.SearchResponse;
import at.jobst.evenly.venuefinder.retrofit.VenueResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Eva on 30.09.2017.
 */

public interface FoursquareService {
    @GET(Settings.SEARCH_URL)
    Call<SearchResponse> getSearch(
            @Query("v") String version,
            @Query("ll") String ll,
            @Query("client_id") String client_id,
            @Query("client_secret") String secret);

    @GET(Settings.VENUE_URL + "{id}")
    Call<VenueResponse> getVenue(
            @Path("id") String id,
            @Query("v") String version,
            @Query("client_id") String client_id,
            @Query("client_secret") String secret
    );
}
