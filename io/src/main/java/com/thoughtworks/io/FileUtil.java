package com.thoughtworks.io;

import java.io.*;

public class FileUtil {

    /**
     * 完成复制文件夹方法:
     * 1. 把给定文件夹from下的所有文件(包括子文件夹)复制到to文件夹下
     * 2. 保证to文件夹为空文件夹，如果to文件夹不存在则自动创建
     * <p>
     * 例如把a文件夹(a文件夹下有1.txt和一个空文件夹c)复制到b文件夹，复制完成以后b文件夹下也有一个1.txt和空文件夹c
     */

    public static void main(String[] args) throws IOException {
        File from = new File(".\\io\\src\\main\\from");
        File to = new File(".\\io\\src\\main\\to");
        copyDirectory(from, to);
    }

    public static void copyDirectory(File from, File to) throws IOException {
        if (!to.exists()) {
            to.mkdirs();
        }
        File[] files = from.listFiles();
        for (File fileName : files) {
            if (fileName.isDirectory()) {
                copyDirectory(fileName, new File(to, fileName.getName()));
            } else {
                File copyFile = new File(to, fileName.getName());
                copyFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(copyFile);
                FileInputStream fis = new FileInputStream(fileName);
                int len = 0;
                while ((len = fis.read()) != -1) {
                    fos.write(len);
                }
            }
        }
    }
}

