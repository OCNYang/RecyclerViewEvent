package com.ocnyang.recyclerviewevent.notify_item

import com.ocnyang.recyclerviewevent.R

class NotifyItemDataManager {
    companion object {
        @JvmStatic
        fun getData() = mutableListOf(
            NotifyItemBean("香蕉?", R.drawable.ic_fruit_icons_01),
            NotifyItemBean("苹果?", R.drawable.ic_fruit_icons_02),
            NotifyItemBean("草莓?", R.drawable.ic_fruit_icons_03),
            NotifyItemBean("橙子?", R.drawable.ic_fruit_icons_04),
            NotifyItemBean("柠檬?", R.drawable.ic_fruit_icons_05),
            NotifyItemBean("雪梨?", R.drawable.ic_fruit_icons_06),
            NotifyItemBean("樱桃?", R.drawable.ic_fruit_icons_01),
            NotifyItemBean("哈密瓜?", R.drawable.ic_fruit_icons_02),
            NotifyItemBean("猕猴桃?", R.drawable.ic_fruit_icons_03),
            NotifyItemBean("葡萄?", R.drawable.ic_fruit_icons_04),
        )
    }
}

data class NotifyItemBean(var name: String, var imgId: Int, var checked: Boolean = false)