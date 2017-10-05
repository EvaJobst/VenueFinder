package at.jobst.evenly.venuefinder.data;

/**
 * Created by Eva on 30.09.2017.
 */

public class VenueLocation {
    private String address;
    private String distance;
    private String lat;
    private String lng;

    public VenueLocation() {}

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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "VenueLocation{" +
                "address='" + address + '\'' +
                ", distance='" + distance + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
