package cn.qwx.test;

import cn.qwx.dao.provider.ProviderMapper;
import cn.qwx.pojo.Provider;
import cn.qwx.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProviderMapperTest {
    private static Logger logger=Logger.getLogger(ProviderMapperTest.class);

    /**
     * 上机1
     */
    @Test
    public void test1(){
        SqlSession sqlSession=null;
        int count=0;
        try {
            sqlSession = MyBatisUtil.createSqlSession();
            count = sqlSession.getMapper(ProviderMapper.class).count();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("一共"+count+"条记录");
    }

    /**
     * 上机二
     * 通过SqlSession实例来执行已映射的SQL语句
     */
   @Test
    public void test2(){
        SqlSession sqlSession=null;
        List<Provider> list=new ArrayList<Provider>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            //使用selectList方法执行查询操作
            list=sqlSession.selectList("cn.qwx.dao.provider.ProviderMapper.list");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (Provider provider:list) {
            logger.debug("proCode:"+provider.getProCode()+"and proName:"+provider.getProName());
        }
    }

    /**
     * 上机二
     * 基于mapper接口方式操作数据
     */
    @Test
    public void test3(){
        SqlSession sqlSession=null;
        List<Provider> list=new ArrayList<Provider>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            //使用selectList方法执行查询操作
            list=sqlSession.getMapper(ProviderMapper.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (Provider provider:list) {
            logger.debug("proCode:"+provider.getProCode()+"and proName:"+provider.getProName());
        }
    }

    @Test //(ch03)上机练习二
    public void Ch03Test2(){
        SqlSession sqlSession=null;
        List<Provider> list=new ArrayList<Provider>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            //使用selectList方法执行查询操作
            list=sqlSession.getMapper(ProviderMapper.class).getProListByCodeName("GYS001","");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (Provider provider:list) {
            logger.debug("供应商id："+provider.getId()+"，供应商编码：:"+provider.getProCode()+"，供应商名称:"+provider.getProName()+
                    "，联系人：" +provider.getProContact()+ "，联系电话："+provider.getProPhone()+
                    "，传真："+provider.getProFax()+"，创建时间："+provider.getCreationDate());
        }
    }

    @Test//(ch03)上机练习三
    public void testupdateById(){
        SqlSession sqlSession=null;
        int count=0;
        Provider provider=new Provider();
        provider.setId(15);
        provider.setProPhone("0735-666666");
        try {
           sqlSession=MyBatisUtil.createSqlSession();
           count=sqlSession.getMapper(ProviderMapper.class).updateById(provider);
           sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
           MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("受影响的行数为："+count);
    }

    @Test//(ch03)上机练习四
    public void testupdateById1(){
        SqlSession sqlSession=null;
        int count=0;
        Provider provider=new Provider();
        provider.setId(15);
        provider.setProPhone("0735-666666");
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            count=sqlSession.getMapper(ProviderMapper.class).updateById1(provider);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        logger.debug("受影响的行数为："+count);
    }

    @Test //（ch03）上机7
    public void TestgetLikeList(){
        SqlSession sqlSession=null;
        List<Provider> proList=new ArrayList<Provider>();
        try {
            Date creationDate=new SimpleDateFormat("yyyy").parse("2016");
            sqlSession=MyBatisUtil.createSqlSession();
            proList=sqlSession.getMapper(ProviderMapper.class).getLikeList("001",null,null,creationDate);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession(sqlSession);
        }
        for (Provider provider:proList) {
            logger.debug("供应商id："+provider.getId()+"，供应商编码：:"+provider.getProCode()+"，供应商名称:"+provider.getProName()+
                    "，联系人：" +provider.getProContact()+ "，联系电话："+provider.getProPhone()+
                    "，传真："+provider.getProFax()+"，创建时间："+provider.getCreationDate());
        }
    }
}
