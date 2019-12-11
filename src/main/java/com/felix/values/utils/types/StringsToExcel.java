package com.felix.values.utils.types;

import com.felix.values.utils.constant.ResourcesConstants;
import com.felix.values.utils.utils.ExcelUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_STRING_NAME;

/**
 * @author Felix
 * date on 2019-11-28.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class StringsToExcel {

    private static final String EXPORT_VALUES_PATH = "/Users/vincent/Downloads/export";
    // 加载资源文件的绝对地址
    private static final String LOAD_VALUES_PATH = "/Users/vincent/Desktop/workspace/PagingTest/app/src/main/res";
    // 资源文件Folder前缀
    private static final String VALUES_PREFIX = "values";
    private static final String ARRAYS_NAME = "arrays.xml";
    private static final String STRINGS_NAME = "strings.xml";
    private static final String BASIC_NAMESPACE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";

    /**
     * 导出步骤
     * 1.读取本地所有Values文件
     * 2.读取本地所有Values下 strings、arrays文件
     * 3.转换读取的strings、arrays到导出数据结构
     * 4.导出Excel
     */

    public static void main(String[] args) {
        LinkedList<File> values = initValuesFiles(LOAD_VALUES_PATH);

        List<String> exportHeaders = new ArrayList<>();
        exportHeaders.add("Key");
        for (File result : values) {
            System.out.println(result.getAbsolutePath());
            exportHeaders.add(result.getName());
        }
        LinkedList<File> needFiles = initValuesNeededFile(values, STRINGS_NAME);
        // 读取Strings中参数
        LinkedList<LinkedHashMap<String, String>> maps = initStringsKeyAndValue(needFiles);

        // 获取导出的数据
        LinkedList<LinkedList<String>> data = initExcelDatas(maps, exportHeaders);

        ExcelUtils.exportExcel(
                EXPORT_VALUES_PATH,
                "LanguageExcel.xlsx",
                data, exportHeaders);
    }


    /**
     * strings文件导出到excel
     *
     * @param stringsBasePath android res目录
     * @param exportExcelPath 导出的excel文件目录
     * @param exportExcelName 导出的excel文件名称
     */
    public static void exportStringsToExcel(
            String stringsBasePath,
            String exportExcelPath,
            String exportExcelName
    ) {
        LinkedList<File> values = initValuesFiles(stringsBasePath);

        List<String> exportHeaders = new ArrayList<>();
        exportHeaders.add(ResourcesConstants.RESOURCE_STRING_KEY_HEADER);
        for (File result : values) {
            System.out.println(result.getAbsolutePath());
            exportHeaders.add(result.getName());
        }
        LinkedList<File> needFiles = initValuesNeededFile(values, RESOURCE_STRING_NAME);
        // 读取Strings中参数
        LinkedList<LinkedHashMap<String, String>> maps = initStringsKeyAndValue(needFiles);

        // 获取导出的数据
        LinkedList<LinkedList<String>> data = initExcelDatas(maps, exportHeaders);

        ExcelUtils.exportExcel(
                exportExcelPath,
                exportExcelName,
                data, exportHeaders);
    }


    private static List<String> head() {
        List<String> list = new ArrayList<String>();
        list.add("字符串" + System.currentTimeMillis());
        list.add("数字" + System.currentTimeMillis());
        list.add("日期" + System.currentTimeMillis());
        return list;
    }

    private static List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }

    /**
     * 外层列表代表行  内层列表代表列
     *
     * @param keyMaps
     * @param exportHeaders
     * @return
     */
    private static LinkedList<LinkedList<String>> initExcelDatas(LinkedList<LinkedHashMap<String, String>> keyMaps, List<String> exportHeaders) {
        LinkedList<LinkedList<String>> results = new LinkedList<>();
        Map<String, String> stringStringMap = keyMaps.get(0);
        for (String s : stringStringMap.keySet()) {
            LinkedList<String> values = new LinkedList<String>();
            values.add(s);
            for (LinkedHashMap<String, String> keyMap : keyMaps) {
                values.add(keyMap.get(s));
            }
            results.add(values);
        }
        return results;
    }

    private static List<String> getMapKeys(List<String> keys, Map<String, String> params) {
        List<String> results = new ArrayList<>();
        for (String key : keys) {
            String value = params.get(key);
            if (value == null) value = "NONE";
            results.add(value);
        }
        return results;
    }


    /**
     * 获取Strings.xml文件中的值
     *
     * @param stringFiles
     * @return
     */
    private static LinkedList<LinkedHashMap<String, String>> initStringsKeyAndValue(LinkedList<File> stringFiles) {
        LinkedList<LinkedHashMap<String, String>> listOfMaps = new LinkedList<LinkedHashMap<String, String>>();
        for (File stringFile : stringFiles) {
            LinkedHashMap<String, String> map = readStringsContentWithFile(stringFile);
            listOfMaps.add(map);
        }
        return listOfMaps;
    }

    /**
     * 读取单个Strings.xml文件内容
     *
     * @param stringFile
     * @return
     */
    private static LinkedHashMap<String, String> readStringsContentWithFile(File stringFile) {
        LinkedHashMap<String, String> maps = new LinkedHashMap<String, String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(stringFile));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("</string>")) {
                    int keyStartIndex = index(tempString, "name=\"");
                    int keyEndIndex = tempString.indexOf("\">");
                    String key = tempString.substring(keyStartIndex, keyEndIndex);
                    System.out.println("key " + key);

                    int valueStartIndex = index(tempString, "\">");
                    int valueEndIndex = tempString.indexOf("</");
                    String value = tempString.substring(valueStartIndex, valueEndIndex);
                    System.out.println("value " + value);
                    maps.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maps;
    }

    /**
     * 获取初次Index
     *
     * @param srcStr
     * @param indexStr
     * @return
     */
    private static int index(String srcStr, String indexStr) {
        int indexLength = indexStr.length();
        return srcStr.indexOf(indexStr) + indexLength;
    }

    /**
     * 检测获取到的File是否包含Values
     *
     * @param file
     */
    private static boolean checkValuesFile(File file) {
        if (file == null) return false;
        String fileName = file.getName();
        return fileName.startsWith(VALUES_PREFIX) && file.isDirectory();
    }

    /**
     * 检测文件是否是需要的文件
     *
     * @param file
     * @param needFileName
     * @return
     */
    private static boolean checkValuesFileIfNeeded(File file, String needFileName) {
        if (file == null || (needFileName == null || needFileName.isEmpty())) return false;
        return file.getName().equals(needFileName);
    }

    /**
     * 获取指定目录下的所有Values文件 多语言
     *
     * @param resourcePath
     * @return
     */
    private static LinkedList<File> initValuesFiles(String resourcePath) {
        File resourceFile = new File(resourcePath);
        if (!resourceFile.exists()) return null;
        LinkedList<File> valuesFile = new LinkedList<>();
        File[] allFiles = resourceFile.listFiles();
        for (File file : allFiles) {
            if (checkValuesFile(file)) valuesFile.add(file);
        }
        return valuesFile;
    }

    /**
     * 获取Values文件中需要的文件
     *
     * @param valuesFile
     * @param needFileName
     * @return
     */
    private static LinkedList<File> initValuesNeededFile(LinkedList<File> valuesFile, String needFileName) {
        if (valuesFile == null || valuesFile.size() <= 0
                || needFileName == null || needFileName.isEmpty()) return null;
        LinkedList<File> neededFiles = new LinkedList<>();
        for (File file : valuesFile) {
            File[] childFiles = file.listFiles();
            for (File childFile : childFiles) {
                if (checkValuesFileIfNeeded(childFile, needFileName)) neededFiles.add(childFile);
            }
        }
        return neededFiles;
    }
}
