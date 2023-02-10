package com.hukaichao.webdisk;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@SpringBootTest
class WebdiskApplicationTests {

    @Test
    public void mkdir() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://mycluster");
        configuration.set("dfs.nameservices", "mycluster");
        configuration.set("dfs.ha.namenodes.mycluster", "nn1,nn2,nn3");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn1", "master:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn2", "slave1:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn3", "slave2:8020");
        configuration.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        DistributedFileSystem dfs = new DistributedFileSystem();
        dfs.initialize(URI.create("hdfs://mycluster:8020"), configuration);

        dfs.mkdirs(new Path("/test4"));
    }

    @Test
    public void put() throws IOException{
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://mycluster");
        configuration.set("dfs.nameservices", "mycluster");
        configuration.set("dfs.ha.namenodes.mycluster", "nn1,nn2,nn3");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn1", "master:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn2", "slave1:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn3", "slave2:8020");
        configuration.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        DistributedFileSystem dfs = new DistributedFileSystem();
        dfs.initialize(URI.create("hdfs://mycluster:8020"), configuration);

        InputStream inputStream = new FileInputStream("C:\\Users\\28184\\Desktop\\p52.txt");
        FSDataOutputStream outputStream = dfs.create(new Path("/hukaichao" + "/" + "test2.txt"));
        IOUtils.copyBytes(inputStream, outputStream, 4096, true);
    }

//    @Test
//    public void get() throws IOException{
//        Configuration configuration = new Configuration();
//        configuration.set("fs.defaultFS", "hdfs://mycluster");
//        configuration.set("dfs.nameservices", "mycluster");
//        configuration.set("dfs.ha.namenodes.mycluster", "nn1,nn2,nn3");
//        configuration.set("dfs.namenode.rpc-address.mycluster.nn1", "master:8020");
//        configuration.set("dfs.namenode.rpc-address.mycluster.nn2", "slave1:8020");
//        configuration.set("dfs.namenode.rpc-address.mycluster.nn3", "slave2:8020");
//        configuration.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
//        DistributedFileSystem dfs = new DistributedFileSystem();
//        dfs.initialize(URI.create("hdfs://mycluster:8020"), configuration);
//
//        InputStream inputStream = dfs.open(new Path("/hukaichao" + "/" + "test2.txt"));
//        IOUtils.copyBytes(inputStream, System.out, 4096, true);
//        dfs.close();
//    }

    @Test
    public void delete() throws IOException{
            Configuration configuration = new Configuration();
            configuration.set("fs.defaultFS", "hdfs://mycluster");
            configuration.set("dfs.nameservices", "mycluster");
            configuration.set("dfs.ha.namenodes.mycluster", "nn1,nn2,nn3");
            configuration.set("dfs.namenode.rpc-address.mycluster.nn1", "master:8020");
            configuration.set("dfs.namenode.rpc-address.mycluster.nn2", "slave1:8020");
            configuration.set("dfs.namenode.rpc-address.mycluster.nn3", "slave2:8020");
            configuration.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
            DistributedFileSystem dfs = new DistributedFileSystem();
            dfs.initialize(URI.create("hdfs://mycluster:8020"), configuration);

            dfs.delete(new Path("/test6"), true);
        }

    @Test
    public void rename() throws IOException{
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://mycluster");
        configuration.set("dfs.nameservices", "mycluster");
        configuration.set("dfs.ha.namenodes.mycluster", "nn1,nn2,nn3");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn1", "master:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn2", "slave1:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn3", "slave2:8020");
        configuration.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        DistributedFileSystem dfs = new DistributedFileSystem();
        dfs.initialize(URI.create("hdfs://mycluster:8020"), configuration);

        dfs.rename(new Path("/hukaichao" + "/" + "test1.txt"), new Path("/hukaichao" + "/" + "test3.txt"));
    }

    @Test
    public void list() throws IOException{
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://mycluster");
        configuration.set("dfs.nameservices", "mycluster");
        configuration.set("dfs.ha.namenodes.mycluster", "nn1,nn2,nn3");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn1", "master:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn2", "slave1:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn3", "slave2:8020");
        configuration.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        DistributedFileSystem dfs = new DistributedFileSystem();
        dfs.initialize(URI.create("hdfs://mycluster:8020"), configuration);

        FileStatus[] fileStatuses = dfs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus.getPath().getName()+"\t"+fileStatus.getLen());
        }
    }

    @Test
    public void move() throws IOException{
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://mycluster");
        configuration.set("dfs.nameservices", "mycluster");
        configuration.set("dfs.ha.namenodes.mycluster", "nn1,nn2,nn3");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn1", "master:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn2", "slave1:8020");
        configuration.set("dfs.namenode.rpc-address.mycluster.nn3", "slave2:8020");
        configuration.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        DistributedFileSystem dfs = new DistributedFileSystem();
        dfs.initialize(URI.create("hdfs://mycluster:8020"), configuration);

        dfs.rename(new Path("/hukaichao" + "/" + "test3.txt"), new Path("/test6" + "/" + "test3.txt"));
    }

}
