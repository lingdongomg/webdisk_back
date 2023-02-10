package com.hukaichao.webdisk.service;


import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;

import java.io.InputStream;

public interface HadoopService {
    FileSystem getFileSystem();
    void putFile(String path, String fileName, InputStream inputStream);
    FSDataInputStream getFile(String path, String fileName);
    void deleteFile(String path,String fileName);
    void mkdirFolder(String path, String folderName);
    void renameFile(String path, String oldName, String newName);
    FileStatus[] listFile(String path);
    void moveFile(String path, String fileName, String newPath);
    FileStatus[] findFile(String fileName, String path);
}
