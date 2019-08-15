package cn.qwx.dao.Bill;

import cn.qwx.pojo.Bill;
import cn.qwx.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    /**
     * 根据商品名称进行模糊查询
     * @param bill
     * @return
     */
    public List<Bill> getBillByName(Bill bill);

    /**
     * 新增供应商
     * @return
     */
    public int add(Provider provider);

    /**
     * 修改供应商
     * @param provider
     * @return
     */
    public int modify(Provider provider);

    /**
     * 根据id删除供应商信息
     * @param Id
     * @return
     */
    public int deleteById(@Param("id") Integer id);
}
