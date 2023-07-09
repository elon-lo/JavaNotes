package com.yu.security.mapper;

import com.yu.security.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author elonlo
 * @since 2023-07-09
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询权限
     *
     * @param userId 用户id
     * @return {@link List}<{@link String}>
     */
    List<String> selectPermsByUserId(@Param("userId") Long userId);
}
