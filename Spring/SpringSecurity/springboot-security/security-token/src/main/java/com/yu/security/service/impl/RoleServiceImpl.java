package com.yu.security.service.impl;

import com.yu.security.domain.entity.Role;
import com.yu.security.mapper.RoleMapper;
import com.yu.security.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author elonlo
 * @since 2023-07-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
