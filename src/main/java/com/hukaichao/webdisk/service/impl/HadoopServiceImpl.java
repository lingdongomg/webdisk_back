package com.hukaichao.webdisk.service.impl;

import com.hukaichao.webdisk.service.HadoopService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@Service
public class HadoopServiceImpl implements HadoopService {
    @Override
    public DistributedFileSystem getFileSystem() {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://mycluster");
        configuration.set("dfs.nameservices", "mycluster");
        configuration.set("dfs.ha.namenodes.mycluster", "nn1,nn2,nn3");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn1", "master:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn2", "slave1:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn3", "slave2:8020");
        configuration.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        DistributedFileSystem dfs = new DistributedFileSystem();
        try {
            dfs.initialize(URI.create("hdfs://mycluster:8020"), configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dfs;
    }

    @Override
    public void putFile(String path, String fileName, InputStream inputStream) {
        DistributedFileSystem dfs = getFileSystem();
        try {
            FSDataOutputStream outputStream = dfs.create(new Path(path + "/" + fileName));
            IOUtils.copyBytes(inputStream, outputStream, 4096, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FSDataInputStream getFile(String path, String fileName) {
        DistributedFileSystem dfs = getFileSystem();
        try {
            return dfs.open(new Path(path + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteFile(String path, String fileName) {
        DistributedFileSystem dfs = getFileSystem();
        try {
            dfs.delete(new Path(path + "/" + fileName), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mkdirFolder(String path, String folderName) {
        DistributedFileSystem dfs = getFileSystem();
        try {
            dfs.mkdirs(new Path(path + "/" + folderName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renameFile(String path, String oldName, String newName) {
        DistributedFileSystem dfs = getFileSystem();
        try {
            dfs.rename(new Path(path + "/" + oldName), new Path(path + "/" + newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileStatus[] listFile(String path) {
        DistributedFileSystem dfs = getFileSystem();
        try {
            return dfs.listStatus(new Path(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void moveFile(String path, String fileName, String newPath) {
        DistributedFileSystem dfs = getFileSystem();
        try {
            dfs.rename(new Path(path + "/" + fileName), new Path(newPath + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileStatus[] findFile(String fileName, String path) {
        DistributedFileSystem dfs = getFileSystem();
        try {
            return dfs.globStatus(new Path(path + "/*" + fileName+"*"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
