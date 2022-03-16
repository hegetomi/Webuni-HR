package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

private EmployeeService employeeService;

public SalaryService(EmployeeService employeeService){
    this.employeeService = employeeService;
}

public void getNewSalary(Employee employee){
    long oldSalary = employee.getSalary();
    long newSalary = oldSalary + (long)(oldSalary * ((double)employeeService.getPayRaisePercent(employee)/100));
    //System.out.println((double)employeeService.getPayRaisePercent(employee)/100);
    //System.out.println(newSalary);
    employee.setSalary(newSalary);
    //System.out.println(employee.getSalary());

}

}
