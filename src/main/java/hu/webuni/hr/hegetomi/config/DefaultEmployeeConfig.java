package hu.webuni.hr.hegetomi.config;

import hu.webuni.hr.hegetomi.service.employee.DefaultEmployeeServiceService;
import hu.webuni.hr.hegetomi.service.employee.EmployeeServiceAncestor;
import hu.webuni.hr.hegetomi.service.employee.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!smart")
public class DefaultEmployeeConfig {

    @Bean
    public EmployeeService employeeService() {

        return new DefaultEmployeeServiceService();
    }

    @Bean
    public EmployeeServiceAncestor employeeAncestor(){
        return new DefaultEmployeeServiceService();
    }

}
