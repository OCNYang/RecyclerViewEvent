package com.ocnyang.recyclerviewevent.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ocnyang.recyclerviewevent.R

class PagingActivity : AppCompatActivity() {
    private lateinit var mAdapter: PagingAdapter
    private val viewModel by lazy {
        ViewModelProvider(this, PagingViewModelFactory()).get(
            PagingViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = PagingAdapter(context)
            adapter = mAdapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(context),
                footer = PagingLoadStateAdapter(context)
            )
        }

        viewModel.requestFruit("苹果") {
            mAdapter.submitData(it)
        }

    }
}