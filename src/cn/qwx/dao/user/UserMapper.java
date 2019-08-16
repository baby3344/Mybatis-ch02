package cn.qwx.dao.user;

import cn.qwx.pojo.Address;
import cn.qwx.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 查询用户列表（参数：对象入参）示例3
     * @param user
     * @return
     */
    public List<User> getUserList(User user);

    /**
     * 查询用户列表（参数：Map）
     * @param userMap
     * @return
     */
    public List<User> getUserListByMap(Map<String, String> userMap);

    /**
     * 查询用户列表（参数：对象入参）示例8
     * @param user
     * @return
     */
    public List<User> getUserList1(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    public int add(User user);

    /**
     * 修改
     * @param user
     * @return
     */
    public int modify(User user);

    /**
     * 修改个人密码
     * @param id
     * @param pwd
     * @return
     */
    public int updatePwd(@Param("id")Integer id,@Param("userPassword")String pwd);

    /**
     * 根据用户id完成删除操作
     * @param delid
     * @return
     */
    public int deleteUserById(@Param("id")Integer delId);

    /**
     * 根据角色id获取用户列表的方法
     * @param roleId
     * @return
     */
    public List<User> getUserListByRoleId(@Param("userRole")Integer roleId);


    /**
     * 根据用户id获取用户信息以及地址列表
     * @param userId
     * @return
     */
    public List<User> getAddressListByUserId(@Param("id")Integer userId);


    /**
     * 查询用户列表 if条件
     * @param userName
     * @param userRole
     * @return
     */
    public List<User> getUserList2(@Param("userName")String userName,@Param("userRole")Integer userRole);

    /**
     * 查询用户列表 where
     * @param userName
     * @param userRole
     * @return
     */
    public List<User> getUserList3(@Param("userName")String userName,@Param("userRole")Integer userRole);

    /**
     * 查询用户列表 if+trim
     * @param userName
     * @param userRole
     * @return
     */
    public List<User> getUserList4(@Param("userName")String userName,@Param("userRole")Integer userRole);

    /**
     * 修改 if+set
     * @param user
     * @return
     */
    public int modify1(User user);

    /**
     * 修改 if+trim
     * @param user
     * @return
     */
    public int modify2(User user);

    /**
     * 根据用户角色列表，获取该角色列表下用户信息列表_foreach_array
     * @param roleIds
     * @return
     */
    public List<User> getUserByRoleId_foreach_array(Integer[] roleIds);

    /**
     * 根据用户角色列表，获取该角色列表下用户信息列表_foreach_list
     * @param roleList
     * @return
     */
    public List<User> getUserByRoleId_foreach_list(List<Integer> roleList);


    /**
     * 根据用户角色列表，获取该角色列表下用户信息列表_foreach_map
     * @param conditionMap
     * @return
     */
    public List<User> getUserByRoleId_foreach_map(Map<String,Object>conditionMap);

    /**
     * 根据用户角色列表，获取该角色列表下用户信息列表_foreach_map1（单参数封装成map）
     * @param roleMap
     * @return
     */
    public List<User> getUserByRoleId_foreach_map1(Map<String,Object>roleMap);
}
