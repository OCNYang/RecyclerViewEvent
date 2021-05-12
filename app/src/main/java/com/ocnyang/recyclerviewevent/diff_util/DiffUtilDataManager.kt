package com.ocnyang.recyclerviewevent.diff_util

import com.ocnyang.recyclerviewevent.R

/*******************************************************************
 *    * * * *   * * * *   *     *       @Author: OCN.Yang
 *    *     *   *         * *   *       @CreateDate: 2021/4/10 9:59 AM.
 *    *     *   *         *   * *       @Email: ocnyang@gmail.com
 *    * * * *   * * * *   *     *.Yang  @GitHub: https://github.com/OCNYang
 *******************************************************************/

class DiffUtilDataManager {
    companion object {
        @JvmStatic
        val dataList = listOf(
            DiffUtilFruitBean("香蕉?", R.drawable.ic_fruit_icons_01),
            DiffUtilFruitBean("苹果?", R.drawable.ic_fruit_icons_02),
            DiffUtilFruitBean("草莓?", R.drawable.ic_fruit_icons_03),
            DiffUtilFruitBean("橙子?", R.drawable.ic_fruit_icons_04),
            DiffUtilFruitBean("柠檬?", R.drawable.ic_fruit_icons_05),
            DiffUtilFruitBean("梨?", R.drawable.ic_fruit_icons_06),
            DiffUtilFruitBean("樱桃?", R.drawable.ic_fruit_icons_01),
            DiffUtilFruitBean("哈密瓜?", R.drawable.ic_fruit_icons_02),
            DiffUtilFruitBean("猕猴桃?", R.drawable.ic_fruit_icons_03),
            DiffUtilFruitBean("葡萄?", R.drawable.ic_fruit_icons_04),
        )

        @JvmStatic
        fun getRandomData(number: Int): MutableList<DiffUtilFruitBean> {
            var randomStartPosition = dataList.indices.random()
            var num = number
            val list = mutableListOf<DiffUtilFruitBean>()
            while (num >= 0) {
                list.add(dataList[randomStartPosition])
                randomStartPosition = ((randomStartPosition + 1) % (dataList.size - 1))
                num--
            }
            return list
        }
    }
}


data class DiffUtilFruitBean(var name: String, var imgId: Int)