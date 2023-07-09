package com.yu.security.service.impl;

import com.yu.security.domain.entity.Menu;
import com.yu.security.mapper.MenuMapper;
import com.yu.security.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author elonlo
 * @since 2023-07-09
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
