package at.jobst.evenly.venuefinder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import at.jobst.evenly.venuefinder.data.Venue;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to create an instance of this fragment.
 */
public class DetailFragment extends DialogFragment {
    @BindView(R.id.tv_dialog_name) TextView name;
    @BindView(R.id.iv_dialog_image) ImageView image;
    @BindView(R.id.tv_dialog_description) TextView description;
    @BindView(R.id.tv_dialog_hours) TextView hours;
    @BindView(R.id.tv_dialog_distance) TextView distance;
    @BindView(R.id.tv_dialog_address) TextView address;
    @BindView(R.id.tv_dialog_category) TextView category;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Creates and returns a new instance with arguments
     * @param venue Currently selected Venue
     * @param venueDistance Distance of the currently selected Venue
     * @return DetailFragment-instance with a Bundle
     */
    public static DetailFragment newInstance(Venue venue, String venueDistance) {
        DetailFragment fragment = new DetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(Settings.VENUE_DETAILS, new Gson().toJson(venue));
        args.putString(Settings.VENUE_DISTANCE, venueDistance);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Is responsible for the layout of the Dialog. The arguments are processed and put into their Views.
     * @param savedInstanceState
     * @return The built Dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_detail, null);
        ButterKnife.bind(this, view);

        // Parses argument values
        Bundle bundle = getArguments();
        Venue venue = new Gson().fromJson(bundle.getSerializable(Settings.VENUE_DETAILS).toString(), Venue.class);
        String d = bundle.getString(Settings.VENUE_DISTANCE);
        venue.getLocation().setDistance(d);

        // Fills TextViews with values
        name.setText(venue.getName());
        distance.setText(venue.getLocation().getDistance());
        address.setText(venue.getLocation().getAddress());
        category.setText(venue.getCategories().get(0).getName());
        description.setText(venue.getDescription());

        if(venue.getHours() != null) {
            hours.setText(venue.getHours().getStatus());
        }

        // Fills ImageView with image
        setImage(venue, view);

        builder.setView(view);
        return builder.create();
    }

    /**
     * Loads the image into the view-container. A Runnable prevents the image from being loaded before the height and width of the container are set.
     * @param venue The Venue containing the image
     * @param view The View of the Fragment
     */
    void setImage(final Venue venue, View view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                if(venue.getBestPhoto() != null) {
                    Picasso.with(getActivity())
                            .load(venue.getBestPhoto().getURL())
                            .resize(image.getWidth(), image.getHeight())
                            .centerCrop()
                            .into(image);
                }
            }
        });
    }
}
