package com.ocnyang.recyclerviewevent.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PagingViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PagingViewModel::class.java)) {
            return PagingViewModel(FruitRepository()) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}