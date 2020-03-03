package com.news.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {  
    /**  
     * 文件上传功能  
     * @param file  
     * @return  
     * @throws IOException   
     */  

    public static String upload(MultipartFile file,HttpServletRequest request){  
        String path = request.getSession().getServletContext().getRealPath("file");  
        System.out.println(path+"==="+file.getOriginalFilename());
        String fileName = file.getOriginalFilename();  
        File dir = new File(path,fileName);          
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
        //MultipartFile自带的解析方法  
        try {
			file.transferTo(dir);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return "ok!";  
    }  
      
    /**  
     * 文件下载功能  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    public static void down(HttpServletRequest request,HttpServletResponse response,String downpath,String filenames) throws Exception{  
        //模拟文件，myfile.txt为需要下载的文件  
    	response.reset();
        String fileName = request.getSession().getServletContext().getRealPath(downpath)+"/"+filenames;  
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
        //假如以中文名下载的话  
        String filename = "下载文件."+fileName.substring(fileName.lastIndexOf(".") + 1);  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        ServletOutputStream outputStream = response.getOutputStream();
        BufferedOutputStream out = new BufferedOutputStream(outputStream); 
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");
        
        int len = 0;         
        while((len = bis.read()) != -1){  
            out.write(len);  
             
        }  
        out.flush(); 
        out.close();  
    }  
}