package com.ocnyang.recyclerviewevent.diff_util

import androidx.recyclerview.widget.DiffUtil

/*******************************************************************
 *    * * * *   * * * *   *     *       @Author: OCN.Yang
 *    *     *   *         * *   *       @CreateDate: 2021/4/10 10:29 AM.
 *    *     *   *         *   * *       @Email: ocnyang@gmail.com
 *    * * * *   * * * *   *     *.Yang  @GitHub: https://github.com/OCNYang
 *******************************************************************/

class AdapterDiffCallback(
    val oldData: List<DiffUtilFruitBean>,
    val newData: List<DiffUtilFruitBean>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    /**
     * 判断两个 item 是不是同一种类型，因为 RecyclerView 支持不同的条目布局
     * 这里的 Demo 因为比较简单不涉及多类型条目，所以直接比较两种数据类型（实际这里每次返回的都是 true）
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldData[oldItemPosition].javaClass == newData[newItemPosition].javaClass

    /**
     * 判断两个 item 的数据内容是否相同，如果 [areItemsTheSame] 判断已经不相同了，就不会判断此方法了
     * 这里的 Demo 直接判断两条数据内容是否相同。
     * ps: 因为这里的 [DiffUtilFruitBean] 采用的是 Kotlin 的数据类，所以可以直接用 == 判断
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldData[oldItemPosition] == newData[newItemPosition]

    /**
     * 判断 item 中具体哪些内容改变了，可以定向刷新。
     */
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}