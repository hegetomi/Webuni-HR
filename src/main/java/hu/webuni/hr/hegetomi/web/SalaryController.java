package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.service.SalaryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {


    private final SalaryService service;

    public SalaryController(SalaryService service){
        this.service = service;
    }

    @PostMapping
    public int getRaisePercent(@RequestBody Employee employee){
        return service.getRaisePercent(employee);
    }
}
