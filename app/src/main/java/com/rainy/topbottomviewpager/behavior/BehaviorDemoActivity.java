package com.rainy.topbottomviewpager.behavior;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rainy.topbottomviewpager.R;

public class BehaviorDemoActivity extends AppCompatActivity {
    private TextView depentent,child;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_demo);
        depentent = (TextView) findViewById(R.id.depentent);
        child = (TextView) findViewById(R.id.child);

        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewCompat.offsetTopAndBottom(view,5);
            }
        });
    }
}
