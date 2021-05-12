package com.ocnyang.recyclerviewevent.reuse_disorder

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ocnyang.recyclerviewevent.DataManager
import com.ocnyang.recyclerviewevent.R

class EleganceMethodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elegance_method)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            this.layoutManager = LinearLayoutManager(this@EleganceMethodActivity)
            this.adapter = EleganceMethodAdapter(
                this@EleganceMethodActivity,
                DataManager.getData(20).map { FruitBean(title = it) }.toMutableList()
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

class EleganceMethodAdapter(private val context: Context, var data: MutableList<FruitBean>) :
    RecyclerView.Adapter<EleganceMethodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EleganceMethodViewHolder {
        val itemView =
            LayoutInflater.from(context)
                .inflate(R.layout.activity_elegance_method_item, parent, false)
        return EleganceMethodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EleganceMethodViewHolder, position: Int) {
        holder.ivImg.setImageResource(imgList[position % imgList.size])
        holder.tvTitle.text = data[position].title

        // 通过只给 CheckBox 设置点击事件 来解决复用错乱
        //        holder.checkBox.setOnClickListener {
        //            data[position].checked = !data[position].checked
        //        }
        //
        //        if (holder.checkBox.isChecked != data[position].checked) {
        //            holder.checkBox.isChecked = data[position].checked
        //        }

        /**
         * CheckBox 解决复用方法：
         * 1. 将监听 CheckBox 状态变换的监听器设为 null。（主要目的是为了防止，设置 CheckBox 选中状态时回调监听）
         * 2. 根据 条目数据 设置 CheckBox 的选中状态
         * 3. 给 CheckBox 设置一个状态变化时的监听器，当状态变化时，将对应的 itemBean 的状态值对应改变
         */
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = data[position].checked
        val onCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { _, isChecked ->
                data[position].checked = isChecked
            }
        holder.checkBox.setOnCheckedChangeListener(onCheckedChangeListener)


        /**
         * EditText 解决复用方法讲解：
         * 主要思路同上 CheckBox 的解决方法。
         * 但 EditText 的文本变化监听器，不能通过 addTextChangedListener(null) 清空，
         * 只能通过 removeTextChangedListener(TextWatcher watcher) 移除掉监听器，参数是你要移除的监听器
         *
         * 所以我们要解决的就是怎么保存监听器，当需要移除时需要获取它
         * 最好的方法，就是和 EditText 一样将 TextWatcher 保存到 ViewHolder
         * 这样同样能保证当 RecyclerView 通过复用机制在滚动中复用 item 时(其实就是复用的 ViewHolder)时, 复用的 EdiText 和 TextWatcher 是对应的
         *
         */
        holder.editText.removeTextChangedListener(holder.textWatcher)
        holder.editText.setText(data[position].inputStr)
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                data[position].inputStr = s.toString()
            }
        }
        holder.editText.addTextChangedListener(textWatcher)
        holder.textWatcher = textWatcher
    }

    override fun getItemCount(): Int = data.size

    companion object {
        val imgList = listOf(
            R.drawable.ic_fruit_icons_01, R.drawable.ic_fruit_icons_02,
            R.drawable.ic_fruit_icons_03, R.drawable.ic_fruit_icons_04,
            R.drawable.ic_fruit_icons_05, R.drawable.ic_fruit_icons_06
        )
    }
}

class EleganceMethodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var ivImg: ImageView = itemView.findViewById(R.id.iv_img)
    var checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    var editText: EditText = itemView.findViewById(R.id.editText)
    var textWatcher: TextWatcher? = null
}