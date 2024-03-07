package com.heima.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: AdminApplication
 * Package: com.heima.admin
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/5 13:04
 * @Version 1.0
 */


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.heima.admin.mapper")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
