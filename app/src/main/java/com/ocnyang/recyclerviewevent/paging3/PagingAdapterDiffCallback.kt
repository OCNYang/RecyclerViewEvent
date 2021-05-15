package com.ocnyang.recyclerviewevent.paging3

import androidx.recyclerview.widget.DiffUtil

class PagingAdapterDiffCallback : DiffUtil.ItemCallback<PagingFruitBean>() {

    override fun areItemsTheSame(oldItem: PagingFruitBean, newItem: PagingFruitBean): Boolean {
        return oldItem.javaClass == newItem.javaClass
    }

    override fun areContentsTheSame(oldItem: PagingFruitBean, newItem: PagingFruitBean): Boolean {
        return oldItem == newItem
    }
}