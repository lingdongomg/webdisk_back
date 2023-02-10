//package com.hukaichao.webdisk.repository;
//
//import com.hukaichao.webdisk.model.File;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//
//public interface FileRepository extends JpaRepository<File, Long> {
//    @Query("from File where path=?1 and isDelete=false")
//    Iterable<File> listFileByPath(String path);
//
//    @Modifying
//    @Query("update File f set f.isDelete = true where f.path = ?1 and f.fileName = ?2")
//    void deleteFile(String path, String fileName);
//
//
//}
//
