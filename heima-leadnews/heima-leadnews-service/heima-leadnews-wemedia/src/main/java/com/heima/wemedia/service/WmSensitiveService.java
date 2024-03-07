package com.heima.wemedia.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.SensitiveDto;
import com.heima.model.wemedia.pojos.WmSensitive;

/**
 * ClassName: WmSensitiveService
 * Package: com.heima.wemedia.service
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/5 14:54
 * @Version 1.0
 */
public interface WmSensitiveService extends IService<WmSensitive> {
    ResponseResult delete(Integer id);

    ResponseResult pagelist(SensitiveDto dto);


    ResponseResult savedata(WmSensitive wmSensitive);

    ResponseResult uodatedata(WmSensitive wmSensitive);
}
