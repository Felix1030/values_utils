package com.felix.values.utils.types.listener;

import java.util.List;
import java.util.Map;

/**
 * @author Felix
 * date on 2019-12-02.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public interface OnValuesReadWithHeaderListener {
    // 读取完成
    void onFinish(List<Map<Integer, String>> dataList, Map<Integer, String> headMap);

    // 读取异常
    void onException(Exception e);
}
