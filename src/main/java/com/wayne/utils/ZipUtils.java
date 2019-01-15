package com.wayne.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    private ZipUtils(){} // 隐藏构造函数，是个工具类

    /**
     * 压缩文件核心类
     * @param inFile 要压缩的文件
     * @param out   压缩文件的输出流
     * @param dir   压缩文件的输出路径
     * @throws IOException 抛出文件异常
     */
    public static void doZip(File inFile, ZipOutputStream out,String dir) throws IOException {
        String entryName = null;
        if(!StringUtils.equals("",dir)){
            entryName = dir + "/" + inFile.getName();
        } else {
            entryName = inFile.getName();
        }

        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);

        int len = 0;
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(inFile);
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer,0,len);
            out.flush();
        }
        out.closeEntry();
        out.close();
        fis.close();
    }

    public static void main(String[] args) throws Exception {
        String filePath = "G:\\tempFiles\\";
        String fileName = "GreetingController.java";
        String zipFileName = "GreetingController.zip";

        File file = new File(filePath + fileName);

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(filePath+zipFileName));

        ZipUtils.doZip(file,out,"");
    }
}
