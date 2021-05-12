package com.ocnyang.recyclerviewevent.swipe_and_drag;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ocnyang.recyclerviewevent.R;

import java.util.Arrays;
import java.util.List;

/*******************************************************************
 *    * * * *   * * * *   *     *       Created by OCN.Yang
 *    *     *   *         * *   *       Time:2017/8/2 09:57.
 *    *     *   *         *   * *       Email address:ocnyang@gmail.com
 *    * * * *   * * * *   *     *.Yang  Web site:www.ocnyang.com
 *******************************************************************/


public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {
    private int item_layout;
    private List<String> mDataList;
    private List<Integer> mInts;
    private boolean isFirstSpecial;

    public RecyAdapter(int item_layout, List<String> dataList) {
        this.item_layout = item_layout;
        mDataList = dataList;
        mInts = Arrays.asList(R.drawable.ic_fruit_icons_01, R.drawable.ic_fruit_icons_02,
                R.drawable.ic_fruit_icons_03, R.drawable.ic_fruit_icons_04,
                R.drawable.ic_fruit_icons_05, R.drawable.ic_fruit_icons_06);
    }

    public RecyAdapter(int item_layout, List<String> dataList, boolean isFirstSpecial) {
        this(item_layout, dataList);
        this.isFirstSpecial = isFirstSpecial;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String string = mDataList.get(position);
        if (isFirstSpecial && position == 0) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
            holder.mTextView.setText("iPhone");
            holder.mImageView.setImageResource(R.drawable.ic_iphone);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.mTextView.setText(string);
            holder.mImageView.setImageResource(mInts.get(position % mInts.size()));
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public List<String> getDataList() {
        return mDataList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;

        ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_item);
            mImageView = itemView.findViewById(R.id.img_item);
        }
    }
}
