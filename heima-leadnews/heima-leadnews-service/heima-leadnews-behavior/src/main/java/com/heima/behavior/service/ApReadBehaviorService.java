package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ReadBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * ClassName: ApReadBehaviorService
 * Package: com.heima.behavior.service
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/7 12:46
 * @Version 1.0
 */
public interface ApReadBehaviorService {
    ResponseResult read(ReadBehaviorDto dto);
}
