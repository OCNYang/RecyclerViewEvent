package com.ocnyang.recyclerviewevent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*******************************************************************
 *    * * * *   * * * *   *     *       Created by OCN.Yang
 *    *     *   *         * *   *       Time:2017/8/2 10:07.
 *    *     *   *         *   * *       Email address:ocnyang@gmail.com
 *    * * * *   * * * *   *     *.Yang  Web site:www.ocnyang.com
 *******************************************************************/


public class DataManager {
    private static List<String> sStringList = Arrays.asList("香蕉", "苹果", "草莓", "橙子",
            "柠檬", "梨", "樱桃", "哈密瓜", "猕猴桃", "葡萄");

    public static final List<String> getData(int number) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            stringList.add(sStringList.get(i % sStringList.size()) + " ?");
        }
        return stringList;
    }


}
