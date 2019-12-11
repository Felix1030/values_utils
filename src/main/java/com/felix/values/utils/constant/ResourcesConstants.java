package com.felix.values.utils.constant;

/**
 * @author Felix
 * date on 2019-12-02.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class ResourcesConstants {

    // 生成的Resources文件头
    public static final String RESOURCE_DOC_TYPE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    // 生成的Resources文件开始标签
    public static final String RESOURCE_START_TAG = "<resources>";
    // 生成的Resources文件结束标签
    public static final String RESOURCE_END_TAG = "</resources>";
    // 生成的Resources文件结束标签
    public static final String RESOURCE_NEWLINE = "\r\n";
    // 生成的Resources文件头带换行
    public static final String RESOURCE_DOC_TYPE_NEWLINE = RESOURCE_DOC_TYPE + RESOURCE_NEWLINE;
    // 生成的Resources文件开始标签
    public static final String RESOURCE_START_TAG_NEWLINE = RESOURCE_START_TAG + RESOURCE_NEWLINE;
    // 生成的Resources文件结束标签
    public static final String RESOURCE_END_TAG_NEWLINE = RESOURCE_END_TAG + RESOURCE_NEWLINE;
    // android res下values文件
    public static final String RESOURCE_VALUES_FOLDER = "values";

    // Resources strings文件名称
    public static final String RESOURCE_STRING_NAME = "strings.xml";
    // 导出的Excel Strings Key对应的header
    public static final String RESOURCE_STRING_KEY_HEADER = "key";
    // Resources strings Item标签开始
    public static final String RESOURCE_STRING_PREFIX = "    <string name=\"";
    // Resources strings Item标签开始闭合
    public static final String RESOURCE_STRING_MIDDLE = "\">";
    // Resources strings Item标签结束
    public static final String RESOURCE_STRING_SUFFIX = "</string>";
    // Resources strings Item标签结束带换行
    public static final String RESOURCE_STRING_SUFFIX_NEWLINE = RESOURCE_STRING_SUFFIX + RESOURCE_NEWLINE;
    // Resources strings 导出的Excel文件名称
    public static final String RESOURCE_STRING_EXPORT_FILE_NAME = "LanguageExcel.xlsx";
    // Resources strings 导入的Excel文件名称
    public static final String RESOURCE_STRING_IMPORT_FILE_NAME = RESOURCE_STRING_EXPORT_FILE_NAME;

    // Resources arrays文件名称
    public static final String RESOURCE_ARRAY_NAME = "arrays.xml";
    // 导出的Excel Arrays Code对应的header
    public static final String RESOURCE_ARRAY_CODE_HEADER = "code";
    // Resources arrays 外层Start Tag
    public static final String RESOURCE_ARRAY_BASE_START_TAG = "    <string-array name=\"";
    // Resources arrays 外层Start End Tag
    public static final String RESOURCE_ARRAY_BASE_START_END_TAG = "\">";
    // Resources arrays 外层Start End Tag
    public static final String RESOURCE_ARRAY_BASE_START_END_TAG_NEWLINE = RESOURCE_ARRAY_BASE_START_END_TAG + RESOURCE_NEWLINE;
    // Resources arrays 外层End Tag
    public static final String RESOURCE_ARRAY_BASE_END_TAG = "    </string-array>";
    // Resources arrays 外层End Tag
    public static final String RESOURCE_ARRAY_BASE_END_TAG_NEWLINE = RESOURCE_ARRAY_BASE_END_TAG + RESOURCE_NEWLINE;
    // Resources arrays Start Tag
    public static final String RESOURCE_ARRAY_START_TAG = "        <item>";
    // Resources arrays End Tag
    public static final String RESOURCE_ARRAY_END_TAG = "</item>";
    // Resources arrays End Tag
    public static final String RESOURCE_ARRAY_END_TAG_NEWLINE = RESOURCE_ARRAY_END_TAG + RESOURCE_NEWLINE;
    // Resources arrays 数据拼接符号
    public static final String RESOURCE_ARRAY_LINK_SYMBOL = "=";
    // Resources arrays 导出的Excel名称
    public static final String RESOURCE_ARRAY_EXPORT_NAME = "Code.xlsx";
    // Resources arrays 导入的Excel名称
    public static final String RESOURCE_ARRAY_IMPORT_NAME = RESOURCE_ARRAY_EXPORT_NAME;

    // 导入或者导出文件结尾
    public static final String EXCEL_SUFFIX_XLSX = ".xlsx";
    public static final String EXCEL_SUFFIX_XLS = ".xls";
}
