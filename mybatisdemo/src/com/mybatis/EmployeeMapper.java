package com.mybatis;

import com.domain.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {


    public Employee getEmployeeById(Integer id);

    /* 完成增删改*/
    //添加
    public Long addEmp(Employee employee);
    //更改
    public boolean updateEmp(Employee employee);
    //删除
    public void deleteEmp(Integer id);

    /*参数的绑定*/
    //多个参数
    public Employee getEmpByIdAndLastName(@Param("id")Integer id,@Param("lastName") String lastName);
    //pojo传参
    public Employee getEmpByPojo(Employee employee);
    //map传参
    public Employee getEmpByMap(Map<String,Object> map);

    /*返回值的绑定*/
    //返回的值集合
    public List<Employee> getEmyByGender(@Param("gender") String gender);
    //返回的是map
    public Map<String,Object> getEmpByIdReturnMap(@Param("id") Integer id);
    //返回值是 Map中带pojo
    //多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("lastName")
    public Map<String,Employee> getEmpByIdReturnMapPojo(@Param("id") Integer id);

    public Employee getEmpAndDeptById(@Param("id") Integer id);

}
