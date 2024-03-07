package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.apis.wemedia.IWemediaClient;
import com.heima.common.constants.UserConstants;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.AuthDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserRealname;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.mapper.ApUserRealnameMapper;
import com.heima.user.service.ApUserRealnameService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * ClassName: ApUserRealnameServiceImpl
 * Package: com.heima.user.service.impl
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/6 12:39
 * @Version 1.0
 */
@Service
@Transactional
@Slf4j
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {

    @Override
    public ResponseResult loadListByStatus(AuthDto dto) {
        //1、检查参数
        if(dto==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2、分页设置
        dto.checkParam();
        IPage page = new Page(dto.getPage(),dto.getSize());

        //3、根据状态查询
        LambdaQueryWrapper<ApUserRealname> queryWrapper = new LambdaQueryWrapper<>();
        if(dto.getStatus()!=null){
            queryWrapper.eq(ApUserRealname::getStatus,dto.getStatus());
        }
         page = page(page, queryWrapper);

        //4、结果返回
        PageResponseResult result = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

    @Override
    public ResponseResult updateStatus(AuthDto dto, Short status) {
        //1、检查参数
        if(dto==null||dto.getId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2、修改认证状态
        ApUserRealname apUserRealname = new ApUserRealname();
        apUserRealname.setId(dto.getId());
        apUserRealname.setStatus(status);
        if(StringUtils.isNotBlank(dto.getMsg())){
            apUserRealname.setReason(dto.getMsg());
        }
        updateById(apUserRealname);

        //3、如果审核状态是9，就是成功，需要创建自媒体账户
        if(status.equals(UserConstants.PASS_AUTH)){
            ResponseResult responseResult = createWmUserAndAuthor(dto);
            if(responseResult!=null){
                return responseResult;
            }
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }


    @Autowired
    private IWemediaClient wemediaClient;

    @Autowired
    private ApUserMapper apUserMapper;

    private ResponseResult createWmUserAndAuthor(AuthDto dto) {
        Integer id = dto.getId();
        //查询用户认证信息
        ApUserRealname apUserRealname = getById(id);
        if(apUserRealname==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

        //查询app端用户信息
        Integer userId = apUserRealname.getUserId();
        ApUser apUser = apUserMapper.selectById(userId);
        if(apUser==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

        //创建自媒体账户
        WmUser wmUser = wemediaClient.findWmUserByName(apUser.getName());
        if(wmUser == null){
            wmUser= new WmUser();
            wmUser.setApUserId(apUser.getId());
            wmUser.setCreatedTime(new Date());
            wmUser.setName(apUser.getName());
            wmUser.setPassword(apUser.getPassword());
            wmUser.setSalt(apUser.getSalt());
            wmUser.setPhone(apUser.getPhone());
            wmUser.setStatus(9);
            wemediaClient.saveWmUser(wmUser);
        }
        apUser.setFlag((short)1);
        apUserMapper.updateById(apUser);

        return null;
    }
}
