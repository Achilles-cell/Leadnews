package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdminUserMapper;
import com.heima.admin.service.AdminUserService;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;

/**
 * ClassName: AdminUserServiceImpl
 * Package: com.heima.admin.service.impl
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/5 13:30
 * @Version 1.0
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdUser> implements AdminUserService {


    @Override
    public ResponseResult login(AdUserDto dto) {
        // 1、检查参数
        if(StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"用户名或密码为空");
        }

        // 2、查询用户
        AdUser adUser = getOne(Wrappers.<AdUser>lambdaQuery().eq(AdUser::getName, dto.getName()));
        if(adUser == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"数据不存在");
        }

        // 3、对比密码
        String salt = adUser.getSalt();
        String password = dto.getPassword();
        password = DigestUtils.md5DigestAsHex((password + salt).getBytes());

        if(password.equals(adUser.getPassword())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(adUser.getId().longValue()));
            adUser.setSalt("");
            adUser.setPassword("");
            map.put("user", adUser);
            return ResponseResult.okResult(map);
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
    }
}
