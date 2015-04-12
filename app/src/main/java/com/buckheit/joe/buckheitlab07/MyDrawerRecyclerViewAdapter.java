package com.buckheit.joe.buckheitlab07;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by joebuckheit on 3/7/15.
 */
public class MyDrawerRecyclerViewAdapter extends RecyclerView.Adapter<MyDrawerRecyclerViewAdapter.ViewHolder> {

    private String[] mDataset;
    private Context mContext;
    private int mCurrentPosition = -1;

    OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

        public MyDrawerRecyclerViewAdapter(Context myContext, String[] myDataset) {
        mContext = myContext;
        mDataset = myDataset;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public MyDrawerRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        switch (viewType) {
            case 0:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.drawer_cardview_icontext, parent, false);
                break;
            case 1:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.drawer_cardview_icontext, parent, false);
                break;
            case 2:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.drawer_cardview_icontext, parent, false);
                break;
            case 3:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.drawer_cardview_separator, parent, false);
                break;
            default:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.drawer_cardview_text, parent, false);
                break;
        }

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        String drawerItem =  mDataset[position];
        holder.bindText(drawerItem, position);
    }

    public int getItemCount() {return mDataset.length; }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView vText;
        public View vView;
        public ImageView vIcon;
        public LinearLayout vBack;

        public ViewHolder(View v) {
            super(v);
            vText = (TextView) v.findViewById(R.id.textView);
            vIcon = (ImageView) v.findViewById(R.id.icon);
            vBack = (LinearLayout) v.findViewById(R.id.drawer_background);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                    mCurrentPosition = getPosition();
                    notifyDataSetChanged();
                }
            });

        }


        public void bindText(String text, int position) {
            if (vBack != null) {
                if (position == mCurrentPosition)
                    vBack.setBackgroundColor(Color.LTGRAY);
                else
                    vBack.setBackgroundColor(Color.WHITE);
            }

            if (vIcon != null) {
                if (position == 0)
                    vIcon.setImageResource(R.drawable.icons_compass);
                if (position == 1)
                    vIcon.setImageResource(R.drawable.icon_list);
                if (position == 2)
                    vIcon.setImageResource(R.drawable.icon_grid);
            }

            if (vText != null)
                vText.setText(text);
        }

    }


}
