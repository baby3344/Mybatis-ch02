package cn.qwx.test;

import cn.qwx.dao.user.UserMapper;
import cn.qwx.pojo.Address;
import cn.qwx.pojo.User;
import cn.qwx.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Test
    //示例10
    public void testGetUserList10(){
        SqlSession sqlSession=null;
        List<User> list=new ArrayList<User>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            User user=new User();
            user.setUserName("赵");
            user.setUserRole(3);
            list=sqlSession.getMapper(UserMapper.class).getUserList1(user);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (User user:list){
            logger.debug("testGetUserList userCode:"+user.getUserCode()+"and userName:"+user.getUserName()+
                         "and userRoleName："+user.getUserRoleName()+
                         "and address："+user.getAddress());
        }
    }

    @Test
    //增加
    public void testAdd(){
        SqlSession sqlSession=null;
        int count=0;
        try{
            sqlSession=MyBatisUtil.createSqlSession();
            User user=new User();
            user.setUserCode("test001");
            user.setUserName("测试用户001");
            user.setUserPassword("1234567");
            Date birthday=new SimpleDateFormat("yyyy-MM-dd").parse("1984-12-12");
            user.setBirthday(birthday);
            user.setAddress("地址测试");
            user.setGender(1);
            user.setPhone("13688783697");
            user.setUserRole(1);
            user.setCreatedBy(1);
            user.setCreationDate(new Date());
            count=sqlSession.getMapper(UserMapper.class).add(user);
            //模拟回滚
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            count=0;
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("testAdd count:"+count);
    }

    @Test
    //修改
    public void testModify(){
        logger.debug("testModify:===============");
        SqlSession sqlSession=null;
        int count=0;
        try{
            User user=new User();
            user.setId(16);
            user.setUserCode("testModify");
            user.setUserName("测试用户修改");
            user.setUserPassword("0000000");
            Date birthday=new SimpleDateFormat("yyyy-MM-dd").parse("1980-10-10");
            user.setBirthday(birthday);
            user.setAddress("地址测试修改");
            user.setGender(2);
            user.setPhone("13600002222");
            user.setUserRole(2);
            user.setCreatedBy(1);
            user.setCreationDate(new Date());
            sqlSession=MyBatisUtil.createSqlSession();
            count=sqlSession.getMapper(UserMapper.class).modify(user);
            //模拟回滚
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            count=0;
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("testModify count:"+count);
    }

    @Test
    //修改个人密码
    public void testUpdatePwd(){
        logger.debug("testUpdate!==============");
        SqlSession sqlSession=null;
        String pwd="6666666";
        Integer id=1;
        int count=0;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            count=sqlSession.getMapper(UserMapper.class).updatePwd(id,pwd);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            count=0;
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("testUpdatePwd count:"+count);
    }

    @Test
    //删除用户信息
    public void testDeleteUserId(){
       logger.debug("testDeleteUserId!==========");
       SqlSession sqlSession=null;
       Integer delId=16;
       int count=0;
       try {
           sqlSession=MyBatisUtil.createSqlSession();
           count=sqlSession.getMapper(UserMapper.class).deleteUserById(delId);
           sqlSession.commit();
       }catch (Exception e){
           e.printStackTrace();
           sqlSession.rollback();
           count=0;
       }finally {
           MyBatisUtil.closeSession(sqlSession);
       }
       logger.debug("testDeleteUserId count:"+count);
    }

    @Test
    //示例21
    public void getUserListByRoleIdTest(){
        SqlSession sqlSession=null;
        List<User> userList=new ArrayList<User>();
        Integer roleId=3;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            userList=sqlSession.getMapper(UserMapper.class).getUserListByRoleId(roleId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("getUserListByRoleIdTest userList.size:"+userList.size());
        for (User user:userList) {
            logger.debug("userList====>userName:"+user.getUserName()+
            ",Role:"+user.getRole()+
                    "---"+user.getRole().getRoleName());
        }
    }

    @Test
    //示例25
    public void getAddressListByUserIdTest(){
         SqlSession sqlSession=null;
         List<User> userList=new ArrayList<User>();
         Integer userId=1;
         try {
             sqlSession=MyBatisUtil.createSqlSession();
             userList=sqlSession.getMapper(UserMapper.class).getAddressListByUserId(userId);
         }catch (Exception e){
            e.printStackTrace();
         }finally {
            MyBatisUtil.closeSession(sqlSession);
         }
        for (User user:userList) {
            logger.debug("userList(include)===>userCode:"+user.getUserCode()+
                    ",userName:"+user.getUserName());
            for (Address address:user.getAddressList()) {
                logger.debug("address--->id:"+address.getId()+
                        ",contact:"+address.getContact()+
                        ",addressDesc:"+address.getAddressDesc()+
                        ",tel:"+address.getTel()+
                        ",postCode:"+address.getPostCode());
            }
        }
    }

    @Test
    //示例27
    public void getAddressListByUserIdTest1(){
     SqlSession sqlSession=null;
     User user=null;
     Integer userId=1;
     try {
         sqlSession=MyBatisUtil.createSqlSession();
         sqlSession.getMapper(UserMapper.class).getAddressListByUserId(userId);

     }catch (Exception e){
      e.printStackTrace();
     }finally {
          MyBatisUtil.closeSession(sqlSession);
     }
     if(sqlSession.getMapper(UserMapper.class).getAddressListByUserId(userId)!=null){
         logger.debug("userList(include:addresslist)===>userCode:"+
         user.getUserCode()+",userName:"+user.getUserName()+
                 ",<未做映射字段>userPassword:"+user.getUserPassword());
     }
    }
}
