package at.jobst.evenly.venuefinder.data;

import java.util.List;

/**
 * Created by Eva on 30.09.2017.
 */

public class Venue {
    private String id;
    private String name;
    private VenueLocation location;
    private Hours hours;
    private String description;
    private String like;
    private List<Category> categories;
    private VenuePhoto bestPhoto;

    public Venue() {}

    public Venue(String id, String name, VenueLocation location, Hours hours, String description, String like, VenuePhoto photos) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.description = description;
        this.like = like;
        this.bestPhoto = photos;
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

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
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

    public VenuePhoto getBestPhoto() {
        return bestPhoto;
    }

    public void setBestPhoto(VenuePhoto bestPhoto) {
        this.bestPhoto = bestPhoto;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", hours=" + hours +
                ", description='" + description + '\'' +
                ", like='" + like + '\'' +
                ", categories=" + categories +
                ", bestPhoto=" + bestPhoto +
                '}';
    }
}
