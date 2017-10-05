package at.jobst.evenly.venuefinder.retrofit;

/**
 * Created by Eva on 30.09.2017.
 */

public class SearchResponse {
    Venues response;

    public Venues getResponse() {
        return response;
    }

    public void setResponse(Venues response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "response=" + response +
                '}';
    }
}
