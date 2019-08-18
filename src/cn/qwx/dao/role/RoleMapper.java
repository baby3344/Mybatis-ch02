package cn.qwx.dao.role;

import cn.qwx.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    /**
     * 使用if+set 根据角色id修改角色信息
     * @param role
     * @return
     */
    public int UpdateById(Role role);

    /**
     * 使用if+trim实现根据角色名称模糊查询角色信息，并分页展示
     * @param roleName
     * @return
     */
    public List<Role> getRolelikeByName(@Param("roleName")String roleName,@Param("from")Integer from,@Param("pageSize")Integer pageSize);
}
