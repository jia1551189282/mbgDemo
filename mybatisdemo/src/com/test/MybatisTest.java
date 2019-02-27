package com.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.domain.Department;
import com.domain.Employee;
import com.mybatis.DepartmentMapper;
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
    @Test
    public void testParam() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

       /* Employee employee = mapper.getEmpByIdAndLastName(1, "玊玊");
        System.out.println("++++++++++++++++++++++"+employee);*/
        Map map = new HashMap<String,Object>();
        map.put("id",1);
        map.put("lastName","玊玊");
        Employee empByMap = mapper.getEmpByMap(map);
        System.out.println("=========================="+empByMap);
        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void testReturn() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        List<Employee> emyByGender = mapper.getEmyByGender("1");
        System.out.println("================="+emyByGender);

        Map<String, Object> empByIdReturnMap = mapper.getEmpByIdReturnMap(1);
        System.out.println("================="+empByIdReturnMap);

        Map<String, Employee> empByIdReturnMapPojo = mapper.getEmpByIdReturnMapPojo(1);
        System.out.println("================="+empByIdReturnMapPojo);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testResultMap() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee empAndDeptById = mapper.getEmpAndDeptById(1);
        System.out.println("================="+empAndDeptById);
        System.out.println("==================="+empAndDeptById.getDept());
        sqlSession.commit();
        sqlSession.commit();
    }
    @Test
    public void testResultCollection() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

        Department deptByIdPlus = mapper.getDeptByIdPlus(1);
        System.out.println("==============="+deptByIdPlus);
        sqlSession.commit();
        sqlSession.close();
    }
}
