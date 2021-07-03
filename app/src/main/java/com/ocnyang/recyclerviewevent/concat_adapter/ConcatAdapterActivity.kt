package com.ocnyang.recyclerviewevent.concat_adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ocnyang.recyclerviewevent.R
import com.ocnyang.recyclerviewevent.diff_util.DiffUtilAdapter
import com.ocnyang.recyclerviewevent.diff_util.DiffUtilDataManager

class ConcatAdapterActivity : AppCompatActivity() {
    private lateinit var mAdapter: ConcatPageAdapter
    private lateinit var mHeaderAdapter: HeaderAdapter
    private lateinit var mFooterAdapter: FooterAdapter
    private var lastVisibleItemPosition: Int = 0
    private val mHandler by lazy { HandlerCompat.createAsync(this.mainLooper) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concat_adapter)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mAdapter = ConcatPageAdapter(this, DiffUtilDataManager.getRandomData((16..19).random()))
        mHeaderAdapter = HeaderAdapter(this, DiffUtilDataManager.getRandomData((1..3).random()))
        mFooterAdapter = FooterAdapter(this)
        mFooterAdapter.loadState = LoadState.NotLoading(false)

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@ConcatAdapterActivity)
            adapter = ConcatAdapter(mHeaderAdapter, mAdapter, mFooterAdapter)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                        var childCount = (layoutManager as LinearLayoutManager).childCount
                        var itemCount = (layoutManager as LinearLayoutManager).itemCount
                        if (!checkIsLoadEnd()) {
                            if (lastVisibleItemPosition == itemCount - 1) {
                                loadMoreData()
                            }
                        } else {
                            mFooterAdapter.loadState = LoadState.Error(Throwable())
                        }
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                }
            })
        }
    }

    fun checkIsLoadEnd(): Boolean = mAdapter.data.size > 38
    fun loadMoreData() {
        mFooterAdapter.loadState = LoadState.Loading
        mHandler.postDelayed({
            mAdapter.data = mAdapter.data.apply {
                addAll(DiffUtilDataManager.getRandomData((6..8).random()))
            }
            mAdapter.notifyDataSetChanged()
            mFooterAdapter.loadState = LoadState.NotLoading(false)
        }, 1500)
    }

}