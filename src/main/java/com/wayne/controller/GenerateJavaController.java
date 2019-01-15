package com.wayne.controller;

import com.wayne.utils.FileUtils;
import com.wayne.utils.ZipUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

            FileUtils.downLoadFile(zipFileName,new FileInputStream(filePath+zipFileName),request,response);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
