package hu.webuni.hr.hegetomi;

import hu.webuni.hr.hegetomi.model.Company;
import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.service.CompanyService;
import hu.webuni.hr.hegetomi.service.CompanyTypeService;
import hu.webuni.hr.hegetomi.service.InitDbService;
import hu.webuni.hr.hegetomi.service.SalaryService;
import hu.webuni.hr.hegetomi.service.employee.EmployeeService;
import hu.webuni.hr.hegetomi.service.employee.EmployeeServiceAncestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

    @Autowired
    SalaryService salaryService;

    @Autowired
    InitDbService initDbService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyTypeService companyTypeService;

    @Autowired
    EmployeeServiceAncestor employeeServiceAncestor;

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Employee e1 = new Employee(1, "John Doe", "Janitor",
                10000, LocalDateTime.of(2000, 12, 5, 12, 0));
        System.out.println(e1.getSalary());
        salaryService.getNewSalary(e1);
        System.out.println(e1.getSalary());
        initDbService.clearDB();
        initDbService.insertTestData();

        List<Company> companyList = companyService.findByEmployeeSalaryGreaterThan(3499);
        companyList.forEach(System.out::println);

        List<Company> companyList2 = companyService.findByEmployeesMoreThan(6);
        companyList2.forEach(System.out::println);

        List<Object[]> averages = companyService.getTitlesAvgSalary();
        averages.forEach(a -> System.out.println(a[0] + " " + a[1]));

    }

}
