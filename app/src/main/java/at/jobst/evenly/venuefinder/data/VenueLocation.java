package at.jobst.evenly.venuefinder.data;

/**
 * Created by Eva on 30.09.2017.
 */

public class VenueLocation {
    private String address;
    private String distance;

    VenueLocation() {}

    public VenueLocation(String address, String distance) {
        this.address = address;
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "at.jobst.evenly.venuefinder.data.VenueLocation{" +
                "address='" + address + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
