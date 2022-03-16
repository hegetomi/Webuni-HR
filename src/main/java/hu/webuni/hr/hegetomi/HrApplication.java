package hu.webuni.hr.hegetomi;

import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

    @Autowired
    SalaryService salaryService;

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Employee e1 = new Employee(1,"John Doe","Janitor",
                10000, LocalDateTime.of(2019,12,5,12,0));
        System.out.println(e1.getSalary());
        salaryService.getNewSalary(e1);
        System.out.println(e1.getSalary());
    }

}
