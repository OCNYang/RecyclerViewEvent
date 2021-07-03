package com.ocnyang.recyclerviewevent.diff_util

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ocnyang.recyclerviewevent.R

class DiffUtilActivity : AppCompatActivity() {
    private lateinit var mAdapter: DiffUtilAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diff_util)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            this.layoutManager = LinearLayoutManager(this@DiffUtilActivity)
            mAdapter = DiffUtilAdapter(
                this@DiffUtilActivity,
                DiffUtilDataManager.getRandomData((6..9).random())
            )
            this.adapter = mAdapter
        }

        findViewById<View>(R.id.btn_change).setOnClickListener {
            // 生成新的列表数据
            // 这里的数据为了达到部分相同部分变化，数据取自相同的数据源集合
            val randomData = DiffUtilDataManager.getRandomData((6..9).random())

            /**
             * 通过 [AdapterDiffCallback] 比较新老数据的差别
             */
            val diffResult = DiffUtil.calculateDiff(
                AdapterDiffCallback(
                    oldData = mAdapter.data,
                    newData = randomData
                )
            )
            // 将差别结果作用于列表的 Adapter 以刷新数据显示
            diffResult.dispatchUpdatesTo(mAdapter)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

open class DiffUtilAdapter(var context: Context, var data: MutableList<DiffUtilFruitBean>) :
    RecyclerView.Adapter<DiffUtilViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffUtilViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.activity_diff_util_item, parent, false)
        return DiffUtilViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiffUtilViewHolder, position: Int) {
        holder.ivImg.setImageResource(data[position].imgId)
        holder.tvTitle.text = data[position].name
    }

    override fun getItemCount(): Int = data.size
}

class DiffUtilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var ivImg: ImageView = itemView.findViewById(R.id.iv_img)
    var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
}
