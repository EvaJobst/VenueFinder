package at.jobst.evenly.venuefinder.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import at.jobst.evenly.venuefinder.DetailFragment;
import at.jobst.evenly.venuefinder.MainActivity;
import at.jobst.evenly.venuefinder.Utility;
import at.jobst.evenly.venuefinder.retrofit.FoursquareService;
import at.jobst.evenly.venuefinder.R;
import at.jobst.evenly.venuefinder.Settings;
import at.jobst.evenly.venuefinder.data.Venue;
import at.jobst.evenly.venuefinder.retrofit.Data;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Is the adapter that is used for the {@link at.jobst.evenly.venuefinder.MainActivity} in order to display the fetched data in the respective cards.
 */
public class VenueListAdapter extends RecyclerView.Adapter<VenueListAdapter.VenueViewHolder> {
    ArrayList<Venue> venueList;
    Activity activity;
    ProgressBar progressBar;

    public VenueListAdapter(ArrayList<Venue> venues, Activity a) {
        venueList = venues;
        activity = a;

        // Add the progressBar of the MainActivity
        if(activity instanceof MainActivity) {
            progressBar = (((MainActivity) activity).progressBar);
        }
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new VenueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        Venue venue = venueList.get(position);

        holder.id = venue.getId();
        holder.name.setText(venue.getName());
        holder.distance.setText(venue.getLocation().getDistance() + " m");
        holder.address.setText(venue.getLocation().getAddress());
        if(venue.getCategories().get(0) != null) {
            holder.category.setText(venue.getCategories().get(0).getName());
        }
    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }

    class VenueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        String id;
        @BindView(R.id.tv_item_name)        TextView name;
        @BindView(R.id.tv_item_distance)    TextView distance;
        @BindView(R.id.tv_item_category)    TextView category;
        @BindView(R.id.tv_item_address)     TextView address;

        /**
         * Shows {@link DetailFragment} of data as a dialog on successful fetch. Shows error message on failed fetch.
         */
        Callback<Data> dataCallback = new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                DetailFragment dialog = DetailFragment.newInstance(response.body().getResponse().getVenue(), distance.getText().toString());
                dialog.show(activity.getFragmentManager(), DetailFragment.class.getName());

                if(progressBar != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Utility.showError(activity, t);

                if(progressBar != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        };

        public VenueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        /**
         * Fetch data of clicked view and enlists a Callback.
         * @param v The view that has been clicked on
         */
        @Override
        public void onClick(View v) {
            Retrofit retrofit = Utility.createRetrofit();
            FoursquareService foursquareService = retrofit.create(FoursquareService.class);
            Call<Data> response = foursquareService.fetch(
                    id,
                    Settings.VERSION,
                    Settings.CLIENT_ID,
                    Settings.CLIENT_SECRET
            );

            if(progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
            }

            response.enqueue(dataCallback);
        }
    }
}
