package com.heima.admin.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: AdminGatewayApplication
 * Package: com.heima.admin.gateway
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/5 12:56
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
public class AdminGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminGatewayApplication.class,args);
    }
}
