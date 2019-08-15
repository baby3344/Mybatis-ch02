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



}
