package com.ocnyang.recyclerviewevent.reuse_disorder

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
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

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            this.layoutManager = LinearLayoutManager(this@EleganceMethodActivity)
            this.adapter = EleganceMethodAdapter(
                    this@EleganceMethodActivity,
                    DataManager.getData(20).map { FruitBean(title = it) }.toMutableList()
            )
        }
    }
}

class EleganceMethodAdapter(private val context: Context, var data: MutableList<FruitBean>) :
        RecyclerView.Adapter<EleganceMethodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EleganceMethodViewHolder {
        val itemView =
                LayoutInflater.from(context).inflate(R.layout.activity_elegance_method_item, parent, false)
        return EleganceMethodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EleganceMethodViewHolder, position: Int) {
        holder.ivImg.setImageResource(imgList[ position%imgList.size])
        holder.tvTitle.text = data[position].title

        // 通过只给 CheckBox 设置点击事件 来解决复用错乱
        //        holder.checkBox.setOnClickListener {
        //            data[position].checked = !data[position].checked
        //        }
        //
        //        if (holder.checkBox.isChecked != data[position].checked) {
        //            holder.checkBox.isChecked = data[position].checked
        //        }

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = data[position].checked
        val onCheckedChangeListener =
                CompoundButton.OnCheckedChangeListener { _, isChecked -> data[position].checked = isChecked }
        holder.checkBox.setOnCheckedChangeListener(onCheckedChangeListener)


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
        val imgList = listOf(R.drawable.ic_fruit_icons_01, R.drawable.ic_fruit_icons_02,
                R.drawable.ic_fruit_icons_03, R.drawable.ic_fruit_icons_04,
                R.drawable.ic_fruit_icons_05, R.drawable.ic_fruit_icons_06)
    }
}

class EleganceMethodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var ivImg: ImageView = itemView.findViewById(R.id.iv_img)
    var checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    var editText: EditText = itemView.findViewById(R.id.editText)
    var textWatcher: TextWatcher? = null
}