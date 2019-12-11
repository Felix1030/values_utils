package com.felix.values.utils;

import com.felix.values.utils.constant.ValueState;
import com.felix.values.utils.types.ArraysToExcel;
import com.felix.values.utils.types.ExcelToArrays;
import com.felix.values.utils.types.ExcelToStrings;
import com.felix.values.utils.types.StringsToExcel;

import static com.felix.values.utils.constant.ResourcesConstants.EXCEL_SUFFIX_XLS;
import static com.felix.values.utils.constant.ResourcesConstants.EXCEL_SUFFIX_XLSX;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_EXPORT_NAME;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_ARRAY_IMPORT_NAME;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_STRING_EXPORT_FILE_NAME;
import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_STRING_IMPORT_FILE_NAME;

/**
 * @author Felix
 * date on 2019-11-29.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description： 用于Strings.xml、Arrays.xml与Excel之间相互转换使用
 */
public class ValueUtilsManager {

    // 导出或者导出的类型
    private ValueState valueState;
    // 若是导出或者导入arrays则需要此Key
    private String arraysXmlKey;
    // 导入时XLS 或者 XLSX存放的位置
    private String importFilePath;
    // 导出时Strings或者arrays 所在位置对应的res目录即可
    private String exportFilePath;
    // 导出的Strings Excel File Name 不设置则为默认的LanguageExcel.xlsx
    private String exportStringsExcelFilName;
    // 导入的Strings Excel File Name 不设置则为默认的LanguageExcel.xlsx
    private String importStringsExcelFilName;
    // 导出的Arrays Excel File Name 不设置则为默认的LanguageExcel.xlsx
    private String exportArraysExcelFilName;
    // 导入的Arrays Excel File Name 不设置则为默认的LanguageExcel.xlsx
    private String importArraysExcelFilName;

    public ValueState getValueState() {
        return valueState;
    }

    public void setValueState(ValueState valueState) {
        this.valueState = valueState;
    }

    public String getArraysXmlKey() {
        return arraysXmlKey;
    }

    public void setArraysXmlKey(String arraysXmlKey) {
        this.arraysXmlKey = arraysXmlKey;
    }

    public String getImportFilePath() {
        return importFilePath;
    }

    public void setImportFilePath(String importFilePath) {
        this.importFilePath = importFilePath;
    }

    public String getExportFilePath() {
        return exportFilePath;
    }

    public void setExportFilePath(String exportFilePath) {
        this.exportFilePath = exportFilePath;
    }

    public String getExportStringsExcelFilName() {
        return exportStringsExcelFilName;
    }

    public void setExportStringsExcelFilName(String exportStringsExcelFilName) {
        this.exportStringsExcelFilName = exportStringsExcelFilName;
    }

    public String getImportStringsExcelFilName() {
        return importStringsExcelFilName;
    }

    public void setImportStringsExcelFilName(String importStringsExcelFilName) {
        this.importStringsExcelFilName = importStringsExcelFilName;
    }

    public String getExportArraysExcelFilName() {
        return exportArraysExcelFilName;
    }

    public void setExportArraysExcelFilName(String exportArraysExcelFilName) {
        this.exportArraysExcelFilName = exportArraysExcelFilName;
    }

    public String getImportArraysExcelFilName() {
        return importArraysExcelFilName;
    }

    public void setImportArraysExcelFilName(String importArraysExcelFilName) {
        this.importArraysExcelFilName = importArraysExcelFilName;
    }

    /**
     * 执行ValueState对应的操作
     */
    public void execute() {
        switch (valueState) {
            case STRINGS_TO_EXCEL:
                // strings to excel
                StringsToExcel.exportStringsToExcel(
                        importFilePath,
                        exportFilePath,
                        exportStringsExcelFilName
                );
                break;
            case EXCEL_TO_STRINGS:
                // excel to strings
                ExcelToStrings.importExcelToStrings(
                        importFilePath,
                        importStringsExcelFilName,
                        exportFilePath
                );
                break;
            case ARRAYS_TO_EXCEL:
                // arrays to excel
                if (arraysXmlKey == null || arraysXmlKey.isEmpty()) {
                    throw new NullPointerException("arraysXmlKey must not be null");
                }
                ArraysToExcel.exportArraysToExcel(
                        importFilePath,
                        arraysXmlKey,
                        exportFilePath,
                        exportArraysExcelFilName
                );
                break;
            case EXCEL_TO_ARRAYS:
                // excel to arrays
                if (arraysXmlKey == null || arraysXmlKey.isEmpty()) {
                    throw new NullPointerException("arraysXmlKey must not be null");
                }
                ExcelToArrays.importExcelToArrays(
                        importFilePath,
                        importArraysExcelFilName,
                        exportFilePath,
                        arraysXmlKey
                );
                break;
            default:
                System.out.println("Do nothing");
                break;
        }
    }

