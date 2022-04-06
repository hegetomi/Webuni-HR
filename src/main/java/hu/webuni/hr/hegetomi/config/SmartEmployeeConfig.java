package hu.webuni.hr.hegetomi.config;

import hu.webuni.hr.hegetomi.service.employee.EmployeeServiceAncestor;
import hu.webuni.hr.hegetomi.service.employee.EmployeeService;
import hu.webuni.hr.hegetomi.service.employee.SmartEmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("smart")
public class SmartEmployeeConfig {

    @Bean
    public EmployeeService employeeService() {
        return new SmartEmployeeService();
    }

    @Bean
    public EmployeeServiceAncestor employeeServiceAncestor() {
        return new SmartEmployeeService();
    }

}
