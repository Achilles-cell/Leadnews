package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.AuthDto;
import com.heima.model.user.pojos.ApUserRealname;

/**
 * ClassName: ApUserRealnameService
 * Package: com.heima.user.service
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/6 12:38
 * @Version 1.0
 */
public interface ApUserRealnameService extends IService<ApUserRealname> {
    ResponseResult loadListByStatus(AuthDto dto);

    ResponseResult updateStatus(AuthDto dto, Short passAuth);
}
