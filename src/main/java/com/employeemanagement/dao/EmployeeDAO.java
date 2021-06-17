package com.employeemanagement.dao;

import com.employeemanagement.entity.EmployeeDetails;

import java.util.List;

public interface EmployeeDAO {

    boolean isUserExist(String userName);

    void saveEmployee(EmployeeDetails employeeDto) throws Exception;

    EmployeeDetails getEmployeeByUserName(String userName);

    List<EmployeeDetails> getEmployeeList();
}
