package at.jobst.evenly.venuefinder.data;

/**
 * Created by Eva on 30.09.2017.
 */

public class VenuePhoto {
    private String id;
    private String prefix;
    private String suffix;
    private String visibility;

    VenuePhoto() {}

    public VenuePhoto(String id, String prefix, String suffix, String visibility) {
        this.id = id;
        this.prefix = prefix;
        this.suffix = suffix;
        this.visibility = visibility;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "at.jobst.evenly.venuefinder.data.VenuePhoto{" +
                "id='" + id + '\'' +
                ", prefix='" + prefix + '\'' +
                ", suffix='" + suffix + '\'' +
                ", visibility='" + visibility + '\'' +
                '}';
    }
}
