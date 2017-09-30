package at.jobst.evenly.venuefinder.data;

import java.util.List;

/**
 * Created by Eva on 30.09.2017.
 */

public class Venue {
    private String id;
    private String name;
    private VenueLocation location;
    private String hours;
    private String description;
    private String like;
    private List<VenuePhoto> photos;

    Venue() {}

    public Venue(String id, String name, VenueLocation location, String hours, String description, String like, List<VenuePhoto> photos) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.description = description;
        this.like = like;
        this.photos = photos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VenueLocation getLocation() {
        return location;
    }

    public void setLocation(VenueLocation location) {
        this.location = location;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public List<VenuePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<VenuePhoto> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "at.jobst.evenly.venuefinder.data.Venue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", hours='" + hours + '\'' +
                ", description='" + description + '\'' +
                ", like='" + like + '\'' +
                ", photos=" + photos +
                '}';
    }
}
