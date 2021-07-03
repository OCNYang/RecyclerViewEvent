package com.ocnyang.recyclerviewevent.paging3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ocnyang.recyclerviewevent.R

open class PagingLoadStateAdapter(val context: Context) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
                LayoutInflater.from(context)
                        .inflate(R.layout.activity_paging_item_loadstate, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.tvLoadState.text = when (loadState) {
            is LoadState.Loading -> "正在加载"
            is LoadState.Error -> "加载错误"
            is LoadState.NotLoading -> "没有加载"
        }
    }
}

class LoadStateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var tvLoadState: TextView = view.findViewById(R.id.tvLoadState)
    var progressBar: ProgressBar = view.findViewById(R.id.progressBar)
}