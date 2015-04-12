package com.buckheit.joe.buckheitlab07;

/**
 * Created by joebuckheit on 2/26/15.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private List<Map<String, ?>> mDataset;
    private Context mContext;
    private int mLayoutType;
    OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
        public void onItemLongClick(View v, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public MovieRecyclerViewAdapter(Context myContext, List<Map<String, ?>> myDataset, int layoutType) {
        mContext = myContext;
        mDataset = myDataset;
        mLayoutType = layoutType;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        if (mLayoutType == MovieListFragment.GRID_LAYOUT) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.gridview, parent, false);
        }
        else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview, parent, false);
         }
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, ?> movie = mDataset.get(position);

        holder.bindMovieData(movie);
    }

    public int getItemCount() {return mDataset.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView vIcon;
        public TextView vTitle;
        public TextView vDescription;

        public ViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.icon);
            vTitle = (TextView) v.findViewById(R.id.title);
            vDescription = (TextView) v.findViewById(R.id.description);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                }
            });

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick (View v){
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemLongClick(v, getPosition());
                    }
                    return true;
                }
            });


        }

        public void bindMovieData(Map<String, ?> movie) {
            vTitle.setText((String) movie.get("name"));
            vDescription.setText((String) movie.get("description"));
            vIcon.setImageResource((Integer) movie.get("image"));

        }

    }

}
