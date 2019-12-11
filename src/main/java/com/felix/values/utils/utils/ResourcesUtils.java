package com.felix.values.utils.utils;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_BASE_END_TAG_NEWLINE;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_BASE_START_END_TAG_NEWLINE;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_BASE_START_TAG;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_END_TAG_NEWLINE;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_LINK_SYMBOL;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_START_TAG;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_DOC_TYPE_NEWLINE;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_END_TAG_NEWLINE;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_START_TAG_NEWLINE;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_STRING_MIDDLE;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_STRING_PREFIX;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_STRING_SUFFIX_NEWLINE;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_VALUES_FOLDER;

/**
 * @author Felix
 * date on 2019-12-02.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class ResourcesUtils {

    /**
     * 组合读取的Excel参数
     *
     * @param dataMap key value 参数
     * @return
     */
    public static StringBuilder convertExcelDataToResourcesString(LinkedHashMap<String, String> dataMap) {
        StringBuilder sb = new StringBuilder();
        sb.append(RESOURCE_DOC_TYPE_NEWLINE)
                .append(RESOURCE_START_TAG_NEWLINE);
        for (Map.Entry<String, String> keyValue : dataMap.entrySet()) {
            sb.append(RESOURCE_STRING_PREFIX)
                    .append(keyValue.getKey())
                    .append(RESOURCE_STRING_MIDDLE)
                    .append(keyValue.getValue())
                    .append(RESOURCE_STRING_SUFFIX_NEWLINE);
        }
        sb.append(RESOURCE_END_TAG_NEWLINE);
        return sb;
    }

    /**
     * 组合读取的Excel参数 转换为
     *
     * @param dataMap key value 参数
     * @return
     */
    public static StringBuilder convertExcelDataToResourcesArray(LinkedHashMap<String, String> dataMap, String arrayName) {
        StringBuilder sb = new StringBuilder();
        sb.append(RESOURCE_DOC_TYPE_NEWLINE)
                .append(RESOURCE_START_TAG_NEWLINE)
                .append(RESOURCE_ARRAY_BASE_START_TAG)
                .append(arrayName)
                .append(RESOURCE_ARRAY_BASE_START_END_TAG_NEWLINE);
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            sb.append(RESOURCE_ARRAY_START_TAG)
                    .append(entry.getKey())
                    .append(RESOURCE_ARRAY_LINK_SYMBOL)
                    .append(entry.getValue())
                    .append(RESOURCE_ARRAY_END_TAG_NEWLINE);
        }
        sb.append(RESOURCE_ARRAY_BASE_END_TAG_NEWLINE)
                .append(RESOURCE_END_TAG_NEWLINE);
        return sb;
    }

    /**
     * 获取parentPath 路径下所有Values开头的Folder
     *
     * @param parentPath
     * @return
     */
    public static LinkedList<File> getAllValuesFolder(String parentPath) {
        File valuesBaseFile = new File(parentPath);
        if (!valuesBaseFile.exists()) return null;
        LinkedList<File> valuesFiles = new LinkedList<File>();
        File[] allFiles = valuesBaseFile.listFiles();
        if (allFiles == null) return null;
        for (File file : allFiles) {
            if (checkValuesFile(file)) valuesFiles.add(file);
        }
        return valuesFiles;
    }

    /**
     * 获取Values文件下所有的Arrays文件
     *
     * @param valuesFiles
     * @return
     */
    public static LinkedList<String> getAllHeadersFile(LinkedList<File> valuesFiles, String defaultHeader) {
        if (valuesFiles == null) return null;
        LinkedList<String> headers = new LinkedList<String>();
        headers.add(defaultHeader);
        for (File valuesFile : valuesFiles) {
            headers.add(valuesFile.getName());
        }
        return headers;
    }

    /**
     * 筛选指定问价夹以及子文件中的文件
     *
     * @param valuesFiles
     * @param filterFileName
     * @return
     */
    public static LinkedList<File> getFilterResourcesFileList(LinkedList<File> valuesFiles, String filterFileName) {
        if (valuesFiles == null || valuesFiles.size() <= 0
                || filterFileName == null || filterFileName.isEmpty()) return null;
        LinkedList<File> neededFiles = new LinkedList<>();
        for (File file : valuesFiles) {
            File[] childFiles = file.listFiles();
            if (null != childFiles) {
                for (File childFile : childFiles) {
                    if (checkValuesFileIfNeeded(childFile, filterFileName))
                        neededFiles.add(childFile);
                }
            }
        }
        return neededFiles;
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
     * 检测获取到的File是否包含Values
     *
     * @param file
     */
    public static boolean checkValuesFile(File file) {
        if (file == null) return false;
        String fileName = file.getName();
        return fileName.startsWith(RESOURCE_VALUES_FOLDER) && file.isDirectory();
    }
}
