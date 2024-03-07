package com.heima.behavior.service;

import com.heima.model.behavior.dtos.UnLikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * ClassName: ApUnlikesBehaviorService
 * Package: com.heima.behavior.service
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/7 12:46
 * @Version 1.0
 */
public interface ApUnlikesBehaviorService {
    ResponseResult unLike(UnLikesBehaviorDto dto);
}
