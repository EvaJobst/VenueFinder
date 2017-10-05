package at.jobst.evenly.venuefinder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;

import at.jobst.evenly.venuefinder.adapter.VenueListAdapter;
import at.jobst.evenly.venuefinder.data.Venue;
import at.jobst.evenly.venuefinder.retrofit.SearchResponse;
import at.jobst.evenly.venuefinder.retrofit.VenueResponse;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {

    @BindView(R.id.rv_main_list)
    RecyclerView recyclerView;

    ArrayList<Venue> venues;
    VenueListAdapter adapter;
    GoogleApiClient client;
    GoogleMap map;

    Callback<VenueResponse> venueResponseCallback = new Callback<VenueResponse>() {
        @Override
        public void onResponse(Call<VenueResponse> call, Response<VenueResponse> response) {
            for(Venue v : venues) {
                if(response.body().getResponse().getVenue().getId().equals(v.getId())) {
                    DetailFragment dialog = DetailFragment.newInstance(response.body().getResponse().getVenue(), v.getLocation().getDistance() + " m");
                    dialog.show(getFragmentManager(), DetailFragment.class.getName());
                }
            }
        }

        @Override
        public void onFailure(Call<VenueResponse> call, Throwable t) {
            Toast.makeText(getBaseContext(), "Error: Failed to load data", Toast.LENGTH_SHORT).show();
        }
    };

    Callback<SearchResponse> searchResponseCallback = new Callback<SearchResponse>() {
        @Override
        public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
            venues.addAll(response.body().getResponse().getVenues());
            Collections.sort(venues, Settings.venueComparator());
            adapter.notifyDataSetChanged();

            if(map != null) {
                loadMarker();
            }
        }

        @Override
        public void onFailure(Call<SearchResponse> call, Throwable t) {
            Toast.makeText(getBaseContext(), "Error: Failed to load data.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        venues = new ArrayList<>();
        adapter = new VenueListAdapter(venues, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(this, Settings.calculateNoOfColumns(this)));
        recyclerView.setVerticalScrollBarEnabled(true);

        Retrofit retrofit = FactoryManager.createRetrofit();
        FoursquareService foursquareService = FactoryManager.createService(retrofit);
        Call<SearchResponse> response = foursquareService.getSearch(
                Settings.V,
                Settings.LL,
                Settings.CLIENT_ID,
                Settings.CLIENT_SECRET
        );

        response.enqueue(searchResponseCallback);

        if (client == null) {
            client = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mv_main_map);
        mapFragment.getMapAsync(this);
    }

    public void loadMarker() {
        LatLngBounds.Builder bounds = LatLngBounds.builder();

        for(Venue v : venues) {
            float lat = Float.parseFloat(v.getLocation().getLat());
            float lng = Float.parseFloat(v.getLocation().getLng());
            LatLng location = new LatLng(lat, lng);
            map.addMarker(new MarkerOptions()
                    .title(v.getName())
                    .snippet(v.getCategories().get(0).getName())
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_maps_marker))
                    .flat(false)
                    .position(location));

            bounds.include(location);
        }

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100));
    }

    @Override
    public void onMapReady(GoogleMap m) {
        map = m;
        map.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        for(Venue v : venues) {
            if(marker.getTitle().equals(v.getName())) {
                Retrofit retrofit = FactoryManager.createRetrofit();
                FoursquareService foursquareService = FactoryManager.createService(retrofit);
                Call<VenueResponse> response = foursquareService.getVenue(
                        v.getId(),
                        Settings.V,
                        Settings.CLIENT_ID,
                        Settings.CLIENT_SECRET
                );

                response.enqueue(venueResponseCallback);

                /*DetailFragment dialog = DetailFragment.newInstance(v.getId(), v.getLocation().getDistance() + " m");
                dialog.show(getFragmentManager(), DetailFragment.class.getName());*/
            }
        }
    }
}
