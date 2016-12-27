package com.rainy.topbottomviewpager.timeline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.rainy.topbottomviewpager.R;

import java.util.ArrayList;
import java.util.List;

public class TimeLineActivity extends AppCompatActivity {
    private String TAG = "TimeLineActivity";
    private RecyclerView mRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        mRecycler = (RecyclerView) findViewById(R.id.listview);
        initRecycler();
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(layoutManager);
        TimeLineAdapter adapter = new TimeLineAdapter(getData());
        Log.d(TAG, "initRecycler: =========="+getData().size());
        mRecycler.setAdapter(adapter);
    }

    private List<TimeLineModel> getData() {
        List<TimeLineModel> models = new ArrayList<TimeLineModel>();

        models.add(new TimeLineModel("XiaoMing", 21));
        models.add(new TimeLineModel("XiaoFang", 20));
        models.add(new TimeLineModel("XiaoHua", 25));
        models.add(new TimeLineModel("XiaoA", 22));
        models.add(new TimeLineModel("XiaoNiu", 23));

        return models;
    }
}
