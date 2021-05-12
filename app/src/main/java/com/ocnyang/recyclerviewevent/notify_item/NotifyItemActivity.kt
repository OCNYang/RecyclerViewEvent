package com.ocnyang.recyclerviewevent.notify_item

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ocnyang.recyclerviewevent.R

class NotifyItemActivity : AppCompatActivity() {
    private lateinit var mAdapter: NotifyItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_item)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            this.layoutManager = LinearLayoutManager(this@NotifyItemActivity)
            mAdapter = NotifyItemAdapter(
                this@NotifyItemActivity,
                NotifyItemDataManager.getData()
            )
            this.adapter = mAdapter
        }

        // 刷新所有条目的选中状态，(注意观察条目刷新时，整个条目的闪动)
        findViewById<CheckBox>(R.id.cb_check_all_left).setOnCheckedChangeListener { _, isChecked ->
            mAdapter.apply {
                data.forEachIndexed { index, bean ->
                    bean.checked = isChecked
                    notifyItemChanged(index)
                }
            }
        }

        // 刷新所有条目的选中状态，（此时其实只是刷新条目的复选框的状态，并不会重绘整个条目，所以不会有条目闪动）
        findViewById<CheckBox>(R.id.cb_check_all_right).setOnCheckedChangeListener { _, isChecked ->
            mAdapter.apply {
                data.forEachIndexed { index, bean ->
                    bean.checked = isChecked
                    notifyItemChanged(index, NotifyItemAdapter.PAYLOAD_CHECKBOX)
                }
            }
        }

        // 交换条目1和条目2的图片并刷新这两个条目
        findViewById<View>(R.id.btn_change_item1_left).setOnClickListener {
            mAdapter.apply {
                // 交换条目1、条目2数据中的图片资源
                data[0].imgId = data[1].imgId.also {
                    data[1].imgId = data[0].imgId
                }
                notifyItemRangeChanged(0, 2)
            }
        }

        // 交换条目1和条目2的图片并刷新这两个条目 (此时只是重新加载两个条目的图片，不会重绘其他控件，不会闪动)
        findViewById<View>(R.id.btn_change_item1_right).setOnClickListener {
            mAdapter.apply {
                data[0].imgId = data[1].imgId.also {
                    data[1].imgId = data[0].imgId
                }
                notifyItemRangeChanged(0, 2, NotifyItemAdapter.PAYLOAD_IMAGE)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}


class NotifyItemAdapter(var context: Context, var data: MutableList<NotifyItemBean>) :
    RecyclerView.Adapter<NotifyItemViewHolder>() {
    companion object {
        @JvmStatic
        val PAYLOAD_CHECKBOX = 0x00000001

        @JvmStatic
        val PAYLOAD_IMAGE = 0x00000010
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifyItemViewHolder =
        NotifyItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.activity_notify_item_item, parent, false)
        )


    override fun onBindViewHolder(holder: NotifyItemViewHolder, position: Int) {
        holder.ivImg.setImageResource(data[position].imgId)
        holder.tvTitle.text = data[position].name
        holder.cb.setOnCheckedChangeListener(null)
        holder.cb.isChecked = data[position].checked
        holder.cb.setOnCheckedChangeListener { _, isChecked ->
            data[position].checked = isChecked
        }
    }

    /**
     * 当使用 [RecyclerView.Adapter.notifyItemChanged] 两个参数的方法 刷新条目时会走此方法刷新布局
     */
    override fun onBindViewHolder(
        holder: NotifyItemViewHolder, position: Int, payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            when (payloads[0]) {
                PAYLOAD_CHECKBOX -> {
                    val checked = data[position].checked
                    if (checked != holder.cb.isChecked) {
                        holder.cb.setOnCheckedChangeListener(null)
                        holder.cb.isChecked = checked
                        holder.cb.setOnCheckedChangeListener { _, isChecked ->
                            data[position].checked = isChecked
                        }
                    }
                }
                PAYLOAD_IMAGE -> holder.ivImg.setImageResource(data[position].imgId)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}

class NotifyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var ivImg: ImageView = itemView.findViewById(R.id.iv_img)
    var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    var cb: CheckBox = itemView.findViewById(R.id.cb)
}
