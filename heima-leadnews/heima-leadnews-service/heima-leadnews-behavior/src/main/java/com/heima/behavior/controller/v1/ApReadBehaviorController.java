package com.heima.behavior.controller.v1;

import com.heima.behavior.service.ApReadBehaviorService;
import com.heima.model.behavior.dtos.ReadBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ApReadBehaviorController
 * Package: com.heima.behavior.controller.v1
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/7 12:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/read_behavior")
public class ApReadBehaviorController {

    @Autowired
    private ApReadBehaviorService apReadBehaviorService;

    @PostMapping
    public ResponseResult read(@RequestBody ReadBehaviorDto dto){
        return apReadBehaviorService.read(dto);
    }
}
