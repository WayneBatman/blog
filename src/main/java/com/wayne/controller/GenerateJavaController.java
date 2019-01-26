package com.wayne.controller;

import com.sun.corba.se.impl.encoding.BufferManagerWrite;
import com.wayne.bean.ClassColumn;
import com.wayne.bean.ClassMainProperty;
import com.wayne.utils.CollectionUtils;
import com.wayne.utils.FileUtils;
import com.wayne.utils.StringUtils;
import com.wayne.utils.ZipUtils;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

@RestController
public class GenerateJavaController extends BaseController{

    @RequestMapping(value = "/generateJavaBean",method = RequestMethod.POST)
    public String generateJavaBean(
            @RequestBody ClassMainProperty classMainProperty
        ){

        try{
            StringBuffer src = new StringBuffer();
            String packageName = classMainProperty.getPackageName();
            if(StringUtils.isNotEmpty(packageName)){
                src.append("package "+ packageName+";\n");
            }

            String tName = classMainProperty.getClassName();
            String cName = tName.substring(0,1).toUpperCase()+tName.substring(1);

            src.append("import java.io.Serializable;\n");
            src.append("import java.util.*;\n");
            src.append("import java.math.*;\n");
            src.append("import javax.persistence.*;\n\n");

            src.append("@Entity\n");
            src.append("@Table(name = \""+tName+"\")\n");
            src.append("public class "+cName+" implements Serializable {\n");
            src.append("\tprivate static final long serialVersionUID = "+(new Date()).getTime()+"L;\n\n");

            List<ClassColumn> classColumns = classMainProperty.getClassColumns();
            if(CollectionUtils.isNotEmpty(classColumns)){
                for (ClassColumn classColumn : classColumns) {
                    String columnType = classColumn.getColumnType();
                    String columnComment = classColumn.getColumnComment();
                    String columnName = classColumn.getColumnName();
                    String getSerCName = columnName.substring(0,1).toUpperCase()+columnName.substring(1);

                    src.append("\tprivate "+ columnType +" "+ columnName +";//"+ columnComment +"\n\n");
                    //src.append("\t@Column(");
                    src.append("\tpublic "+ columnType +" get"+getSerCName+"(){\n");
                    src.append("\t\treturn "+columnName+";\n");
                    src.append("\t}\n\n");
                    src.append("\tpublic void set"+getSerCName+"("+columnType+" "+columnName+"){\n");
                    src.append("\t\tthis."+columnName+" = "+columnName+";\n");
                    src.append("\t}\n\n");
                }
            }

            src.append("}\n");

            String filePath ="G:\\tempFiles\\";
            String fileName = cName+".java";

            File f = new File(filePath+fileName);

            if(!f.exists()){
                f.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(src.toString());
            bw.close();

            String zipFileName = cName+".zip";
            FileUtils.setHeader(zipFileName,request,response);
            ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
            ZipUtils.doZip(f,out,"");

            FileUtils.downLoadFile(zipFileName,request,response);

            return "finish";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @RequestMapping("/downloadJavaFile")
    public void downloadJavaFile(){
        try{
            String filePath = "G:\\tempFiles\\";
            String fileName = "GreetingController.java";
            String zipFileName = "GreetingController.zip";

            File file = new File(filePath + fileName);

            //下载方式一，生成临时文件
            //ZipOutputStream out = new ZipOutputStream(new FileOutputStream(filePath+zipFileName));
            //ZipUtils.doZip(file,out,"");
            //FileUtils.downLoadFile(zipFileName,new FileInputStream(filePath+zipFileName),request,response);

            //下载方式二，直接写在response流中
            FileUtils.setHeader(zipFileName,request,response);
            ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
            ZipUtils.doZip(file,out,"");

            FileUtils.downLoadFile(zipFileName,request,response);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
