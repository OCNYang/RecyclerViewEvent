package com.ocnyang.recyclerviewevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ocnyang.recyclerviewevent.concat_adapter.ConcatAdapterActivity;
import com.ocnyang.recyclerviewevent.diff_util.DiffUtilActivity;
import com.ocnyang.recyclerviewevent.notify_item.NotifyItemActivity;
import com.ocnyang.recyclerviewevent.paging3.PagingActivity;
import com.ocnyang.recyclerviewevent.reuse_disorder.EleganceMethodActivity;
import com.ocnyang.recyclerviewevent.swipe_and_drag.GridViewActivity;
import com.ocnyang.recyclerviewevent.swipe_and_drag.ListViewActivity;

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

    public void skipToDiffUtil(View view) {
        startActivity(new Intent(this, DiffUtilActivity.class));
    }

    public void skipToNotifyItem(View view) {
        startActivity(new Intent(this, NotifyItemActivity.class));
    }

    public void skipToPaging3(View view) {
        startActivity(new Intent(this, PagingActivity.class));
    }

    public void skipToConcatAdapter(View view) {
        startActivity(new Intent(this, ConcatAdapterActivity.class));
    }
}
