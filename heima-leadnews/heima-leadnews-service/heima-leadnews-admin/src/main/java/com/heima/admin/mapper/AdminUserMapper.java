package com.heima.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.admin.pojos.AdUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: AdminUserMapper
 * Package: com.heima.admin.mapper
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/2/5 13:32
 * @Version 1.0
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdUser> {
}
