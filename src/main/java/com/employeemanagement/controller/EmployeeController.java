package com.employeemanagement.controller;

import com.employeemanagement.model.EmployeeDto;
import com.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/employee-management")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/home")
    public String showHomeScreen() {
        return "employee-registration";
    }

    /**
     * This API needs to trigger when the cursor moves out from the username text box
     *
     * @param userName
     * @return
     */
    @PostMapping("/user-exist")
    public ModelAndView isExistingUser(@RequestBody String userName) {
        ModelAndView modelAndView = new ModelAndView("employee-registration");
        boolean isUserExist = employeeService.isUserExist(userName);
        if (isUserExist) {
            modelAndView.addObject("message", "User exists!");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveEmployee(@RequestBody EmployeeDto employeeDto) throws Exception {
        ModelAndView modelAndView = new ModelAndView("employee-registration");
        try {
            employeeService.saveEmployee(employeeDto);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return modelAndView;
    }

    @GetMapping("/{userName}")
    public ModelAndView getEmployeeByUserName(@PathVariable String userName) {
        ModelAndView modelAndView = new ModelAndView("employee-details");
        EmployeeDto employeeDto = employeeService.getEmployeeByUserName(userName);
        modelAndView.addObject("employee", employeeDto);
        return modelAndView;
    }

    @GetMapping("/employee-list")
    public ModelAndView getEmployeeList() {
        ModelAndView modelAndView = new ModelAndView("employee-details");
        List<EmployeeDto> employeeList = employeeService.getEmployeeList();
        modelAndView.addObject("employeeList", employeeList);
        return modelAndView;
    }
}
