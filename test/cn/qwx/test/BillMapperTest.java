package cn.qwx.test;

import cn.qwx.dao.Bill.BillMapper;
import cn.qwx.pojo.Bill;
import cn.qwx.pojo.Provider;
import cn.qwx.pojo.Role;
import cn.qwx.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
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

    //上机练习3
    @Test
    public void testGetBillProvider(){
        SqlSession sqlSession=null;
        List<Bill> billlist=null;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            billlist=sqlSession.getMapper(BillMapper.class).getBillProvider("豆",6,2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (Bill bill1:billlist) {
            logger.debug("getBillProviderTest3====>订单编码:"+bill1.getBillCode()+",商品名称:"+bill1.getProductName()+
                    ",供应商编码："+bill1.getProvider().getProCode()
                    +",供应商名称："+bill1.getProvider().getProName()
                    +",供应商联系人："+bill1.getProvider().getProContact()
                    +",供应商联系电话："+bill1.getProvider().getProPhone()
                    +",账单金额："+bill1.getTotalPrice()
                    + ",是否付款:"+bill1.getIsPayment());
        }
    }

    //上机练习四
    @Test
    public void testgetBillProviderList(){
        SqlSession sqlSession=null;
        List<Bill> billlist=null;
        Integer id=1;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            billlist=sqlSession.getMapper(BillMapper.class).getBillProviderList(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (Bill bill1:billlist) {
            logger.debug("TestGetBillProviderList====>"
                    +",供应商id:"+bill1.getProvider().getId()
                    + ",供应商编码："+bill1.getProvider().getProCode()
                    + ",商品名称:"+bill1.getProductName()
                    +",供应商名称："+bill1.getProvider().getProName()
                    +",供应商联系人："+bill1.getProvider().getProContact()
                    +",供应商联系电话："+bill1.getProvider().getProPhone()
                    +",商品名称："+bill1.getProductName()
                    +",订单金额："+bill1.getTotalPrice()
                    + ",是否付款:"+bill1.getIsPayment());
        }
    }

    /**
     * 简答题增加操作
     */
    @Test
    public void TestAddRole(){
          SqlSession sqlSession=null;
          Role role=new Role();
          role.setRoleCode("SMBMS_ADMIN");
          role.setRoleName("普通员工");
          role.setCreatedBy(1);
          int count=0;
          role.setCreationDate(new Date());
          try{
              sqlSession=MyBatisUtil.createSqlSession();
              count=sqlSession.getMapper(BillMapper.class).addRole(role);
              sqlSession.commit();
          }catch (Exception e){
              e.printStackTrace();
              sqlSession.rollback();
          }finally {
              MyBatisUtil.closeSession(sqlSession);
          }
         logger.debug("ShortAddTest=====>"+count);
    }

    //根据id修改角色信息
    @Test
   public void TestModifyById(){
        SqlSession sqlSession=null;
        Role role=new Role();
        role.setId(4);
        role.setRoleCode("SMBMS_ADMIN");
        role.setRoleName("经理");
        role.setCreatedBy(1);
        int count=0;
        role.setCreationDate(new Date());
        try{
            sqlSession=MyBatisUtil.createSqlSession();
            count=sqlSession.getMapper(BillMapper.class).modifyById(role);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("ShortAddTest=====>"+count);
  }

  @Test
  public void test4(){
        SqlSession sqlSession=null;
        Integer id=4;
        try{
            sqlSession=MyBatisUtil.createSqlSession();
            int count=sqlSession.getMapper(BillMapper.class).getCount(id);
            if(count>0){
                int del=sqlSession.getMapper(BillMapper.class).delUserId(id);
                if(del>0){
                    int num=sqlSession.getMapper(BillMapper.class).delId(id);
                }
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
    }

    @Test
    public void Test5(){
        SqlSession sqlSession=null;
        List<Role> roleList=new ArrayList<Role>();
        String name="经理";
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            roleList=sqlSession.getMapper(BillMapper.class).getRoleByName(name);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (Role role: roleList) {
            logger.debug("简答:=====>"+role.getRoleCode());
        }
    }

    @Test//ch03上机练习1
    public void Ch03Test1(){
        SqlSession sqlSession=null;
        List<Bill> billList=new ArrayList<Bill>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            billList=sqlSession.getMapper(BillMapper.class).getBillByList("豆",null,null);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (Bill bill1:billList) {
            logger.debug("test1====>订单编码:"+bill1.getBillCode()+",商品名称:"+bill1.getProductName()+
                    ",供应商名称："+bill1.getProvider().getProName()+
                    ",供应商id："+bill1.getProvider().getId()
                    +",账单金额："+bill1.getTotalPrice()
                    + ",是否付款:"+bill1.getIsPayment()+",创建时间："+bill1.getCreationDate());
        }
    }

    @Test//(ch03)上机练习5
    public void TestgetBilList_array(){
      SqlSession sqlSession=null;
      List<Bill> billList=new ArrayList<Bill>();
      Integer[] providerId={13,14};
      try {
          sqlSession=MyBatisUtil.createSqlSession();
          billList=sqlSession.getMapper(BillMapper.class).getBilList_array(providerId);
      }catch (Exception e){
          e.printStackTrace();
      }finally {
          MyBatisUtil.closeSession(sqlSession);
      }
      logger.debug("billList.size:"+billList.size());
        for (Bill bill:billList) {
            logger.debug("bill=====》"+bill.getProductName());
        }
    }
}
