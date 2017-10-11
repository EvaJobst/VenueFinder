package at.jobst.evenly.venuefinder.retrofit;

/**
 * Container that is used when all the Venues are being requested. Data is displayed in {@link at.jobst.evenly.venuefinder.MainActivity} as cards.
 */
public class Search {
    SearchResponse response;

    public SearchResponse getResponse() {
        return response;
    }

    public void setResponse(SearchResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Search{" +
                "response=" + response +
                '}';
    }
}
