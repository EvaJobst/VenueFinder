package at.jobst.evenly.venuefinder.data;

import java.util.List;

/**
 * Created by Eva on 30.09.2017.
 */

public class Venues {
    List<Venue> venues;

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    @Override
    public String toString() {
        return "Venues{" +
                "venues=" + venues +
                '}';
    }
}
