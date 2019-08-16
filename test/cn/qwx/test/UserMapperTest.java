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
            ",Role:"+user.getRole().getRoleCode()+ "---"+user.getRole().getRoleName());
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
        if(user!=null){
            logger.debug("userList(include:addresslist)===>userCode:"+
                    user.getUserCode()+",userName:"+user.getUserName()+
                    ",<未做映射字段>userPassword:"+user.getUserPassword());
        }
    }

    //（）示例3
    @Test
    public void testGetUserListCh03(){
        SqlSession sqlSession=null;
        List<User> userList=new ArrayList<User>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            String userName="孙";
            Integer role=null;
            userList=sqlSession.getMapper(UserMapper.class).getUserList2(userName,role);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
             MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("userlist.size---->"+userList.size());
        for (User user:userList){
            logger.debug("testUserList2======>id:"+user.getId()+
            " and userCode:"+user.getUserCode()+
                    " and userName:"+user.getUserName()+
                    " and userRole:"+user.getRole()+"" +
                    " and userRoleName:"+user.getUserRoleName()+
                    " and phone:"+user.getPhone()+
                    " and gender:"+user.getGender());
        }
    }

    @Test
    public void testGetUserListCh03Where(){
        SqlSession sqlSession=null;
        List<User> userList=new ArrayList<User>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            String userName="";
            Integer role=null;
            userList=sqlSession.getMapper(UserMapper.class).getUserList3(userName,role);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("userlist.size---->"+userList.size());
        for (User user:userList){
            logger.debug("testUserList2======>id:"+user.getId()+
                    " and userCode:"+user.getUserCode()+
                    " and userName:"+user.getUserName()+
                    " and userRole:"+user.getRole()+"" +
                    " and userRoleName:"+user.getUserRoleName()+
                    " and phone:"+user.getPhone()+
                    " and gender:"+user.getGender());
        }
    }

    @Test
    public void testGetUserListCh03ifTrim(){
        SqlSession sqlSession=null;
        List<User> userList=new ArrayList<User>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            String userName="";
            Integer role=1;
            userList=sqlSession.getMapper(UserMapper.class).getUserList4(userName,role);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("userlist.size---->"+userList.size());
        for (User user:userList){
            logger.debug("testUserList2======>id:"+user.getId()+
                    " and userCode:"+user.getUserCode()+
                    " and userName:"+user.getUserName()+
                    " and userRole:"+user.getRole()+"" +
                    " and userRoleName:"+user.getUserRoleName()+
                    " and phone:"+user.getPhone()+
                    " and gender:"+user.getGender());
        }
    }

    @Test
    //修改 if+set
    public void testModify1(){
        logger.debug("testModify1:===============");
        SqlSession sqlSession=null;
        int count=0;
        try{
            User user=new User();
            user.setId(17);
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
            count=sqlSession.getMapper(UserMapper.class).modify1(user);
            //模拟回滚
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            count=0;
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("testModify1 count:"+count);
    }

    @Test
    //修改 if+trim
    public void testModify2(){
        logger.debug("testModify2:===============");
        SqlSession sqlSession=null;
        int count=0;
        try{
            User user=new User();
            user.setId(17);
            user.setUserCode("testModify");
            user.setUserName("测试用户修改001");
            user.setUserPassword("8888888");
            Date birthday=new SimpleDateFormat("yyyy-MM-dd").parse("1980-10-10");
            user.setBirthday(birthday);
            user.setAddress("地址测试修改");
            user.setGender(2);
            user.setPhone("13600002222");
            user.setUserRole(2);
            user.setCreatedBy(1);
            user.setCreationDate(new Date());
            sqlSession=MyBatisUtil.createSqlSession();
            count=sqlSession.getMapper(UserMapper.class).modify2(user);
            //模拟回滚
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            count=0;
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("testModify2 count:"+count);
    }

    @Test
    public void testGetUserByRoleId_foreach_array(){
        SqlSession sqlSession=null;
        List<User> userList=new ArrayList<User>();
        Integer[] roleIds={2,3};
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            userList=sqlSession.getMapper(UserMapper.class).getUserByRoleId_foreach_array(roleIds);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("userList.size:=========>"+userList.size());
        for (User user:userList) {
            logger.debug("user====>id:"+user.getId()+
            ",userCode:"+user.getUserCode()+
            ",userName:"+user.getUserName()+
                    ",userRole:"+user.getRole());
        }
    }


    @Test
    public void testgetUserByRoleId_foreach_list(){
        SqlSession sqlSession=null;
        List<User> userList=new ArrayList<User>();
        List<Integer> roleList=new ArrayList<Integer>();
        roleList.add(2);
        roleList.add(3);
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            userList=sqlSession.getMapper(UserMapper.class).getUserByRoleId_foreach_list(roleList);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("userList.size:=========>"+userList.size());
        for (User user:userList) {
            logger.debug("user====>id:"+user.getId()+
                    ",userCode:"+user.getUserCode()+
                    ",userName:"+user.getUserName()+
                    ",userRole:"+user.getRole());
        }
    }

    @Test
    public void testgetUserByRoleId_foreach_map(){
        SqlSession sqlSession=null;
        List<User> userList=new ArrayList<User>();
        Map<String,Object> conditionMap=new HashMap<String,Object>();
        List<Integer> roleList=new ArrayList<Integer>();
        roleList.add(2);
        roleList.add(3);
        conditionMap.put("gender",1);
        conditionMap.put("roleIds",roleList);
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            userList=sqlSession.getMapper(UserMapper.class).getUserByRoleId_foreach_map(conditionMap);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("userList.size:=========>"+userList.size());
        for (User user:userList) {
            logger.debug("user====>id:"+user.getId()+
                    ",userCode:"+user.getUserCode()+
                    ",userName:"+user.getUserName()+
                    ",userRole:"+user.getRole());
        }
    }

    @Test//将单参数封装成map
    public void getUserByRoleId_foreach_map1(){
        SqlSession sqlSession=null;
        List<User> userList=new ArrayList<User>();
        Map<String,Object> roleMap=new HashMap<String,Object>();
        List<Integer> roleList=new ArrayList<Integer>();
        roleList.add(2);
        roleList.add(3);
        roleMap.put("rKey",roleList);
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            userList=sqlSession.getMapper(UserMapper.class).getUserByRoleId_foreach_map1(roleMap);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("userList.size:=========>"+userList.size());
        for (User user:userList) {
            logger.debug("user====>id:"+user.getId()+
                    ",userCode:"+user.getUserCode()+
                    ",userName:"+user.getUserName()+
                    ",userRole:"+user.getRole());
        }
    }

    @Test
    public void testGetUserList_choose(){
        SqlSession sqlSession=null;
        List<User> userList=new ArrayList<User>();
        String userName="";
        Integer roleId=null;
        String userCode="";
        try {
            Date creationDate=new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01");
            sqlSession=MyBatisUtil.createSqlSession();
            userList=sqlSession.getMapper(UserMapper.class).getUserList_choose(userName,roleId,userCode,creationDate);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("userList.size:=========>"+userList.size());
        for (User user:userList) {
            logger.debug("user====>id:"+user.getId()+
                    ",userCode:"+user.getUserCode()+
                    ",userName:"+user.getUserName()+
                    ",userRole:"+user.getRole());
        }
    }


    @Test
    public void testGetUserList4(){
       SqlSession sqlSession=null;
       List<User> userList=new ArrayList<User>();
       try {
           sqlSession=MyBatisUtil.createSqlSession();
           String userName="";
           Integer roleId=null;
           Integer pageSize=5;
           Integer currentPageNo=0;
           userList=sqlSession.getMapper(UserMapper.class).getUserList5(userName,roleId,currentPageNo,pageSize);
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           MyBatisUtil.closeSession(sqlSession);
       }
       logger.debug("userList4.size----->"+userList.size());
        for (User user:userList) {
            logger.debug("testGetUserList4====>id:"+user.getId()+
            " and userCode:"+user.getUserCode()+
            " and userRole:"+user.getUserRole()+
            " and userRoleName:"+user.getUserRoleName()+
            " and phone:"+user.getPhone()+
            " and gender:"+user.getGender()+
            " and creationDate:"+new SimpleDateFormat("yyyy_MMM-dd").format(user.getCreationDate()));
        }
    }
}
