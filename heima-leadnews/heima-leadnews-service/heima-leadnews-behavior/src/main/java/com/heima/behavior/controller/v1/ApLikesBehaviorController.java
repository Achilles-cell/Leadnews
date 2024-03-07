package com.heima.behavior.controller.v1;

import com.heima.behavior.service.ApLikesBehaviorService;
import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ApLikesBehaviorController
 * Package: com.heima.behavior.controller.v1
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/7 12:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/likes_behavior")
public class ApLikesBehaviorController {

    @Autowired
    private ApLikesBehaviorService apLikesBehaviorService;

    @PostMapping
    public ResponseResult like(@RequestBody LikesBehaviorDto dto){
        return apLikesBehaviorService.like(dto);
    }

}
