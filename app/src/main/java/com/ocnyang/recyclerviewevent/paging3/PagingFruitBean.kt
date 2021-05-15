package com.ocnyang.recyclerviewevent.paging3

import androidx.annotation.DrawableRes

data class PagingFruitBean(var fruitName: String,@DrawableRes var imgId: Int)

data class PagingFruitResultBean(
    var fruits: List<PagingFruitBean>,
    var currentPage: Int,
    var totalPage: Int
)

data class BaseBean<T>(var data: T, var isSuccess: Boolean, var msg: String)
