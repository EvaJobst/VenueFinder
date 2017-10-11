package at.jobst.evenly.venuefinder.data;

/**
 * Created by Eva on 30.09.2017.
 */

public class Photo {
    private String id;
    private String prefix;
    private String suffix;
    private String visibility;

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    private String width;
    private String height;

    public Photo() {}

    public Photo(String id, String prefix, String suffix, String visibility) {
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
        return "Photo{" +
                "id='" + id + '\'' +
                ", prefix='" + prefix + '\'' +
                ", suffix='" + suffix + '\'' +
                ", visibility='" + visibility + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }

    /**
     * Every URL of an image is constructed of the prefix, width, height and suffix.
     * @return The URL of the image
     */
    public String getURL() {
        return prefix + width + "x" + height + suffix;
    }
}
