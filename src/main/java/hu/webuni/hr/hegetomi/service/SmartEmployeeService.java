package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.model.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Primary
public class SmartEmployeeService implements EmployeeService {


    @Override
    public int getPayRaisePercent(Employee employee) {
        int months = getMonths(employee);
        if(months>=120){
            return 10;
        } else if(months > 60){
            return 5;
        } else if(months > 30){
            return 2;
        } else{
            return 0;
        }
    }

    private int getMonths(Employee employee) {
        LocalDateTime currDate = LocalDateTime.now();
        int years = currDate.getYear() - employee.getEmpSince().getYear();
        int months = currDate.getMonthValue() - employee.getEmpSince().getMonthValue();
        return years * 12 + months;
    }

}
