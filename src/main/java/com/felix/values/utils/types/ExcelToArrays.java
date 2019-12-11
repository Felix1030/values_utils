package com.felix.values.utils.types;

import com.felix.values.utils.types.listener.OnValuesReadWithHeaderListener;
import com.felix.values.utils.utils.ExcelUtils;
import com.felix.values.utils.utils.FileUtils;
import com.felix.values.utils.utils.ResourcesUtils;
import com.felix.values.utils.utils.XmlUtils;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_CODE_HEADER;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_NAME;

/**
 * @author Felix
 * date on 2019-12-03.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class ExcelToArrays {

    public static void main(String[] args) {
        String excelPath = "/Users/vincent/Downloads/export/Code.xlsx";
        String writeBasePath = "/Users/vincent/Desktop/workspace/PagingTest/app/src/main/res";
        String arrayName = "clock_error_codes";
        // 1.读取Excel文件数据
        ExcelUtils.readExcelData(excelPath, new OnValuesReadWithHeaderListener() {
            @Override
            public void onFinish(List<Map<Integer, String>> dataList, Map<Integer, String> headMap) {
                // 2.转换读取到的Excel数据到对应的需要生成Arrays的Key Values格式
                LinkedList<LinkedHashMap<String, String>> generateData = convertExcelToGenerateArraysCode(dataList, headMap.size());
                // 3.生成arraysCode文件
                for (int i = 0; i < generateData.size(); i++) {
                    // 4.要生成文件中的Key Values
                    LinkedHashMap<String, String> arrayCode = generateData.get(i);
                    // 5.获取要生成文件的Base 路径 默认为每列的Header
                    String baseFolderName = headMap.get(i);
                    // 6.过滤掉不需要导入的部分  如果是Key的那一列不需要生成文件
                    if (baseFolderName.equals(RESOURCE_ARRAY_CODE_HEADER)) continue;
                    // 7.调用具体的生成逻辑
                    generateResourcesArraysFile(arrayCode, baseFolderName, writeBasePath, arrayName);
                }
            }

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 导入Excel到Arrays
     *
     * @param importExcelPath     文件路径
     * @param importExcelName     文件名称
     * @param arraysWriteBasePath arrays生成的路径
     * @param arraysName          生成的arrays 对应的名称
     */
    public static void importExcelToArrays(
            String importExcelPath,
            String importExcelName,
            String arraysWriteBasePath,
            String arraysName) {
        // 1.读取Excel文件数据
        ExcelUtils.readExcelData(importExcelPath + importExcelName, new OnValuesReadWithHeaderListener() {
            @Override
            public void onFinish(List<Map<Integer, String>> dataList, Map<Integer, String> headMap) {
                // 2.转换读取到的Excel数据到对应的需要生成Arrays的Key Values格式
                LinkedList<LinkedHashMap<String, String>> generateData = convertExcelToGenerateArraysCode(dataList, headMap.size());
                // 3.生成arraysCode文件
                for (int i = 0; i < generateData.size(); i++) {
                    // 4.要生成文件中的Key Values
                    LinkedHashMap<String, String> arrayCode = generateData.get(i);
                    // 5.获取要生成文件的Base 路径 默认为每列的Header
                    String baseFolderName = headMap.get(i);
                    // 6.过滤掉不需要导入的部分  如果是Key的那一列不需要生成文件
                    if (baseFolderName.equals(RESOURCE_ARRAY_CODE_HEADER)) continue;
                    // 7.调用具体的生成逻辑
                    generateResourcesArraysFile(arrayCode, baseFolderName, arraysWriteBasePath, arraysName);
                }
            }

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 生成Arrays File
     *
     * @param arrayCode      参数
     * @param baseFolderName 生成文件的父及目录
     * @param writeBasePath  生成文件的路径
     * @param arrayName      arrays Name
     *                       生成文件路径 = writeBasePath + baseFolderName + arrays.xml
     */
    private static void generateResourcesArraysFile(LinkedHashMap<String, String> arrayCode, String baseFolderName, String writeBasePath, String arrayName) {
        // 1.创建路径
        String generateArrayPath = writeBasePath + File.separator + baseFolderName + File.separator;
        FileUtils.createDir(generateArrayPath);
        // 2.转换Key Values数据到写入Content格式
        StringBuilder generateContent = ResourcesUtils.convertExcelDataToResourcesArray(arrayCode, arrayName);
        // 3.执行写入逻辑
        XmlUtils.generateResources(generateArrayPath + RESOURCE_ARRAY_NAME, generateContent);
    }

    /**
     * 将读取到的Excel数据转换为可以生成Arrays文件的数据
     *
     * @param excelData
     * @param headerLength
     * @return
     */
    private static LinkedList<LinkedHashMap<String, String>> convertExcelToGenerateArraysCode(
            List<Map<Integer, String>> excelData, int headerLength) {
        LinkedList<LinkedHashMap<String, String>> result = new LinkedList<LinkedHashMap<String, String>>();
        // 生成跟Header数量一致的列表
        for (int i = 0; i < headerLength; i++) {
            result.add(new LinkedHashMap<String, String>());
        }
        // 循环excel数据
        for (Map<Integer, String> data : excelData) {
            // 循环遍历单条的数据并设置返回
            for (int i = 0; i < data.values().size(); i++) {
                String value = data.get(i);
                if (value == null) value = "";
                result.get(i).put(data.get(0), value);
            }
        }
        return result;
    }
}
