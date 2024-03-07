package com.heima.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.heima.article.service.ApCollectionService;
import com.heima.common.constants.BehaviorConstants;
import com.heima.common.redis.CacheService;
import com.heima.model.article.dtos.CollectionBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.thread.AppThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ApCollectionServiceImpl
 * Package: com.heima.article.service.impl
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/7 13:58
 * @Version 1.0
 */
@Service
@Slf4j
public class ApCollectionServiceImpl implements ApCollectionService {

    @Autowired
    private CacheService cacheService;

    @Override
    public ResponseResult collection(CollectionBehaviorDto dto) {
        //1、检查参数
        if (dto==null||dto.getEntryId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2、是否登录
        ApUser user = AppThreadLocalUtil.getUser();
        if(user==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        //3、收藏
        String collectionJson = (String) cacheService.hGet(BehaviorConstants.COLLECTION_BEHAVIOR + user.getId().toString(), dto.getEntryId().toString());
        if(StringUtils.isNotBlank(collectionJson)&&dto.getOperation()==0){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"已收藏");
        }

        if(dto.getOperation()==0){
            log.info("文章收藏，保存key:{},{},{}",dto.getEntryId(),user.getId().toString(), JSON.toJSONString(dto));
            cacheService.hPut(BehaviorConstants.COLLECTION_BEHAVIOR+user.getId().toString(),dto.getEntryId().toString(),JSON.toJSONString(dto));
        }else {
            log.info("文章收藏，删除key:{},{},{}",dto.getEntryId(),user.getId().toString(), JSON.toJSONString(dto));
            cacheService.hDelete(BehaviorConstants.COLLECTION_BEHAVIOR+user.getId().toString(),dto.getEntryId().toString());
        }

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
