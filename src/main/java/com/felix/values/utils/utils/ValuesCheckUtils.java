package com.felix.values.utils.utils;

import java.io.File;

import static com.felix.values.utils.constant.ValuesUtilsConstant.EXCEL_SUFFIX_XLS;
import static com.felix.values.utils.constant.ValuesUtilsConstant.EXCEL_SUFFIX_XLSX;

/**
 * @author Felix
 * @date 2019-11-29.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class ValuesCheckUtils {

    /**
     * 检测导入的时候Excel文件的路径
     *
     * @param excelPath
     * @return
     */
    public static boolean checkImportExcelPath(String excelPath) {
        return excelPath != null && !excelPath.isEmpty() &&
                (excelPath.endsWith(EXCEL_SUFFIX_XLSX)
                        || excelPath.endsWith(EXCEL_SUFFIX_XLS))
                && new File(excelPath).exists();
    }


}
