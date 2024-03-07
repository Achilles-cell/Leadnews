package com.heima.user.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.UserRelationDto;

/**
 * ClassName: ApUserRelationService
 * Package: com.heima.user.service
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/7 13:40
 * @Version 1.0
 */
public interface ApUserRelationService {

    /**
     * 关注
     * @param dto
     * @return
     */
    ResponseResult follow(UserRelationDto dto);
}
