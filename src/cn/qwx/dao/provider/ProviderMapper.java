package cn.qwx.dao.provider;

import cn.qwx.pojo.Provider;
import org.apache.ibatis.annotations.Param;

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
}
