package at.jobst.evenly.venuefinder.retrofit;

import at.jobst.evenly.venuefinder.Settings;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Eva on 30.09.2017.
 */

public interface FoursquareService {
    /**
     * Method creating the Query for searching venues in a certain location.
     * @param version The version, usually the current date
     * @param ll The longitude and latitude of the locaiton
     * @param client_id Access token
     * @param secret Access token
     * @return Query
     */
    @GET(Settings.SEARCH_URL)
    Call<Search> search(
            @Query("v") String version,
            @Query("ll") String ll,
            @Query("client_id") String client_id,
            @Query("client_secret") String secret);

    /**
     * Method creating the Query for fetching a certain venue.
     * @param id The id of the venue
     * @param version The version, usually the current date
     * @param client_id Access token
     * @param secret Access token
     * @return Query
     */
    @GET(Settings.VENUE_URL + "{id}")
    Call<Data> fetch(
            @Path("id") String id,
            @Query("v") String version,
            @Query("client_id") String client_id,
            @Query("client_secret") String secret
    );
}
