package com.yu.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yu.mybatis.mapper.EmpMapper;
import com.yu.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author elonlo
 * @date 2022/8/28 12:54
 */
public class PageHelperTest {

	private static final Logger Logger = LoggerFactory.getLogger(PageHelperTest.class);

	@Test
	public void testPageHelper() {
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
			EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

			PageHelper.startPage(1,4);
			List<Emp> empList = mapper.selectByExample(null);
			PageInfo<Emp> pageInfo = new PageInfo<>(empList,5);
			Logger.info("当前页面第一个元素在数据库中的行号: {}",pageInfo.getStartRow());
			Logger.info("当前页面最后一个元素在数据库中的行号: {}",pageInfo.getEndRow());
			Logger.info("前一页: {}",pageInfo.getPrePage());
			Logger.info("下一页: {}",pageInfo.getNextPage());
			Logger.info("当前页: {}",pageInfo.getPageNum());
			Logger.info("每页的数量: {}",pageInfo.getPageSize());
			Logger.info("当前页的数量: {}",pageInfo.getSize());
			Logger.info("总页数: {}",pageInfo.getPages());
			Logger.info("是否为第一页: {}",pageInfo.isIsFirstPage());
			Logger.info("是否为最后一页: {}",pageInfo.isIsLastPage());
			Logger.info("是否有前一页: {}",pageInfo.isHasPreviousPage());
			Logger.info("是否有下一页: {}",pageInfo.isHasNextPage());
			Logger.info("总记录数: {}",pageInfo.getTotal());
			Logger.info("导航页码数: {}",pageInfo.getNavigatePages());
			Logger.info("所有导航页号: {}",pageInfo.getNavigatepageNums());
			Logger.info("导航条上的第一页: {}",pageInfo.getNavigateFirstPage());
			Logger.info("导航条上的最后一页: {}",pageInfo.getNavigateLastPage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
