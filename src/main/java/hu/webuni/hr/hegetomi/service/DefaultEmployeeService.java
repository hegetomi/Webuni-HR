package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.config.HrConfigProperties;
import hu.webuni.hr.hegetomi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmployeeService implements EmployeeService {

    @Autowired
    HrConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        return config.getRaise().getDef().getPercent();
    }
}
