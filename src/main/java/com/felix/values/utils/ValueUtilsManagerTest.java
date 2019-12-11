package com.felix.values.utils;

import com.felix.values.utils.constant.ValueState;

/**
 * @author Felix
 * date 2019-12-11.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class ValueUtilsManagerTest {

    public static void main(String[] args) {
        new ValueUtilsManager.ValueUtilsManagerBuilder(ValueState.NONE)
                .setImportAndExportPath("/Users/vincent/Desktop/workspace/PagingTest/app/src/main/res")
                .setArraysXmlKey("clock_error_codes")
                .build()
                .execute();
    }
}
