package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmployeeService implements EmployeeService{
    @Override
    public int getPayRaisePercent(Employee employee) {
        return 5;
    }
}
