package at.jobst.evenly.venuefinder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.Collections;
import at.jobst.evenly.venuefinder.adapter.VenueListAdapter;
import at.jobst.evenly.venuefinder.data.Venue;
import at.jobst.evenly.venuefinder.retrofit.Data;
import at.jobst.evenly.venuefinder.retrofit.FoursquareService;
import at.jobst.evenly.venuefinder.retrofit.Search;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener
{
    @BindView(R.id.rv_main_list)        RecyclerView recyclerView;
    @BindView(R.id.pb_main_progress)    public ProgressBar progressBar;

    ArrayList<Venue> venues;
    VenueListAdapter adapter;
    GoogleMap map;

    Callback<Search> searchCallback = createSearchCallback();
    Callback<Data> dataCallback = createDataCallback();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Set up lists and Recycler View
        venues = new ArrayList<>();
        adapter = new VenueListAdapter(venues, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, Utility.calculateNoOfColumns(this)));

        // Start fetching venues
        Retrofit retrofit = Utility.createRetrofit();
        FoursquareService foursquareService = retrofit.create(FoursquareService.class);
        Call<Search> response = foursquareService.search(
                Settings.VERSION,
                Settings.LL,
                Settings.CLIENT_ID,
                Settings.CLIENT_SECRET
        );

        response.enqueue(searchCallback);
        progressBar.setVisibility(View.VISIBLE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mv_main_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap m) {
        map = m;
        map.setOnInfoWindowClickListener(this);
    }

    /**
     * Checks for a match and fetches detail information on the venue.
     * @param marker Marker, whose Info-Window, has been clicked
     */
    @Override
    public void onInfoWindowClick(Marker marker) {
        for(Venue v : venues) {
            if(marker.getTitle().equals(v.getName())) {
                Retrofit retrofit = Utility.createRetrofit();
                FoursquareService foursquareService = retrofit.create(FoursquareService.class);
                Call<Data> response = foursquareService.fetch(
                        v.getId(),
                        Settings.VERSION,
                        Settings.CLIENT_ID,
                        Settings.CLIENT_SECRET
                );

                response.enqueue(dataCallback);
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Shows the detail dialog on successful response or a message when an error has occured.
     * @return Callback containing the respective methods
     */
    public Callback<Data> createDataCallback() {
        return new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                for(Venue v : venues) {
                    if(response.body().getResponse().getVenue().getId().equals(v.getId())) {
                        DetailFragment dialog = DetailFragment.newInstance(response.body().getResponse().getVenue(), v.getLocation().getDistance() + " m");
                        dialog.show(getFragmentManager(), DetailFragment.class.getName());
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Utility.showError(getBaseContext(), t);
                progressBar.setVisibility(View.INVISIBLE);
            }
        };
    }

    /**
     * On successful fetch the data will be added to the list, sorted in ascending order and updated on map and cards. On failure a message is shown.
     * @return Callback containing respective methods
     */
    public Callback<Search> createSearchCallback() {
        return new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                venues.addAll(response.body().getResponse().getVenues());
                Collections.sort(venues, Utility.createComparator());
                adapter.notifyDataSetChanged();

                if(map != null) {
                    loadMarker();
                }

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                Utility.showError(getBaseContext(), t);
                progressBar.setVisibility(View.INVISIBLE);
            }
        };
    }

    /**
     * The marker on the venue locations are being shown with custom images. The camera focuses on the bounding box of the marker.
     */
    public void loadMarker() {
        LatLngBounds.Builder bounds = LatLngBounds.builder();

        // Add Venue marker
        for(Venue v : venues) {
            float lat = Float.parseFloat(v.getLocation().getLat());
            float lng = Float.parseFloat(v.getLocation().getLng());
            LatLng location = new LatLng(lat, lng);

            map.addMarker(new MarkerOptions()
                    .title(v.getName())
                    .snippet(v.getCategories().get(0).getName())
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMarker()))
                    .position(location));

            bounds.include(location);
        }

        // Add Start marker
        float startLat = Float.parseFloat(Settings.LATITUDE);
        float startLng = Float.parseFloat(Settings.LONGITUDE);
        LatLng startPosition = new LatLng(startLat, startLng);
        map.addMarker(new MarkerOptions()
                    .position(startPosition));

        bounds.include(startPosition);

        // Focus camera to the bounding box of the marker
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100));
    }

    /**
     * Loads Bitmap from Resource and returns the resized image.
     * @return Resized Bitmap
     */
    public Bitmap resizeMarker() {
        Bitmap marker = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("ic_maps_marker", "mipmap", getPackageName()));
        return Bitmap.createScaledBitmap (marker, 60, 60, true);
    }
}
