package com.yu.security.mapper;

import com.yu.security.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author elonlo
 * @since 2023-07-08
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
