package com.rainy.topbottomviewpager.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rainy.topbottomviewpager.R;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/22 0022 下午 4:19 <br>
 */

public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    private TextView mName;
    public TimeLineViewHolder(View itemView,int type) {
        super(itemView);

        mName = (TextView) itemView.findViewById(R.id.item_time_line_txt);

        TimeLineMarker mMarker = (TimeLineMarker) itemView.findViewById(R.id.item_time_line_mark);
        if (type == ItemType.ATOM) {
            mMarker.setBeginLine(null);
            mMarker.setEndLine(null);
        } else if (type == ItemType.START) {
            mMarker.setBeginLine(null);
        } else if (type == ItemType.END) {
            mMarker.setEndLine(null);
        }
    }



    public void setData(TimeLineModel data) {
        mName.setText("Name:" + data.getName() + " Age:" + data.getAge());
    }




}
