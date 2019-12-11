package com.felix.values.utils.types.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Felix
 * date on 2019-11-29.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class ValuesReadExcelListener extends AnalysisEventListener<Map<Integer, String>> {
    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(ValuesReadExcelListener.class);
    // 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 5;
    // 读取到的Excel数据
    private List<Map<Integer, String>> dataList = new ArrayList<Map<Integer, String>>();
    //是否需要Header
    private boolean mNeedHeader;
    // 读取监听
    private OnValuesReadListener mOnValuesReadListener;
    // 读取到的Excel Header
    private Map<Integer, String> headerMap = null;
    // 带有Header的监听
    private OnValuesReadWithHeaderListener mOnValuesReadWithHeaderListener;

    public ValuesReadExcelListener(OnValuesReadListener onValuesReadListener) {
        this(onValuesReadListener, true);
    }

    public ValuesReadExcelListener(OnValuesReadWithHeaderListener onValuesReadWithHeaderListener) {
        this.mOnValuesReadWithHeaderListener = onValuesReadWithHeaderListener;
    }

    public ValuesReadExcelListener(
            OnValuesReadListener onValuesReadListener,
            boolean isInvokeHeader) {
        this.mOnValuesReadListener = onValuesReadListener;
        this.mNeedHeader = isInvokeHeader;
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        dataList.add(data);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        if (mNeedHeader && mOnValuesReadWithHeaderListener == null) {
            dataList.add(headMap);
        }
        headerMap = headMap;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (null != mOnValuesReadListener) mOnValuesReadListener.onFinish(dataList);
        if (null != mOnValuesReadWithHeaderListener)
            mOnValuesReadWithHeaderListener.onFinish(dataList, headerMap);
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        super.onException(exception, context);
        if (null != mOnValuesReadListener) mOnValuesReadListener.onException(exception);
        if (null != mOnValuesReadWithHeaderListener)
            mOnValuesReadWithHeaderListener.onException(exception);
    }
}
