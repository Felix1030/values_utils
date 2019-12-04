package com.felix.values.utils;

import com.felix.values.utils.constant.ValueState;

/**
 * @author Felix
 * @date 2019-11-29.
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

    /**
     * 执行ValueState对应的操作
     */
    public void execute() {
        switch (valueState) {
            case STRINGS_TO_EXCEL:
                // strings to excel
                break;
            case EXCEL_TO_STRINGS:
                // excel to strings
                break;
            case ARRAYS_TO_EXCEL:
                // arrays to excel
                break;
            case EXCEL_TO_ARRAYS:
                // excel to arrays
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
        // 导入时XLS 或者 XLSX存放的位置
        private String importFilePath;
        // 导出时Strings或者arrays 所在位置对应的res目录即可
        private String exportFilePath;
        // 导入和导出同路径设置
        private String importAndExportPath;

        public ValueUtilsManagerBuilder() {
            this(ValueState.NONE);
        }

        public ValueUtilsManagerBuilder(ValueState valueState) {
            this.valueState = valueState;
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
            return importAndExportPath != null && !importAndExportPath.isEmpty();
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
                // 设置导入路径
                manager.setImportFilePath(importAndExportPath);
                // 设置导出路径
                manager.setExportFilePath(importAndExportPath);
            }
            // 设置导入路径
            if (isPathNotEmpty(importFilePath))
                manager.setImportFilePath(importFilePath);
            // 设置导出路径
            if (isPathNotEmpty(exportFilePath))
                manager.setExportFilePath(exportFilePath);
            return manager;
        }
    }


}
