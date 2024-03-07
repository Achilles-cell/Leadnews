package com.heima.minio.test;

import com.heima.file.service.FileStorageService;
import com.heima.minio.MinIOApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest(classes = MinIOApplication.class)
@RunWith(SpringRunner.class)
public class MinIOTest {



    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void testUpdateImgFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\shizhansystem\\heima-leadnews\\heima-leadnews-test\\freemarker-demo\\src\\main\\resources\\templates\\list.html");
            String filePath = fileStorageService.uploadHtmlFile("", "list1.html", fileInputStream);
            System.out.println(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//
//        FileInputStream fileInputStream = null;
//        try {
//
//            fileInputStream =  new FileInputStream("D:\\shizhansystem\\heima-leadnews\\heima-leadnews-test\\freemarker-demo\\src\\main\\resources\\templates\\list.html");;
//
//            //1.创建minio链接客户端
//            MinioClient minioClient = MinioClient.builder().credentials("minioadmin", "minioadmin").endpoint("http://localhost:9000").build();
//            //2.上传
//            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
//                    .object("list.html")//文件名
//                    .contentType("text/html")//文件类型
//                    .bucket("leadnews")//桶名词  与minio创建的名词一致
//                    .stream(fileInputStream, fileInputStream.available(), -1) //文件流
//                    .build();
//            minioClient.putObject(putObjectArgs);
//
//            System.out.println("http://localhost:9000/leadnews/list.html");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

}