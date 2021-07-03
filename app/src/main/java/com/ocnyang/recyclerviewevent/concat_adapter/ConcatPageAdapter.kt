package com.ocnyang.recyclerviewevent.concat_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.LoadState
import com.ocnyang.recyclerviewevent.R
import com.ocnyang.recyclerviewevent.diff_util.DiffUtilAdapter
import com.ocnyang.recyclerviewevent.diff_util.DiffUtilFruitBean
import com.ocnyang.recyclerviewevent.diff_util.DiffUtilViewHolder
import com.ocnyang.recyclerviewevent.paging3.LoadStateViewHolder
import com.ocnyang.recyclerviewevent.paging3.PagingLoadStateAdapter

class ConcatPageAdapter(context: Context, data: MutableList<DiffUtilFruitBean>) : DiffUtilAdapter(context, data)

class HeaderAdapter(context: Context, data: MutableList<DiffUtilFruitBean>) : DiffUtilAdapter(context, data) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffUtilViewHolder {
        val itemView =
                LayoutInflater.from(context).inflate(R.layout.activity_concat_item_header, parent, false)
        return DiffUtilViewHolder(itemView)
    }
}

class FooterAdapter(context: Context) : PagingLoadStateAdapter(context) {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.tvLoadState.text = when (loadState) {
            is LoadState.Loading -> {
                holder.progressBar.visibility = View.VISIBLE
                "正在加载"
            }
            is LoadState.Error -> {
                holder.progressBar.visibility = View.GONE
                "没有更多数据"
            }
            is LoadState.NotLoading -> {
                holder.progressBar.visibility = View.GONE
                "加载完成"
            }
        }
    }

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.NotLoading || super.displayLoadStateAsItem(loadState)
    }
}

