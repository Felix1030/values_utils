package com.felix.values.utils.utils;

import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileSystemView;

import static com.felix.values.utils.constant.ResourcesConstants.RESOURCE_VALUES_FOLDER;

/**
 * @author Felix
 * date on 2019-12-02.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class FileUtils {

    /**
     * 获取操作系统桌面路径
     *
     * @return
     */
    public static String getOSDesktopPath() {
        File desktopFile = FileSystemView.getFileSystemView().getHomeDirectory();
        return desktopFile.getAbsolutePath();
    }

    /**
     * 检测所有Values Folder
     * @param file
     * @return
     */
    public static boolean isValusFolder(File file) {
        return file.getName().startsWith(RESOURCE_VALUES_FOLDER) && file.isDirectory();
    }

    /**
     * 创建文件夹
     *
     * @param path
     */
    public static void createPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                // 获取父文件
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
//                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 创建目录
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {// 判断目录是否存在
            System.out.println("创建目录失败，目标目录已存在！");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
            destDirName = destDirName + File.separator;
        }
        if (dir.mkdirs()) {// 创建目标目录
            System.out.println("创建目录成功！" + destDirName);
            return true;
        } else {
            System.out.println("创建目录失败！");
            return false;
        }
    }

    /**
     * 创建写入文件路径
     *
     * @param baseFolderPath
     * @param destDirName
     */
    public static void createDir(String baseFolderPath, String destDirName) {
        String writePath = baseFolderPath + File.separator + destDirName + File.separator;
        FileUtils.createDir(writePath);
    }


    // 创建单个文件
    public static boolean createFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {// 判断文件是否存在
            System.out.println("目标文件已存在" + filePath);
            return false;
        }
        if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
            System.out.println("目标文件不能为目录！");
            return false;
        }
        if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
            // 如果目标文件所在的文件夹不存在，则创建父文件夹
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
                System.out.println("创建目标文件所在的目录失败！");
                return false;
            }
        }
        try {
            if (file.createNewFile()) {// 创建目标文件
                System.out.println("创建文件成功:" + filePath);
                return true;
            } else {
                System.out.println("创建文件失败！");
                return false;
            }
        } catch (IOException e) {// 捕获异常
            e.printStackTrace();
            System.out.println("创建文件失败！" + e.getMessage());
            return false;
        }
    }

}
