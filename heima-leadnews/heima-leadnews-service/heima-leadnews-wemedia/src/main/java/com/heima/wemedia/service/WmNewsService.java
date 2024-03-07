package com.heima.wemedia.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.NewsAuthDto;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;

public interface WmNewsService extends IService<WmNews> {

    /**
     * 查询文章
     * @param dto
     * @return
     */
    ResponseResult findAll(WmNewsPageReqDto dto);

    /**
     *  发布文章或保存草稿
     * @param dto
     * @return
     */
    ResponseResult submitNews(WmNewsDto dto);


    /**
     * 文章的上下架
     * @param dto
     * @return
     */
    ResponseResult downOrUp(WmNewsDto dto);

    ResponseResult findList(NewsAuthDto dto);

    ResponseResult findWmNewsVo(Integer id);

    ResponseResult updateStatus(Short status, NewsAuthDto dto);

    ResponseResult findOne(Integer id);
}