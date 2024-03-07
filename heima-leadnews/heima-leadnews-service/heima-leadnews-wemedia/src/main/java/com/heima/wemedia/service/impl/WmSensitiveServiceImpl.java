package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dtos.SensitiveDto;
import com.heima.model.wemedia.pojos.WmSensitive;
import com.heima.wemedia.mapper.WmSensitiveMapper;
import com.heima.wemedia.service.WmSensitiveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ClassName: WmSensitiveServiceImpl
 * Package: com.heima.wemedia.service.impl
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/5 14:56
 * @Version 1.0
 */
@Service
public class WmSensitiveServiceImpl extends ServiceImpl<WmSensitiveMapper, WmSensitive> implements WmSensitiveService {

    @Autowired
    private WmSensitiveMapper wmSensitiveMapper;

    @Override
    public ResponseResult delete(Integer id) {
        // 1、检查参数
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        // 2、查询敏感词
        if(getById(id) == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

        // 3、删除
        removeById(id);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult pagelist(SensitiveDto dto) {
        // 1、 检查参数
        if(dto==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2、分页设置
        dto.checkParam();
        IPage page = new Page(dto.getPage(), dto.getSize());

        //3、查询
        LambdaQueryWrapper<WmSensitive> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(dto.getName())){
            lambdaQueryWrapper.like(WmSensitive::getSensitives,dto.getName());
        }
        page = page(page, lambdaQueryWrapper);

        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult savedata(WmSensitive wmSensitive) {
        // 1、检查参数
        if(wmSensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        WmSensitive wmSensitive1 = getOne(Wrappers.<WmSensitive>lambdaQuery().eq(WmSensitive::getSensitives, wmSensitive.getSensitives()));
        if(wmSensitive1 != null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"敏感词已存在");
        }

        //2、保存数据
        wmSensitive.setCreatedTime(new Date());
        save(wmSensitive);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult uodatedata(WmSensitive wmSensitive) {
        // 1、检查参数
        if(wmSensitive==null||wmSensitive.getId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2、修改
        updateById(wmSensitive);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
