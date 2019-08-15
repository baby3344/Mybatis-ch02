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
     * @param id
     * @return
     */
    public int deleteById(@Param("id") Integer id);

    /**
     * 根据商品名称、供应商、是否付款进行查询
     * @param productName
     * @param
     * @param isPayment
     * @return
     */
    public List<Bill> getBillProvider(@Param("productName") String productName,@Param("providerId") Integer providerId,@Param("isPayment")Integer isPayment);


    /**
     * 根据指定的供应商id查询信息
     * @param id
     * @return
     */
    public List<Bill> getBillProviderList(@Param("id")Integer id);
}
