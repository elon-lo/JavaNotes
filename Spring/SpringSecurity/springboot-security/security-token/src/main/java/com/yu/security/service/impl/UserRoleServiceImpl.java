package com.yu.security.service.impl;

import com.yu.security.domain.entity.UserRole;
import com.yu.security.mapper.UserRoleMapper;
import com.yu.security.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author elonlo
 * @since 2023-07-09
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
