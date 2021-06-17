package com.employeemanagement.service.impl;

import com.employeemanagement.dao.EmployeeDAO;
import com.employeemanagement.entity.EmployeeDetails;
import com.employeemanagement.model.EmployeeDto;
import com.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public boolean isUserExist(String userName) {
        return employeeDAO.isUserExist(userName);
    }

    @Override
    public void saveEmployee(EmployeeDto employeeDto) throws Exception {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmailId(employeeDto.getEmail());
        employeeDetails.setUserName(employeeDto.getUserName());
        employeeDetails.setFirstName(employeeDto.getFirstName());
        employeeDetails.setLastName(employeeDto.getLastName());
        employeeDAO.saveEmployee(employeeDetails);
    }

    @Override
    public EmployeeDto getEmployeeByUserName(String userName) {
        EmployeeDetails employeeDetails = employeeDAO.getEmployeeByUserName(userName);
        if (employeeDetails == null) {
            return null;
        }
        return new EmployeeDto(employeeDetails.getUserName(), employeeDetails.getFirstName(),
                employeeDetails.getLastName(), employeeDetails.getEmailId());
    }

    @Override
    public List<EmployeeDto> getEmployeeList() {
        List<EmployeeDetails> employeeDetails = employeeDAO.getEmployeeList();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        if (employeeDetails == null || employeeDetails.isEmpty())
            return null;
        for (EmployeeDetails emp : employeeDetails) {
            employeeDtoList.add(new EmployeeDto(emp.getUserName(), emp.getFirstName(),
                    emp.getLastName(), emp.getEmailId()));
        }
        return employeeDtoList;
    }
}
