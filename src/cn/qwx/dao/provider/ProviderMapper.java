package cn.qwx.dao.provider;

import cn.qwx.pojo.Provider;

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
}
