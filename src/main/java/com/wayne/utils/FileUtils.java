package com.wayne.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class FileUtils {
    private FileUtils(){}

    public static void downLoadFile(
            String zipFileName,
            FileInputStream fis,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        setHeader(zipFileName,request,response);

        byte[] car = new byte[1024];
        int L;
        while((L = fis.read(car)) != -1){
            if( car.length != 0) {
                outputStream.write(car,0,L);
            }
        }

        if(outputStream!= null) {
            outputStream.flush();
            outputStream.close();
        }
    }

    public static void downLoadFile(String zipFileName,HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.flushBuffer();
    }

    public static void setHeader(String zipFileName,HttpServletRequest request, HttpServletResponse response) throws  IOException{
        URLEncoder.encode(zipFileName,"UTF-8");
        response.setContentType("application/doc");
        final String userAgent = request.getHeader("USER-AGENT");
        if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
            zipFileName = URLEncoder.encode(zipFileName,"UTF-8");
        }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
            zipFileName = new String(zipFileName.getBytes(), "ISO8859-1");
        }else{
            zipFileName = URLEncoder.encode(zipFileName,"UTF-8");//其他浏览器
        }

        response.addHeader("Content-Disposition","attachment;filename="+zipFileName);
    }
}
