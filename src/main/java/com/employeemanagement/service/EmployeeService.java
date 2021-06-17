package com.employeemanagement.service;

import com.employeemanagement.model.EmployeeDto;
import java.util.List;

public interface EmployeeService {
    public boolean isUserExist(String userName);

    public void saveEmployee(EmployeeDto employeeDto) throws Exception;

    public EmployeeDto getEmployeeByUserName(String userName);

    public List<EmployeeDto> getEmployeeList();
}
