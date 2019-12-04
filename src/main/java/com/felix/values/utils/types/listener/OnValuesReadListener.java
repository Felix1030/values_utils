package com.felix.values.utils.types.listener;

import java.util.List;
import java.util.Map;

/**
 * @author Felix
 * @date 2019-11-29.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public interface OnValuesReadListener {
    // 读取完成
    void onFinish(List<Map<Integer, String>> dataList);

    // 读取异常
    void onException(Exception e);
}
