package cn.qwx.dao.Bill;

import cn.qwx.pojo.Bill;

import java.util.List;

public interface BillMapper {
    /**
     * 根据商品名称进行模糊查询
     * @param bill
     * @return
     */
    public List<Bill> getBillByName(Bill bill);
}
