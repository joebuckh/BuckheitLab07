package com.buckheit.joe.buckheitlab07;

/**
 * Created by joebuckheit on 2/26/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.HashMap;


public class MovieListFragment extends Fragment {

    public static final String ARG_OPTION = "layout";
    public static final int LIST_LAYOUT = 0;
    public static final int GRID_LAYOUT = 1;
    private static final int GRID_COLUMNS = 3;


    private MovieDataJson movieData;
    private RecyclerView mRecyclerView;
    private MovieRecyclerViewAdapter mRecyclerViewAdapter;

    private int mLayoutType = LIST_LAYOUT;

    private OnListItemSelectedListener mListener;

    public interface OnListItemSelectedListener {
        public void onListItemSelected(int position, HashMap<String, ?> movie);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnListItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    public static MovieListFragment newInstance(int layout) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_OPTION, layout);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            //Get the Layout Type
            mLayoutType = getArguments().getInt(ARG_OPTION);
        }

        setRetainInstance(true);

        try {
            movieData = new MovieDataJson(getActivity());
        }
        catch (JSONException e) {
            Log.d("MyDebugMsg", "JsonException in MovieListFragment onCreate()");
            e.printStackTrace();
        }

    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_movielist, parent, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.cardList);
        mRecyclerView.setHasFixedSize(true);

        if (mLayoutType == GRID_LAYOUT) {
            GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(),GRID_COLUMNS);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        else {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        mRecyclerViewAdapter = new MovieRecyclerViewAdapter(getActivity(), movieData.getMoviesList(), mLayoutType);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);


        mRecyclerViewAdapter.SetOnItemClickListener(new MovieRecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                HashMap<String, ?> movie = (HashMap<String, ?>) movieData.getItem(position);
                mListener.onListItemSelected(position,movie);           }

            @Override
            public void onItemLongClick(View v, int position) {
            }

        });

        return v;
    }

}
