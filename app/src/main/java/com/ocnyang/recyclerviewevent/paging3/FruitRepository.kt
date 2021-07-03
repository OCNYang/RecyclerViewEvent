package com.ocnyang.recyclerviewevent.paging3

import com.ocnyang.recyclerviewevent.R
import kotlinx.coroutines.delay

class FruitRepository {

    companion object {
        const val PAGE_MAX = 6
        val IMG_IDS = listOf(
                R.drawable.ic_fruit_icons_01,
                R.drawable.ic_fruit_icons_02,
                R.drawable.ic_fruit_icons_03,
                R.drawable.ic_fruit_icons_04,
                R.drawable.ic_fruit_icons_05,
                R.drawable.ic_fruit_icons_06,
        )
    }

    suspend fun requestFruitList(
            fruitName: String,
            pageNumber: Int
    ): BaseBean<PagingFruitResultBean> {
        delay(1000)

        val fruitList = mutableListOf<PagingFruitBean>()
        for (i in 0..9) {
            fruitList.add(
                    PagingFruitBean(
                            "$fruitName $pageNumber$i 代",
                            imgId = IMG_IDS[(pageNumber + i) % IMG_IDS.size]
                    )
            )
        }

        return BaseBean(
                isSuccess = true,
                msg = "success",
                data = PagingFruitResultBean(
                        fruits = fruitList,
                        currentPage = pageNumber,
                        totalPage = PAGE_MAX
                )
        )
    }
}