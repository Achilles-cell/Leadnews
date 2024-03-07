package com.heima.admin.controller.v1;

import com.heima.admin.service.AdminUserService;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: LoginController
 * Package: com.heima.admin.controller.v1
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/5 13:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/in")
    public ResponseResult login(@RequestBody AdUserDto dto){
        return adminUserService.login(dto);
    }
}