    public static class ValueUtilsManagerBuilder {
        // 导出或者导出的类型
        private ValueState valueState;
        // 若是导出或者导入arrays则需要此Key
        private String arraysXmlKey;
        // 导入(Strings、Arrays的Excel)时XLS 或者 XLSX存放的位置
        // 若直接为文件全部路径则使用此路径  若只到文件根目录 则拼上 importStringsExcelFilName 或者 importArraysExcelFilName
        private String importFilePath;
        // 导出时Strings或者arrays 所在位置对应的res目录即可
        // 导出的文件路径为 exportFilePath + (exportStringsExcelFilName/exportArraysExcelFilName)
        private String exportFilePath;
        // 导入(Excel)和导出(strings\arrays)路径设置
        private String importAndExportPath;

        // 导出的Strings Excel File Name 不设置则为默认的LanguageExcel.xlsx
        private String exportStringsExcelFileName;
        // 导入的Strings Excel File Name 不设置则为默认的LanguageExcel.xlsx
        private String importStringsExcelFileName;
        // 导出的Arrays Excel File Name 不设置则为默认的LanguageExcel.xlsx
        private String exportArraysExcelFileName;
        // 导入的Arrays Excel File Name 不设置则为默认的LanguageExcel.xlsx
        private String importArraysExcelFileName;

        public ValueUtilsManagerBuilder() {
            this(ValueState.NONE);
        }

        public ValueUtilsManagerBuilder(ValueState valueState) {
            this.valueState = valueState;
        }

        /**
         * 导出的Strings Excel File Name 不设置则为默认的LanguageExcel.xlsx
         *
         * @param exportStringsExcelFileName
         * @return
         */
        public ValueUtilsManagerBuilder setExportStringsExcelFileName(String exportStringsExcelFileName) {
            this.exportStringsExcelFileName = exportStringsExcelFileName;
            return this;
        }

        /**
         * 导入的Strings Excel File Name 不设置则为默认的LanguageExcel.xlsx
         *
         * @param importStringsExcelFileName
         * @return
         */
        public ValueUtilsManagerBuilder setImportStringsExcelFileName(String importStringsExcelFileName) {
            this.importStringsExcelFileName = importStringsExcelFileName;
            return this;
        }

        /**
         * 导出的Arrays Excel File Name 不设置则为默认的LanguageExcel.xlsx
         *
         * @param exportArraysExcelFileName
         * @return
         */
        public ValueUtilsManagerBuilder setExportArraysExcelFileName(String exportArraysExcelFileName) {
            this.exportArraysExcelFileName = exportArraysExcelFileName;
            return this;
        }

        /**
         * 导入的Arrays Excel File Name 不设置则为默认的LanguageExcel.xlsx
         *
         * @param importArraysExcelFileName
         * @return
         */
        public ValueUtilsManagerBuilder setImportArraysExcelFileName(String importArraysExcelFileName) {
            this.importArraysExcelFileName = importArraysExcelFileName;
            return this;
        }

        /**
         * 导入和导出同路径设置
         * 这个与 importFilePath exportFilePath两个参数不可同时出现
         * 若两个都设置 则默认取单独设置的
         * 若importFilePath exportFilePath未设置 importAndExportPath设置了 则取该参数
         *
         * @param importAndExportPath
         * @return
         */
        public ValueUtilsManagerBuilder setImportAndExportPath(String importAndExportPath) {
            this.importAndExportPath = importAndExportPath;
            return this;
        }

        /**
         * 导出时Strings或者arrays 所在位置对应的res目录即可
         *
         * @param exportPath
         * @return
         */
        public ValueUtilsManagerBuilder setExportPath(String exportPath) {
            this.exportFilePath = exportPath;
            return this;
        }

