package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.service.SalaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {


    private final SalaryService service;

    public SalaryController(SalaryService service){
        this.service = service;
    }

    @GetMapping
    public int getRaisePercent(@RequestBody Employee employee){
        return service.getRaisePercent(employee);
    }
}
