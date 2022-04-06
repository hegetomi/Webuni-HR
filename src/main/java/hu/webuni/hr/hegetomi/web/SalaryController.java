package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public int getRaisePercent(@RequestBody Employee employee){
        return employeeService.getPayRaisePercent(employee);
    }


}
