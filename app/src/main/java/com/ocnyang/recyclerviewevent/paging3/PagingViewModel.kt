package com.ocnyang.recyclerviewevent.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingViewModel(private val repository: FruitRepository) : ViewModel() {

    private fun fruitFlow(fruitName: String) = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 20, enablePlaceholders = false),
        pagingSourceFactory = {
            FruitPagingSource(repository = repository, queryFruitName = fruitName)
        }).flow


//    fun requestFruit(fruitName: String) = Pager(
//        config = PagingConfig(pageSize = 10, initialLoadSize = 20, enablePlaceholders = false),
//        pagingSourceFactory = {
//            FruitPagingSource(repository = repository, queryFruitName = fruitName)
//        })
//        .flow
//        .cachedIn(viewModelScope)


    fun requestFruit(
        fruitName: String,
        action: suspend (value: PagingData<PagingFruitBean>) -> Unit
    ) {
        viewModelScope.launch {
            fruitFlow(fruitName).collectLatest(action = action)
        }
    }

}