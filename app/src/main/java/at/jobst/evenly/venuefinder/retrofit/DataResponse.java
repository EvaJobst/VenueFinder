package at.jobst.evenly.venuefinder.retrofit;

import at.jobst.evenly.venuefinder.data.Venue;

/**
 * Nested element of {@link Data}, which contains the Venue.
 */
public class DataResponse {
    Venue venue;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "DataResponse{" +
                "venue=" + venue +
                '}';
    }
}
