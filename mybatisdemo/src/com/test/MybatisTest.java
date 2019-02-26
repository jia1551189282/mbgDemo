package com.test;


import java.io.IOException;
import java.io.InputStream;

import com.domain.Employee;
import com.mybatis.EmployeeMapper;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


public class MybatisTest {
    @Test
    public  void testDemo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        Employee employee = sqlSession.selectOne("getEmpById", 1);
        System.out.println(employee);

        sqlSession.close();
    }

    /**
     * 接口式 测试
     * @throws IOException
     */
    @Test
    public void testMapper() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmployeeById(2);

        System.out.println(employee);
        sqlSession.close();
    }

    @Test
    public void testMapperDemo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //测试添加
       /* Employee employee = new Employee("宝宝","宝宝@qq.com","1");
        mapper.addEmp(employee);
        System.out.println(employee);
        //测试修改*/
        /*Employee employee1 = new Employee("梦梦","susu@qq.com","1");
        employee1.setId(2);
        mapper.updateEmp(employee1);*/
        //测试删除
        mapper.deleteEmp(2);

       sqlSession.commit();
       sqlSession.close();


    }
}
