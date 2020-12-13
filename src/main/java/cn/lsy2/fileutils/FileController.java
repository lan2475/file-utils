package cn.lsy2.fileutils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName FileController
 * @Author Fly Man
 * @Date 2020/12/13 23:01
 */
@RequestMapping("file")
@Controller
public class FileController {


    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

        printParam(request);

        String filePath = "static/6.png";
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        InputStream myStream = classPathResource.getInputStream();
        File downloadFile = classPathResource.getFile();
        ServletContext context = request.getServletContext();

        // get MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
            System.out.println("context getMimeType is null");
        }
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        try {
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @RequestMapping("preview")
    public void preview(HttpServletRequest request, HttpServletResponse response) throws IOException {

        printParam(request);

        String filePath = "static/6.png";
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        InputStream myStream = classPathResource.getInputStream();
        File downloadFile = classPathResource.getFile();
        ServletContext context = request.getServletContext();

        // get MIME type of the file
        String mimeType = context.getMimeType(filePath);
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        try {
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printParam(HttpServletRequest request){
        request.getParameterMap().forEach((name,value)->{
            System.out.println(String.format("参数打印:%s:%s",name,String.join(",",value)));
        });
    }
}
