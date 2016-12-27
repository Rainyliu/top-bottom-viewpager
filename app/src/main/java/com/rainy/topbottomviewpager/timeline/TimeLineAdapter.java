package com.rainy.topbottomviewpager.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rainy.topbottomviewpager.R;

import java.util.List;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/22 0022 下午 4:22 <br>
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {
    private List<TimeLineModel> mDataSet;

    public TimeLineAdapter(List<TimeLineModel> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_time_line, parent, false);
        return new TimeLineViewHolder(v,viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        holder.setData(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        int size = mDataSet.size() - 1;
        if (size == 0)
            return ItemType.ATOM;
        else if (position == 0)
            return ItemType.START;
        else if (position == size)
            return ItemType.END;
        else return ItemType.NORMAL;
    }
}
