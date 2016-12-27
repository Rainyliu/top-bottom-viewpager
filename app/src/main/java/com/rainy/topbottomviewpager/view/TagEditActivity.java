package com.rainy.topbottomviewpager.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rainy.topbottomviewpager.R;

public class TagEditActivity extends AppCompatActivity {
    private EditText tagEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_edit);
        tagEdit = (EditText) findViewById(R.id.tagET);
    }

    public void click(View view){
        finish();
    }

}
