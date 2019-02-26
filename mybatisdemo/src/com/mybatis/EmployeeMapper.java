package com.mybatis;

import com.domain.Employee;

public interface EmployeeMapper {


    public Employee getEmployeeById(Integer id);

    /* 完成增删改*/
    //添加
    public Long addEmp(Employee employee);
    //更改
    public boolean updateEmp(Employee employee);
    //删除
    public void deleteEmp(Integer id);
}
