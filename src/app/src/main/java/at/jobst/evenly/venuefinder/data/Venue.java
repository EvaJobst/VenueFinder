package at.jobst.evenly.venuefinder.data;

import java.util.List;

public class Venue {
    private String id;
    private String name;
    private Location location;
    private Hours hours;
    private String description;
    private List<Category> categories;
    private Photo bestPhoto;

    public Venue() {}

    public Venue(String id, String name, Location location, Hours hours, String description, String like, Photo photos) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.description = description;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
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

    public Photo getBestPhoto() {
        return bestPhoto;
    }

    public void setBestPhoto(Photo bestPhoto) {
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
                ", categories=" + categories +
                ", bestPhoto=" + bestPhoto +
                '}';
    }
}
