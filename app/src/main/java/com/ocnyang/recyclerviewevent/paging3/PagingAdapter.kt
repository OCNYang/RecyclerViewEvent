package com.ocnyang.recyclerviewevent.paging3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ocnyang.recyclerviewevent.R

class PagingAdapter(val context: Context) :
    PagingDataAdapter<PagingFruitBean, PagingViewHolder>(PagingAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder =
        PagingViewHolder(
            LayoutInflater.from(context).inflate(R.layout.activity_diff_util_item, parent, false)
        )

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val itemBean = getItem(position)
        holder.ivImg.setImageResource(itemBean?.imgId ?: R.drawable.ic_fruit_icons_01)
        holder.tvTitle.text = itemBean?.fruitName ?: ""
    }

}


class PagingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var ivImg: ImageView = itemView.findViewById(R.id.iv_img)
    var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
}