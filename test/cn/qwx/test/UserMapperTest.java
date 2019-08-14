package cn.qwx.test;

import cn.qwx.dao.user.UserMapper;
import cn.qwx.pojo.User;
import cn.qwx.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {
    private  static Logger logger=Logger.getLogger(UserMapperTest.class);

    @Test
    //示例4
    public void testGetUserList(){
        SqlSession sqlSession=null;
        List<User> list=new ArrayList<User>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            User  user=new User();
            user.setUserName("赵");
            user.setUserRole(3);
            list=sqlSession.getMapper(UserMapper.class).getUserList(user);
        }catch (Exception e){
           e.printStackTrace();
        }finally {
           MyBatisUtil.closeSession(sqlSession);
        }
        for (User user:list){
            logger.debug("testGetUserList userCode:"+user.getUserCode()+"and userName:"+user.getUserName());
        }
    }

    @Test
    //示例6
    public void testGetUserList1(){
        SqlSession sqlSession=null;
        List<User> list=new ArrayList<User>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            Map<String, String> userMap=new HashMap<String, String>();
            userMap.put("uName","赵");
            userMap.put("uRole","3");
            list=sqlSession.getMapper(UserMapper.class).getUserListByMap(userMap);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (User user:list){
            logger.debug("testGetUserList userCode:"+user.getUserCode()+"and userName:"+user.getUserName());
        }
    }
}
