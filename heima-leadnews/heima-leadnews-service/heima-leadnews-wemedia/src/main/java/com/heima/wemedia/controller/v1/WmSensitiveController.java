package com.heima.wemedia.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.SensitiveDto;
import com.heima.model.wemedia.pojos.WmSensitive;
import com.heima.wemedia.service.WmSensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: WmSensitiveController
 * Package: com.heima.wemedia.controller.v1
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/5 14:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/sensitive")
public class WmSensitiveController {

    @Autowired
    private WmSensitiveService wmSensitiveService;

    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id){
        return wmSensitiveService.delete(id);
    }

    @PostMapping("/list")
    public ResponseResult list(@RequestBody SensitiveDto dto){
        return wmSensitiveService.pagelist(dto);
    }

    @PostMapping("/save")
    public ResponseResult save(@RequestBody WmSensitive wmSensitive){
        return wmSensitiveService.savedata(wmSensitive);
    }


    @PostMapping("/update")
    public ResponseResult updatedata(@RequestBody WmSensitive wmSensitive){
        return wmSensitiveService.uodatedata(wmSensitive);
    }
}
