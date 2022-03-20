package hu.webuni.hr.hegetomi.config;

import hu.webuni.hr.hegetomi.service.DefaultEmployeeService;
import hu.webuni.hr.hegetomi.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!smart")
public class DefaultEmployeeConfig {

    @Bean
    public EmployeeService employeeService() {
        return new DefaultEmployeeService();
    }

}
