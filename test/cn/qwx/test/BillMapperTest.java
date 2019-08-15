package cn.qwx.test;

import cn.qwx.dao.Bill.BillMapper;
import cn.qwx.pojo.Bill;
import cn.qwx.pojo.Provider;
import cn.qwx.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Date;
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

    @Test
    public void handsAdd(){
        SqlSession sqlSession=null;
        Provider provider=new Provider();
        provider.setProCode("JS_GYS001");
        provider.setProName("小灰机");
        provider.setProDesc("长期合作伙伴，主营产品：玩具等");
        provider.setProContact("王静玲");
        provider.setProPhone("12345678912");
        provider.setProAddress("北京");
        provider.setProFax("0735-123456");
        provider.setCreatedBy(1);
        provider.setCreationDate(new Date());
        int count=0;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            count=sqlSession.getMapper(BillMapper.class).add(provider);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("test2=====>:"+count);
    }

    @Test
    public void handsModify(){
        SqlSession sqlSession=null;
        Provider provider=new Provider();
        provider.setId(16);
        provider.setProCode("JS_GYS001");
        provider.setProName("大灰机");
        provider.setProDesc("长期合作伙伴，主营产品：玩具等");
        provider.setProContact("王静玲");
        provider.setProPhone("12345678912");
        provider.setProAddress("上海浦东新区");
        provider.setProFax("0735-123456");
        provider.setModifyBy(5);
        provider.setModifyDate(new Date());
        int count=0;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            count=sqlSession.getMapper(BillMapper.class).modify(provider);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("testModify=====>:"+count);
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession=null;
        Integer Id=16;
        int count=0;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            count=sqlSession.getMapper(BillMapper.class).deleteById(Id);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("testDelete=====>:"+count);
    }
}
