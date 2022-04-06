package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.service.employee.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    private final EmployeeService employeeService;

    public SalaryService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void getNewSalary(Employee employee) {
        long oldSalary = employee.getSalary();
        long newSalary = oldSalary + (long) (oldSalary * ((double) employeeService.getPayRaisePercent(employee) / 100));
        employee.setSalary(newSalary);
    }

}
