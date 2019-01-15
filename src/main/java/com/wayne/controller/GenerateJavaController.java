package com.wayne.controller;

import com.wayne.utils.StringUtils;
import com.wayne.utils.ZipUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URLEncoder;
import java.util.zip.ZipOutputStream;

@RestController
public class GenerateJavaController extends BaseController{

    @RequestMapping("/downloadJavaFile")
    public void downloadJavaFile(){
        try{
            String filePath = "G:\\tempFiles\\";
            String fileName = "GreetingController.java";
            String zipFileName = "GreetingController.zip";

            File file = new File(filePath + fileName);

            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(filePath+zipFileName));

            ZipUtils.doZip(file,out,"");

            InputStream inputStream = new FileInputStream(filePath+"GreetingController.zip");

            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            URLEncoder.encode(fileName,"UTF-8");
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

            byte[] car = new byte[1024];
            int L;
            while((L = inputStream.read(car)) != -1){
                if( car.length != 0) {
                    outputStream.write(car,0,L);
                }
            }

            if(outputStream!= null) {
                outputStream.flush();
                outputStream.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
