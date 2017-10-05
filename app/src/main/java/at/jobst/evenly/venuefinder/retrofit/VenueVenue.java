package at.jobst.evenly.venuefinder.retrofit;

import at.jobst.evenly.venuefinder.data.Venue;

/**
 * Created by Eva on 01.10.2017.
 */

public class VenueVenue {
    Venue venue;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "VenueVenue{" +
                "venue=" + venue +
                '}';
    }
}
