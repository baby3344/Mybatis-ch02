package cn.qwx.dao.provider;

import cn.qwx.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProviderMapper {
    /**
     * 查询供应商记录数
     * @return
     */
    int count();

    /**
     * 查询供应商
     * @return
     */
    List<Provider> list();

    /**
     *动态SQL—if+where查询供应商表
     * @param proCode
     * @param proName
     * @return
     */
    public List<Provider> getProListByCodeName(@Param("proCode")String proCode,@Param("proName")String proName);

    /**
     * 使用动态SQL if+set实现供应商表的修改
     * @return
     */
    public int updateById(Provider provider);

    /**
     * 使用动态SQL if+trim实现供应商表的修改
     * @return
     */
    public int updateById1(Provider provider);

    /**
     * 使用动态SQL-choose查询供应商列表
     * @param proCode
     * @param proName
     * @param proContact
     * @return
     */
    public List<Provider> getLikeList(@Param("proCode")String proCode, @Param("proName")String proName,
                                      @Param("proContact")String proContact, @Param("creationDate")Date creationDate);

    /**
     * 分页查询供应商列表
     * @param from
     * @param pageSize
     * @return
     */
    public List<Provider> getProListPage(@Param("from")Integer from,@Param("pageSize")Integer pageSize);
}
