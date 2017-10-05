package at.jobst.evenly.venuefinder.retrofit;

import java.util.ArrayList;
import java.util.List;

import at.jobst.evenly.venuefinder.data.Venue;

/**
 * Created by Eva on 30.09.2017.
 */

public class Venues {
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
