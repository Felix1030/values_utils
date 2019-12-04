package com.felix.values.utils.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Felix
 * @date 2019-12-02.
 * GitHub：https://github.com/Felix1030
 * email：felix.hua@mchain.pro
 * description：
 */
public class XmlUtils {

    /**
     * 将Content写入generatePath对应的文件中
     *
     * @param generatePath
     * @param content
     * @return
     */
    public static boolean generateResources(File generatePath, String content) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(generatePath);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(content);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtils.closeQuietly(printWriter, bufferedWriter, fileWriter);
        }
        return true;
    }

    /**
     * 将Content写入generatePath对应的文件中
     *
     * @param generatePath
     * @param content
     * @return
     */
    public static boolean generateResources(String generatePath, String content) {
        return generateResources(new File(generatePath), content);
    }

    /**
     * 将Content写入generatePath对应的文件中
     *
     * @param generatePath
     * @param content
     * @return
     */
    public static boolean generateResources(String generatePath, StringBuilder content) {
        return generateResources(generatePath, content.toString());
    }
}
