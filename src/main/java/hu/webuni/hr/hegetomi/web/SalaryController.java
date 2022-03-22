package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    private SalaryService service;

    @PostMapping
    public Employee getRaisePercent(@RequestBody Employee employee){

        service.getNewSalary(employee);
        return employee;

    }

}
