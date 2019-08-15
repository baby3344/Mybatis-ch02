package cn.qwx.test;

import cn.qwx.dao.Bill.BillMapper;
import cn.qwx.pojo.Bill;
import cn.qwx.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

public class BillMapperTest {
    private  static Logger logger=Logger.getLogger(BillMapperTest.class);

    @Test
    public void handsOne(){
        SqlSession sqlSession=null;
        List<Bill> billList=null;
        Bill bill=new Bill();
        bill.setProductName("豆");
        bill.setProviderId(6);
        bill.setIsPayment(2);
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            billList=sqlSession.getMapper(BillMapper.class).getBillByName(bill);
        }catch (Exception e){
          e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (Bill bill1:billList) {
            logger.debug("test1====>订单编码:"+bill1.getBillCode()+",商品名称:"+bill1.getProductName()+
                    ",供应商名称："+bill1.getProviderName()
                    +",账单金额："+bill1.getTotalPrice()
                    + ",是否付款:"+bill1.getIsPayment()+",创建时间："+bill1.getCreationDate());
        }
    }
}
