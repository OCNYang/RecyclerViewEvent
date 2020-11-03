package com.ocnyang.recyclerviewevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ocnyang.recyclerviewevent.reuse_disorder.EleganceMethodActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showListView(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void showGridView(View view) {
        startActivity(new Intent(this, GridViewActivity.class));
    }

    public void skipToEleganceMethod(View view) {
        startActivity(new Intent(this, EleganceMethodActivity.class));
    }
}
