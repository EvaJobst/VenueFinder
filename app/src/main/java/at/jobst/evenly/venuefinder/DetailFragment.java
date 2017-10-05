package at.jobst.evenly.venuefinder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.DialogFragment;
//import android.support.v4.app.DialogFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends DialogFragment {
    @BindView(R.id.tv_dialog_name)
    TextView name;

    @BindView(R.id.iv_dialog_image)
    ImageView image;

    @BindView(R.id.tv_dialog_description)
    TextView description;

    @BindView(R.id.tv_dialog_hours)
    TextView hours;

    @BindView(R.id.tv_dialog_distance)
    TextView distance;

    @BindView(R.id.tv_dialog_address)
    TextView address;

    @BindView(R.id.tv_dialog_category)
    TextView category;

    private Venue venue;

    View view;

    //private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Venue venue, String venueDistance) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();

        args.putSerializable(Settings.VENUE_ID, new Gson().toJson(venue));
        args.putString(Settings.VENUE_DISTANCE, venueDistance);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        name.setText(venue.getName());
        distance.setText(venue.getLocation().getDistance());
        address.setText(venue.getLocation().getAddress());
        category.setText(venue.getCategories().get(0).getName());
        description.setText(venue.getDescription());

        if(venue.getHours() != null) {
            hours.setText(venue.getHours().getStatus());
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_detail, null);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        Venue v = new Gson().fromJson(bundle.getSerializable(Settings.VENUE_ID).toString(), Venue.class);
        String d = bundle.getString(Settings.VENUE_DISTANCE);
        v.getLocation().setDistance(d);
        venue = v;

        if(v.getBestPhoto() == null) {
            image.setMaxHeight(0);
            image.setMinimumHeight(0);
            image.setEnabled(false);
        }

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

        builder.setView(view);
        return builder.create();
    }



    /*@Override
    public void onResponse(Call<VenueResponse> call, Response<VenueResponse> response) {
        Log.d("REQUEST", response.toString());
        Log.d("RESPONSE", response.body().toString());

        distance.setText(venueDistance);

        name.setText(response.body().getResponse().getVenue().getName());
        address.setText(response.body().getResponse().getVenue().getLocation().getAddress());
        category.setText(response.body().getResponse().getVenue().getCategories().get(0).getName());

        if(response.body().getResponse().getVenue().getDescription() != null) {
            description.setText(response.body().getResponse().getVenue().getDescription());
        }

        else {
            description.setEnabled(false);
            //description.setHeight(0);
            //description.setText("No description available.");
        }


        if(response.body().getResponse().getVenue().getHours() != null) {
            hours.setText(response.body().getResponse().getVenue().getHours().getStatus());
        }

        if(response.body().getResponse().getVenue().getBestPhoto() != null) {
            String prefix = response.body().getResponse().getVenue().getBestPhoto().getPrefix();
            String suffix = response.body().getResponse().getVenue().getBestPhoto().getSuffix();
            String height = response.body().getResponse().getVenue().getBestPhoto().getHeight();
            String width = response.body().getResponse().getVenue().getBestPhoto().getWidth();
            String url = prefix + width + "x" + height + suffix;
            Log.d("URL", url);

                Picasso.with(getActivity())
                        .load(url)
                        .resize(image.getWidth(), image.getHeight())
                        .centerCrop()
                        .into(image);
        }

        else {
            image.setEnabled(false);
        }
    }

    @Override
    public void onFailure(Call<VenueResponse> call, Throwable t) {

    }*/
}
