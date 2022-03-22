package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HrThymeController {

    Map<Long,Employee> employees = new HashMap<>();

    {
        employees.put(0L,new Employee(0, "John Doe", "Architect", 2000, LocalDateTime.of(2000, 2, 10, 12, 12)));
        employees.put(1L,new Employee(1, "Jane Doe", "Lead dev", 1500, LocalDateTime.of(2010, 4, 15, 12, 12)));
        employees.put(2L,new Employee(2, "Joe Schmoe", "Senior dev", 1000, LocalDateTime.of(2015, 6, 20, 12, 12)));
        employees.put(3L,new Employee(3, "Jane Smith", "Medior dev", 500, LocalDateTime.of(2020, 8, 25, 12, 12)));
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/employees")
    public String getEmployees(Map<String, Object> model) {

        model.put("employees", new ArrayList<>(employees.values()));
        model.put("newEmployee", new Employee());
        return "employees";
    }

    @PostMapping("/employees")
    public String addEmployee(Employee employee) {
        employees.put(employee.getId(),employee);
        return "redirect:employees";
    }

    @GetMapping("/editemployee/{id}")
    public String editEmployee(@PathVariable long id, Map<String, Object> model){
    model.put("employee",employees.get(id));
    return "editemployee";
    }

    @PutMapping("/employees")
    public String submitChanges(Employee employee){
        employees.put(employee.getId(), employee);
        return "employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id){
        employees.remove(id);
        return "redirect:/employees";
    }
}
