package com.felix.values.utils.types;

import com.felix.values.utils.utils.ExcelUtils;
import com.felix.values.utils.utils.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_BASE_END_TAG;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_BASE_START_END_TAG;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_BASE_START_TAG;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_CODE_HEADER;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_END_TAG;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_LINK_SYMBOL;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_NAME;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_START_TAG;
import static com.felix.values.utils.utils.ResourcesUtils.getAllHeadersFile;
import static com.felix.values.utils.utils.ResourcesUtils.getAllValuesFolder;
import static com.felix.values.utils.utils.ResourcesUtils.getFilterResourcesFileList;

/**
 * @author Felix
 * @date 2019-12-02.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class ArraysToExcel {

    public static void main(String[] args) {
        /**
         * 读取所有的values开头的文件夹
         */
        String loadPath = "/Users/vincent/Desktop/workspace/PagingTest/app/src/main/res";
        String arraysKey = "clock_error_codes";
        String exportPath = "/Users/vincent/Downloads/export";

        // 1.获取所有Values文件
        LinkedList<File> allValuesFolder = getAllValuesFolder(loadPath);
        // 2.配置以及获取所有Header
        LinkedList<String> allHeaders = getAllHeadersFile(allValuesFolder, RESOURCE_ARRAY_CODE_HEADER);
        // 3.获取所有符合条件的文件
        LinkedList<File> filterResourcesFileList = getFilterResourcesFileList(allValuesFolder, RESOURCE_ARRAY_NAME);
        // 4.读取文件中数据
        LinkedList<LinkedHashMap<String, String>> linkedHashMaps = initAllFilesArraysCodes(filterResourcesFileList, arraysKey);
        // 5.读取到的arrays数据转换为导出excel需要的数据
        LinkedList<LinkedList<String>> arraysExportExcelData = convertArraysCodesToExcelData(linkedHashMaps);
        // 6.执行导出
        ExcelUtils.exportExcel(exportPath, "Code.xlsx", arraysExportExcelData, allHeaders);
    }

    public static void exportArraysToExcel() {

    }

    /**
     * 将读取到的数据转换为Excel导出需要的参数
     * 外层列表代表行  内层列表代表列 数据结构有些奇怪
     *
     * @param arraysCodes
     * @return
     */
    private static LinkedList<LinkedList<String>> convertArraysCodesToExcelData(LinkedList<LinkedHashMap<String, String>> arraysCodes) {
        if (null == arraysCodes || arraysCodes.size() <= 0) return null;
        LinkedList<LinkedList<String>> results = new LinkedList<>();
        // 暂时写的是以默认的为基准 后期增加可自行设置
        LinkedHashMap<String, String> baseMap = arraysCodes.get(0);
        // 循环所有的Key
        for (String key : baseMap.keySet()) {
            LinkedList<String> values = new LinkedList<String>();
            values.add(key);
            for (LinkedHashMap<String, String> keyMap : arraysCodes) {
                values.add(keyMap.get(key));
            }
            results.add(values);
        }
        return results;
    }

    /**
     * 读取每个Arrays文件中的信息
     *
     * @param filterResourcesFileList
     * @return
     */
    private static LinkedList<LinkedHashMap<String, String>> initAllFilesArraysCodes(LinkedList<File> filterResourcesFileList, String arraysKeyCode) {
        if (null == filterResourcesFileList) return null;
        LinkedList<LinkedHashMap<String, String>> arrayCodes = new LinkedList<LinkedHashMap<String, String>>();
        for (File file : filterResourcesFileList) {
            arrayCodes.add(readArrayCodeByFile(file, arraysKeyCode));
        }
        return arrayCodes;
    }

    /**
     * 读取单个文件中的arrays数据
     *
     * @param file
     * @param arrayCodes
     * @return
     */
    private static LinkedHashMap<String, String> readArrayCodeByFile(File file, String arrayCodes) {
        LinkedHashMap<String, String> arrayCode = new LinkedHashMap<String, String>();
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
            String contentLine;
            // 是否含有指定的Key
            boolean hasFilterArrayCodes = false;
            // 是否读取到指定Key的末尾
            boolean isFilterArrayCodesEnd = false;
            while ((contentLine = bufferedReader.readLine()) != null && !isFilterArrayCodesEnd) {
                if (hasFilterArrayCodes) {
                    if (contentLine.startsWith(RESOURCE_ARRAY_START_TAG)
                            && contentLine.endsWith(RESOURCE_ARRAY_END_TAG)) {
                        // 读取对应的数据
                        System.out.println("export array data " + contentLine);

                        String data = contentLine.replace(RESOURCE_ARRAY_START_TAG, "")
                                .replace(RESOURCE_ARRAY_END_TAG, "");
                        String[] contents = data.split(RESOURCE_ARRAY_LINK_SYMBOL);
                        if (contents.length == 2) {
                            arrayCode.put(contents[0], contents[1]);
                        }
                    }
                } else {
                    // 循环查找对应的Key
                    hasFilterArrayCodes = contentLine.equals(
                            RESOURCE_ARRAY_BASE_START_TAG + arrayCodes + RESOURCE_ARRAY_BASE_START_END_TAG);
                }
                // 找不到继续找
                if (!hasFilterArrayCodes) continue;
                // 是否读到了根节点
                isFilterArrayCodesEnd = contentLine.equals(RESOURCE_ARRAY_BASE_END_TAG);
                if (isFilterArrayCodesEnd) hasFilterArrayCodes = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bufferedReader, reader);
        }
        return arrayCode;
    }

}
