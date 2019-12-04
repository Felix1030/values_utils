package com.felix.values.utils.types;

import com.felix.values.utils.types.listener.OnValuesReadWithHeaderListener;
import com.felix.values.utils.utils.ExcelUtils;
import com.felix.values.utils.utils.FileUtils;
import com.felix.values.utils.utils.ResourcesUtils;
import com.felix.values.utils.utils.XmlUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_STRING_KEY_HEADER;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_STRING_NAME;

/**
 * @author Felix
 * @date 2019-11-28.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class ExcelToStrings {

    /**
     * 导入步骤
     * 1.读取本地Excel文件
     * 2.转换Excel文件到对应格式
     * 3.转换对应格式到对应的strings、arrays文件
     * 4.写入对应的文件
     */
    public static void main(String[] args) {
        String excelPath = "/Users/vincent/Downloads/export/LanguageExcel.xlsx";
        // 代表写入的父路径 写入时会拼上 xml header/文件名
        String writeBasePath = "/Users/vincent/Desktop/workspace/PagingTest/app/src/main/res";
        // 1 读取Excel
        ExcelUtils.readExcelData(excelPath, new OnValuesReadWithHeaderListener() {
            @Override
            public void onFinish(List<Map<Integer, String>> dataList, Map<Integer, String> headMap) {
                // 2.转换Excel到可生成Strings的Kay Values文件
                List<LinkedHashMap<String, String>> linkedHashMaps = convertExcelDataToGenerate(dataList, headMap.size());
                for (int i = 0; i < linkedHashMaps.size(); i++) {
                    // 3.获取单列的Key Value数据
                    LinkedHashMap<String, String> linkedHashMap = linkedHashMaps.get(i);
                    // 4.获取生成文件的Base Folder
                    String parentFolderName = headMap.get(i);
                    // 5.过滤掉Key哪一列
                    if (parentFolderName.equals(RESOURCE_STRING_KEY_HEADER)) continue;
                    // 6.生成文件
                    generateResourcesFileByMap(linkedHashMap, writeBasePath, parentFolderName);
                }
            }

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 根据Excel导入的数据生成对应的文件
     *
     * @param linkedHashMap
     */
    private static void generateResourcesFileByMap(LinkedHashMap<String, String> linkedHashMap, String baseFolderPath, String parentFolderName) {
        /**
         * 1.判断父路径是否存在 不存在则创建
         * 2.执行循环写入操作
         */
        // 1 创建写入文件路径
        String baseWriteFolderPath = baseFolderPath + File.separator + parentFolderName + File.separator;
        FileUtils.createDir(baseWriteFolderPath);
        // 2.转换写入资源
        StringBuilder writeContent = ResourcesUtils.convertExcelDataToResourcesString(linkedHashMap);
        // 3.执行生成操作
        XmlUtils.generateResources(baseWriteFolderPath + RESOURCE_STRING_NAME, writeContent);
    }

    /**
     * 数据转换
     *
     * @param excelData
     * @return
     */
    private static List<LinkedHashMap<String, String>> convertExcelDataToGenerate(List<Map<Integer, String>> excelData, int headerLength) {
        List<LinkedHashMap<String, String>> excel = new ArrayList<>();
        for (int i = 0; i < headerLength; i++) {
            excel.add(new LinkedHashMap<>());
        }
        // 数据转换部分
        for (int i = 0; i < excelData.size(); i++) {
            Map<Integer, String> data = excelData.get(i);
            /**
             * 这里如果引用HeaderCount那么生成的文件中对应的Key有 对应的值为空
             * 如果用Values的Size 那么就是生成的只有有的Key
             */
            for (int i1 = 0; i1 < data.values().size(); i1++) {
                String value = data.get(i1);
                if (value == null) value = "";
                excel.get(i1).put(data.get(0), value);
            }
        }
        return excel;
    }
}
