//package com.hukaichao.webdisk.service.impl;
//
//import com.hukaichao.webdisk.model.File;
//import com.hukaichao.webdisk.repository.FileRepository;
//import com.hukaichao.webdisk.service.FileService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Date;
//
//public class FileServicveImpl implements FileService {
//
//    @Autowired
//    private FileRepository fileRepository;
//
//    @Override
//    public void uploadFile(File file) {
//        fileRepository.save(file);
//    }
//
//    @Override
//    public void downloadFile(String path, String fileName) {
//    }
//
//    @Override
//    public void deleteFile(String path, String fileName) {
//        fileRepository.deleteFile(path,fileName);
//
//    }
//
//    @Override
//    public Iterable<File> listFile(String path) {
//        return fileRepository.listFileByPath(path);
//    }
//
//    @Override
//    public void newFolder(String path, String folderName) {
//        File file = new File(path,folderName,"folder",0,new Date());
//        fileRepository.save(file);
//    }
//}
//
