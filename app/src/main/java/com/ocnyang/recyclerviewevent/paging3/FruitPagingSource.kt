package com.ocnyang.recyclerviewevent.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

class FruitPagingSource(
        private val repository: FruitRepository,
        private val queryFruitName: String
) : PagingSource<Int, PagingFruitBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PagingFruitBean> {
        return try {
            var nextPageNumber = params.key ?: 1
            val baseBean = repository.requestFruitList(queryFruitName, nextPageNumber)

            if (baseBean.isSuccess) {
                val currentPage = baseBean.data.currentPage
                val prevPageNumber = if (currentPage > 1) currentPage - 1 else null
                val nextPageNumber = if (currentPage < baseBean.data.totalPage) currentPage + 1 else null

                LoadResult.Page<Int, PagingFruitBean>(
                        data = baseBean.data.fruits,
                        prevKey = prevPageNumber,
                        nextKey = nextPageNumber
                )
            } else {
                LoadResult.Error(Exception())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PagingFruitBean>): Int? {
        // TODO("Not yet implemented")
        return 1
    }
}