        /**
         * 导入时XLS 或者 XLSX存放的位置
         *
         * @param importPath
         * @return
         */
        public ValueUtilsManagerBuilder setImportPath(String importPath) {
            this.importFilePath = importPath;
            return this;
        }

        /**
         * 若是导出或者导入arrays则需要此Key
         *
         * @param arraysName
         * @return
         */
        public ValueUtilsManagerBuilder setArraysXmlKey(String arraysName) {
            this.arraysXmlKey = arraysName;
            return this;
        }

        /**
         * 设置导入或者导出的类型
         *
         * @param valueState
         * @return
         */
        public ValueUtilsManagerBuilder setValueState(ValueState valueState) {
            this.valueState = valueState;
            return this;
        }

        /**
         * 检测设置的路径是否为空
         *
         * @param path
         * @return
         */
        private boolean isPathNotEmpty(String path) {
            return path != null && !path.isEmpty();
        }

        /**
         * 设置路径做容错处理
         *
         * @param path
         * @return
         */
        private String convertFilePath(String path) {
            String newPath = path;
            if (!newPath.endsWith("/")) {
                newPath += "/";
            }
            return newPath;
        }

        /**
         * 检测用户设置的导入导出的FileName
         *
         * @param fileName
         * @return
         */
        private boolean checkUserSetExporrOrImportFileNameValid(String fileName) {
            return fileName != null && !fileName.isEmpty() && (fileName.endsWith(EXCEL_SUFFIX_XLSX) || fileName.endsWith(EXCEL_SUFFIX_XLS));
        }

        /**
         * 创建对应的管理类
         *
         * @return
         */
        public ValueUtilsManager build() {
            final ValueUtilsManager manager = new ValueUtilsManager();
            // 设置执行的类型
            manager.setValueState(valueState);
            // 导出或者导入Arrays对应的Key
            manager.setArraysXmlKey(arraysXmlKey);
            // 设置通用路径
            if (isPathNotEmpty(importAndExportPath)) {
                String path = convertFilePath(importAndExportPath);
                // 设置导入路径
                manager.setImportFilePath(path);
                // 设置导出路径
                manager.setExportFilePath(path);
            }
            // 设置导入路径
            if (isPathNotEmpty(importFilePath))
                manager.setImportFilePath(convertFilePath(importFilePath));
            // 设置导出路径
            if (isPathNotEmpty(exportFilePath))
                manager.setExportFilePath(convertFilePath(exportFilePath));
            // 设置导出的strings Excel文件名
            String exportStringsToExcelFileName;
            if (!checkUserSetExporrOrImportFileNameValid(exportStringsExcelFileName)) {
                exportStringsToExcelFileName = RESOURCE_STRING_EXPORT_FILE_NAME;
            } else {
                exportStringsToExcelFileName = exportStringsExcelFileName;
            }
            manager.setExportStringsExcelFilName(exportStringsToExcelFileName);
            // 设置导入的strings Excel文件名
            String importStringsToExcelFileName;
            if (!checkUserSetExporrOrImportFileNameValid(importStringsExcelFileName)) {
                importStringsToExcelFileName = RESOURCE_STRING_IMPORT_FILE_NAME;
            } else {
                importStringsToExcelFileName = importStringsExcelFileName;
            }
            manager.setImportStringsExcelFilName(importStringsToExcelFileName);
            // 设置导出的arrays Excel文件名
            String exportArraysToExcelFileName;
            if (!checkUserSetExporrOrImportFileNameValid(exportArraysExcelFileName)) {
                exportArraysToExcelFileName = RESOURCE_ARRAY_EXPORT_NAME;
            } else {
                exportArraysToExcelFileName = exportArraysExcelFileName;
            }
            manager.setExportArraysExcelFilName(exportArraysToExcelFileName);
            // 设置导入的arrays Excel文件名
            String importArraysToExcelFileName;
            if (!checkUserSetExporrOrImportFileNameValid(importArraysExcelFileName)) {
                importArraysToExcelFileName = RESOURCE_ARRAY_IMPORT_NAME;
            } else {
                importArraysToExcelFileName = importArraysExcelFileName;
            }
            manager.setImportArraysExcelFilName(importArraysToExcelFileName);
            return manager;
        }
    }


}
