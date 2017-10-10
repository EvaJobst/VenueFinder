package at.jobst.evenly.venuefinder.retrofit;

/**
 * Container that is used when a specific Venue is being fetched. Data is displayed in {@link at.jobst.evenly.venuefinder.DetailFragment} as a dialog.
 */
public class Data {
    DataResponse response;

    public DataResponse getResponse() {
        return response;
    }

    public void setResponse(DataResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Data{" +
                "response=" + response +
                '}';
    }
}
