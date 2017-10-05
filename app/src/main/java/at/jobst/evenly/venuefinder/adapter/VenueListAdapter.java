package at.jobst.evenly.venuefinder.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import at.jobst.evenly.venuefinder.DetailFragment;
import at.jobst.evenly.venuefinder.FactoryManager;
import at.jobst.evenly.venuefinder.FoursquareService;
import at.jobst.evenly.venuefinder.R;
import at.jobst.evenly.venuefinder.Settings;
import at.jobst.evenly.venuefinder.data.Venue;
import at.jobst.evenly.venuefinder.retrofit.SearchResponse;
import at.jobst.evenly.venuefinder.retrofit.VenueResponse;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Eva on 30.09.2017.
 */

public class VenueListAdapter extends RecyclerView.Adapter<VenueListAdapter.VenueViewHolder> {
    ArrayList<Venue> venueList;
    Activity activity;

    public VenueListAdapter(Activity a) {
        venueList = new ArrayList<>();
        activity = a;
    }

    public VenueListAdapter(ArrayList<Venue> venues, Activity a) {
        venueList = venues;
        activity = a;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new VenueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        holder.id = venueList.get(position).getId();
        holder.name.setText(venueList.get(position).getName());
        holder.distance.setText(venueList.get(position).getLocation().getDistance() + " m");
        holder.address.setText(venueList.get(position).getLocation().getAddress());
        if(venueList.get(position).getCategories().get(0) != null) {
            holder.category.setText(venueList.get(position).getCategories().get(0).getName());
        }
    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }

    public ArrayList<Venue> getVenueList() {
        return venueList;
    }

    public void setVenueList(ArrayList<Venue> venueList) {
        this.venueList = venueList;
    }



    class VenueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        String id;
        @BindView(R.id.tv_item_name) TextView name;
        @BindView(R.id.tv_item_distance) TextView distance;
        @BindView(R.id.tv_item_category) TextView category;
        @BindView(R.id.tv_item_address) TextView address;

        Callback<VenueResponse> venueResponseCallback = new Callback<VenueResponse>() {
            @Override
            public void onResponse(Call<VenueResponse> call, Response<VenueResponse> response) {
                /*DetailFragment dialog = DetailFragment.newInstance(id, distance.getText().toString());
                dialog.show(activity.getFragmentManager(), DetailFragment.class.getName());*/
                DetailFragment dialog = DetailFragment.newInstance(response.body().getResponse().getVenue(), distance.getText().toString());
                dialog.show(activity.getFragmentManager(), DetailFragment.class.getName());
            }

            @Override
            public void onFailure(Call<VenueResponse> call, Throwable t) {
                Toast.makeText(activity, "Error: Failed to load data", Toast.LENGTH_SHORT).show();
            }
        };

        public VenueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Retrofit retrofit = FactoryManager.createRetrofit();
            FoursquareService foursquareService = FactoryManager.createService(retrofit);
            Call<VenueResponse> response = foursquareService.getVenue(
                    id,
                    Settings.V,
                    Settings.CLIENT_ID,
                    Settings.CLIENT_SECRET
            );

            response.enqueue(venueResponseCallback);
        }
    }
}
