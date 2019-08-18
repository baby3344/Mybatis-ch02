package cn.qwx.test;

import cn.qwx.dao.role.RoleMapper;
import cn.qwx.pojo.Role;
import cn.qwx.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleMapperTest {
    public  static Logger logger=Logger.getLogger(RoleMapperTest.class);

    @Test  //使用if+set 根据角色id修改角色信息
    public void testUpdateById(){
        SqlSession sqlSession=null;
        Role role=new Role();
        role.setId(4);
        role.setRoleName("普通员工");
        role.setModifyBy(1);
        role.setModifyDate(new Date());
        int count=0;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            count=sqlSession.getMapper(RoleMapper.class).UpdateById(role);
            sqlSession.commit();
        }catch (Exception e){
             e.printStackTrace();
             sqlSession.rollback();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("count=====>"+count);
    }

    @Test  //使用if+trim实现根据角色名称模糊查询角色信息，并分页展示
    public void testgetRolelikeByName(){
        SqlSession sqlSession=null;
        List<Role> roleList=new ArrayList<Role>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            roleList=sqlSession.getMapper(RoleMapper.class).getRolelikeByName("",2,2);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (Role role:roleList) {
            logger.debug("test====>id:"+role.getId()+"名称："+role.getRoleName()+"编号："+role.getRoleCode());
        }

    }
}
