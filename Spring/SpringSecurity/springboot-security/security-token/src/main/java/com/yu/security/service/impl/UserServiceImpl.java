package com.yu.security.service.impl;

import com.yu.security.domain.entity.User;
import com.yu.security.mapper.UserMapper;
import com.yu.security.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author elonlo
 * @since 2023-07-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
