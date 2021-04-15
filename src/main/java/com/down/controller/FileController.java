package com.down.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Controller
public class FileController {
    private static  String FILEPATH = "E:\\花U盘\\";
    private static  String FILENAME = "Bak.zip";

    /**
     * 下载文件
     * @param fileId 文件ID
     * @param request
     * @param response
     * @return 成功下载文件，失败返回0
     * @throws IOException
     */
    @RequestMapping("/download")
    public int downloadFile(Integer fileId, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //得到该文件
        File file = new File(FILEPATH + FILENAME);
        if(!file.exists()){
            System.out.println("Have no such file!");
            return 0;//文件不存在就退出方法
        }

        FileInputStream fileInputStream = new FileInputStream(file);
        //设置Http响应头告诉浏览器下载这个附件,下载的文件名也是在这里设置的
        response.setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(FILENAME, "UTF-8"));
        OutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[2048];
        int len = 0;
        while ((len = fileInputStream.read(bytes))>0){
            outputStream.write(bytes,0,len);
        }
        fileInputStream.close();
        outputStream.close();

        return 0;
    }

    @RequestMapping("/down")
    public String demo(){
        return "demo";//地址指向demo.html
    }

}
