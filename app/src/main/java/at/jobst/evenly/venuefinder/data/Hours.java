package at.jobst.evenly.venuefinder.data;

/**
 * Created by Eva on 30.09.2017.
 */

public class Hours {
    private String status;

    public String getStatus() {
        if(status.isEmpty()) {
            return "-";
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Hours{" +
                "status='" + status + '\'' +
                '}';
    }
}
