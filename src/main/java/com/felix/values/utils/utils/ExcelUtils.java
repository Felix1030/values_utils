package com.felix.values.utils.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.felix.values.utils.types.listener.OnValuesReadListener;
import com.felix.values.utils.types.listener.OnValuesReadWithHeaderListener;
import com.felix.values.utils.types.listener.ValuesReadExcelListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Felix
 * @date 2019-11-29.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class ExcelUtils {
    private static WriteSheet mWriteSheet;

    static {
        mWriteSheet = new WriteSheet();
        mWriteSheet.setSheetName("WriteSheetName");
    }

    /**
     * @param exportPath
     * @param exportFileName
     * @param data
     * @param headers
     */
    public static void exportExcel(String exportPath,
                                   String exportFileName,
                                   LinkedList<LinkedList<String>> data,
                                   List<String> headers) {
        exportExcel(exportPath, exportFileName, data, headers, null);
    }

    /**
     * 导出资源到Excel
     *
     * @param exportPath     导出的文件路径
     * @param exportFileName
     * @param data
     * @param headers
     * @param sheet
     */
    public static void exportExcel(String exportPath,
                                   String exportFileName,
                                   LinkedList<LinkedList<String>> data,
                                   List<String> headers,
                                   WriteSheet sheet) {
        sheet = (sheet != null) ? sheet : mWriteSheet;
        List<List<String>> list = new ArrayList<>();
        if (headers != null) {
            for (String header : headers) {
                list.add(Collections.singletonList(header));
            }
            sheet.setHead(list);
        }
        try {
            EasyExcel.write(exportPath + File.separator + exportFileName)
                    .head(list)
                    .sheet()
                    .doWrite(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取Excel数据
     *
     * @param realExcelPath
     * @param onValuesReadListener
     */
    public static void readExcelData(String realExcelPath, OnValuesReadListener onValuesReadListener) {
        if (!ValuesCheckUtils.checkImportExcelPath(realExcelPath)) {
            if (null != onValuesReadListener)
                onValuesReadListener.onException(new FileNotFoundException());
        }
        EasyExcel.read(realExcelPath, new ValuesReadExcelListener(onValuesReadListener)).sheet().doRead();
    }

    /**
     * 读取Excel数据
     * @param realExcelPath
     * @param onValuesReadWithHeaderListener
     */
    public static void readExcelData(String realExcelPath, OnValuesReadWithHeaderListener onValuesReadWithHeaderListener) {
        if (!ValuesCheckUtils.checkImportExcelPath(realExcelPath)) {
            if (null != onValuesReadWithHeaderListener)
                onValuesReadWithHeaderListener.onException(new FileNotFoundException());
        }
        EasyExcel.read(realExcelPath, new ValuesReadExcelListener(onValuesReadWithHeaderListener)).sheet().doRead();
    }
}
