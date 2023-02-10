package com.hukaichao.webdisk.controller;

import com.alibaba.fastjson.JSONObject;
import com.hukaichao.webdisk.model.File;
import com.hukaichao.webdisk.service.HadoopService;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileController {

    @Autowired
    private HadoopService hadoopService;

    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {
        String fileName = file.getOriginalFilename();
        try {
            hadoopService.putFile(path, fileName, file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传成功";
    }

    @RequestMapping("/downloadFile")
    public String downloadFile(HttpServletResponse response, @RequestParam("path") String path, @RequestParam("fileName") String fileName) {
        FSDataInputStream file = hadoopService.getFile(path, fileName);
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/octet-stream");
            byte[] buffer = new byte[2048];
            int len;
            while ((len = file.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, len);
            }
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "下载成功";
    }

    @RequestMapping("/deleteFile")
    public String deleteFile(@RequestParam("path") String path,@RequestParam("fileName") String fileName) {
        hadoopService.deleteFile(path,fileName);
        return "删除成功";
    }

    @RequestMapping("/listFile")
    public String listFile(@RequestParam("path") String path) {
        FileStatus[] fileStatuses = hadoopService.listFile(path);
        List<File> files = new ArrayList<>();
        for (FileStatus fileStatus : fileStatuses) {
            String fileName = fileStatus.getPath().getName();
            String filePath = fileStatus.getPath().toString();
            String fileType = fileStatus.isDirectory() ? "文件夹" : "文件";
            String fileSize = "";
            if (fileStatus.getLen() > 1024 * 1024) {
                fileSize = String.format("%.2f", fileStatus.getLen() / 1024.0 / 1024.0) + " MB";
            } else if (fileStatus.getLen() > 1024) {
                fileSize = String.format("%.2f", fileStatus.getLen() / 1024.0) + " KB";
            } else {
                fileSize = fileStatus.getLen() + " B";
            }
            long modificationTime = fileStatus.getModificationTime();
            String uploadTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(modificationTime));
            File file = new File(filePath, fileName, fileType, fileSize, uploadTime);
            files.add(file);
        }
        return JSONObject.toJSONString(files);
    }

    @RequestMapping("/newFolder")
    public String newFolder(@RequestParam("path") String path,@RequestParam("folderName") String folderName){
        hadoopService.mkdirFolder(path,folderName);
        return "新建文件夹成功";
    }

    @RequestMapping("/searchFile")
    public String searchFile(@RequestParam("path") String path,@RequestParam("fileName") String fileName){
        FileStatus[] fileStatuses = hadoopService.findFile(fileName, path);
        List<File> files = new ArrayList<>();
        for (FileStatus fileStatus : fileStatuses) {
            String fileName1 = fileStatus.getPath().getName();
            String filePath = fileStatus.getPath().toString();
            String fileType = fileStatus.isDirectory() ? "文件夹" : "文件";
            String fileSize = "";
            if (fileStatus.getLen() > 1024 * 1024) {
                fileSize = String.format("%.2f", fileStatus.getLen() / 1024.0 / 1024.0) + " MB";
            } else if (fileStatus.getLen() > 1024) {
                fileSize = String.format("%.2f", fileStatus.getLen() / 1024.0) + " KB";
            } else {
                fileSize = fileStatus.getLen() + " B";
            }
            long modificationTime = fileStatus.getModificationTime();
            String uploadTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(modificationTime));
            File file = new File(filePath, fileName1, fileType, fileSize, uploadTime);
            files.add(file);
        }
        return JSONObject.toJSONString(files);
    }

}
