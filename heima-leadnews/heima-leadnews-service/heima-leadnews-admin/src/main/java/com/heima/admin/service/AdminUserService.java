package com.heima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;

/**
 * ClassName: AdminUserService
 * Package: com.heima.admin.service
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/5 13:30
 * @Version 1.0
 */
public interface AdminUserService extends IService<AdUser> {
    ResponseResult login(AdUserDto dto);
}
