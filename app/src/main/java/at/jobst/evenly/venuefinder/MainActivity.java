package at.jobst.evenly.venuefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import at.jobst.evenly.venuefinder.data.VenueResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback<VenueResponse> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = FactoryManager.createRetrofit();
        FoursquareService foursquareService = FactoryManager.createService(retrofit);
        Call<VenueResponse> response = foursquareService.getResponse(
                Settings.V,
                Settings.LL,
                Settings.CLIENT_ID,
                Settings.CLIENT_SECRET
        );

        response.enqueue(this);
    }

    @Override
    public void onResponse(Call<VenueResponse> call, Response<VenueResponse> response) {
        Log.d("RESPONSE", response.toString());
        Log.d("RESULTS: ", response.body().getResponse().getVenues().toString());
    }

    @Override
    public void onFailure(Call<VenueResponse> call, Throwable t) {
        Log.d("ERROR", t.getMessage());
        Log.d("REQUEST", call.request().toString());
    }
}
