package at.jobst.evenly.venuefinder.retrofit;

import at.jobst.evenly.venuefinder.data.Venue;

/**
 * Created by Eva on 01.10.2017.
 */

public class VenueResponse {
    VenueVenue response;

    public VenueVenue getResponse() {
        return response;
    }

    public void setResponse(VenueVenue response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "VenueResponse{" +
                "response=" + response +
                '}';
    }
}
