package com.heima.user.service.impl;

import com.heima.common.constants.BehaviorConstants;
import com.heima.common.redis.CacheService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.UserRelationDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.service.ApUserRelationService;
import com.heima.utils.thread.AppThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ApUserRelationServiceImpl
 * Package: com.heima.user.service.impl
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/7 13:41
 * @Version 1.0
 */
@Service
public class ApUserRelationServiceImpl implements ApUserRelationService {

    @Autowired
    private CacheService cacheService;

    @Override
    public ResponseResult follow(UserRelationDto dto) {
        //1、检查参数
        if(dto==null||dto.getArticleId()==null||dto.getOperation()<0||dto.getOperation()>1){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2、是否登录
        ApUser user = AppThreadLocalUtil.getUser();
        if(user==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        //3、关注
        if(dto.getOperation()==0){
            // 将对方写入我的关注中
            cacheService.zAdd(BehaviorConstants.APUSER_FOLLOW_RELATION+user.getId().toString(),dto.getArticleId().toString(),System.currentTimeMillis());
            // 将我写入对方的粉丝中
            cacheService.zAdd(BehaviorConstants.APUSER_FANS_RELATION+dto.getArticleId().toString(),user.getId().toString(),System.currentTimeMillis());
        }else {
            // 取消关注
            cacheService.zRemove(BehaviorConstants.APUSER_FOLLOW_RELATION+user.getId().toString(),dto.getArticleId().toString());
            cacheService.zRemove(BehaviorConstants.APUSER_FANS_RELATION+dto.getArticleId().toString(),user.getId().toString());
        }


        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
