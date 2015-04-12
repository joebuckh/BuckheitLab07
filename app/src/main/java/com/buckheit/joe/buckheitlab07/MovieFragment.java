package com.buckheit.joe.buckheitlab07;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by joebuckheit on 1/26/15.
 */
public class MovieFragment extends Fragment {

    private HashMap<String, ?> movie;
    private TextView mMovieName, mRating, mDirector, mYear, mStars, mDescription;
    private ImageView mImage;
    private RatingBar mRatingBar;
    private ShareActionProvider mShareActionProvider;

    private static final String ARG_OPTION = "argument_option";

    public static MovieFragment newInstance(HashMap<String, ?> movie) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_OPTION, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.movie_detail_menu_item, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        intentShare.putExtra(Intent.EXTRA_TEXT, (String) movie.get("name"));

        mShareActionProvider.setShareIntent(intentShare);

        super.onCreateOptionsMenu(menu, inflater);

    }

        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() !=null) {
            movie = (HashMap<String, ?>) getArguments().getSerializable(ARG_OPTION);
        }
        setHasOptionsMenu(true);
    }

    private void showMovieInfo() {
        mMovieName.setText((String)movie.get("name"));
        mRating.setText(movie.get("rating").toString());
        mDirector.setText((String)movie.get("director"));
        mYear.setText((String)movie.get("year"));
        mImage.setImageResource((Integer)movie.get("image"));
        mStars.setText((String)movie.get("stars"));
        mDescription.setText((String)movie.get("description"));
        Double r = (Double) movie.get("rating");
        mRatingBar.setRating(r.floatValue());

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie, parent, false);

        mMovieName = (TextView) v.findViewById(R.id.txtView_moviename);
        mRating = (TextView) v.findViewById(R.id.txtView_rating);
        mDirector = (TextView) v.findViewById(R.id.txtView_director);
        mYear = (TextView) v.findViewById(R.id.txtView_year);
        mImage = (ImageView) v.findViewById(R.id.imgView_movie);
        mStars = (TextView) v.findViewById(R.id.txtView_stars);
        mDescription = (TextView) v.findViewById(R.id.txtView_description);
        mRatingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        showMovieInfo();

        return v;
    }

}
