package at.jobst.evenly.venuefinder.retrofit;

import java.util.ArrayList;
import at.jobst.evenly.venuefinder.data.Venue;

/**
 * Nested element of {@link Search}, which contains the Venues.
 */
public class SearchResponse {
    ArrayList<Venue> venues;

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }

    @Override
    public String toString() {
        return "Venues{" +
                "venues=" + venues +
                '}';
    }
}