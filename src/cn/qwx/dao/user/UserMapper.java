package cn.qwx.dao.user;

import cn.qwx.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 查询用户列表（参数：对象入参）
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
}
