package at.jobst.evenly.venuefinder.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import at.jobst.evenly.venuefinder.R;
import at.jobst.evenly.venuefinder.data.Venue;
import butterknife.BindView;
import butterknife.ButterKnife;

//import butterknife.*;

/**
 * Created by Eva on 30.09.2017.
 */

public class VenueListAdapter extends RecyclerView.Adapter<VenueListAdapter.VenueViewHolder> {
    List<Venue> venueList;

    VenueListAdapter() {

    }

    VenueListAdapter(List<Venue> venues) {
        venueList = venues;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new VenueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class VenueViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_name) TextView name;
        @BindView(R.id.tv_item_distance) TextView distance;
        @BindView(R.id.tv_item_hours) TextView hours;
        @BindView(R.id.tv_item_description) TextView description;

        public VenueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
