package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HrThymeController {

    List<Employee> employees = new ArrayList<>();

    {
        employees.add(new Employee(0, "John Doe", "Architect", 2000, LocalDateTime.of(2000, 2, 10, 12, 12)));
        employees.add(new Employee(1, "Jane Doe", "Lead dev", 1500, LocalDateTime.of(2010, 4, 15, 12, 12)));
        employees.add(new Employee(2, "Joe Schmoe", "Senior dev", 1000, LocalDateTime.of(2015, 6, 20, 12, 12)));
        employees.add(new Employee(3, "Jane Smith", "Medior dev", 500, LocalDateTime.of(2020, 8, 25, 12, 12)));
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/employees")
    public String getEmployees(Map<String, Object> model) {
        model.put("employees", employees);
        model.put("newEmployee", new Employee());
        return "employees";
    }

    @PostMapping("/employees")
    public String addEmployee(Employee employee) {
        employees.add(employee);
        return "redirect:employees";
    }

}
