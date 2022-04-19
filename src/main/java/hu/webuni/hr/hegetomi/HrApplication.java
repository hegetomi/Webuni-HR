package hu.webuni.hr.hegetomi;

import hu.webuni.hr.hegetomi.dto.ResultPair;
import hu.webuni.hr.hegetomi.model.company.Company;
import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.model.Position;
import hu.webuni.hr.hegetomi.repository.PositionRepository;
import hu.webuni.hr.hegetomi.service.company.CompanyService;
import hu.webuni.hr.hegetomi.service.company.CompanyTypeService;
import hu.webuni.hr.hegetomi.service.InitDbService;
import hu.webuni.hr.hegetomi.service.SalaryService;
import hu.webuni.hr.hegetomi.service.employee.EmployeeServiceAncestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    PositionRepository positionRepository;

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        Employee e1 = new Employee(1, "John Doe", "Janitor",
                10000, LocalDateTime.of(2000, 12, 5, 12, 0));
        System.out.println(e1.getSalary());
        salaryService.getNewSalary(e1);
        System.out.println(e1.getSalary());

         */
        initDbService.clearDB();
        initDbService.insertTestData();

        List<Company> companyList = companyService.findByEmployeeSalaryGreaterThan(3499);
        companyList.forEach(System.out::println);

        List<Company> companyList2 = companyService.findByEmployeesMoreThan(6);
        companyList2.forEach(System.out::println);

        List<ResultPair<String, Double>> averages = companyService.getTitlesAvgSalary();
        averages.forEach(a -> System.out.println(a.getKey() + " " + a.getValue()));


        //Paging test
        System.out.println("-----------------------------------");
        Page<Employee> employees = employeeServiceAncestor.findByName("Gyakornok", 0, 2);
        System.out.println("Total employees with selected name: " + employees.getTotalElements());
        System.out.println("In pages of two it is : " + employees.getTotalPages() + " page(s)");
//        System.out.println(employees.getNumber());
        System.out.println("current page: " + employees.getPageable());
        employees.stream().forEach(System.out::println);
        System.out.println("Final page: " + (employees.getPageable().getPageNumber()+1 == employees.getTotalPages()));
        System.out.println("--------Next page");
        employees = employeeServiceAncestor.findByName("Gyakornok",
                employees.getPageable().getPageNumber() + 1,
                employees.getPageable().getPageSize());
        employees.stream().forEach(System.out::println);
        System.out.println("Final page: " + (employees.getPageable().getPageNumber()+1 == employees.getTotalPages()));


        //Test giving raise for Position / positionAtCompany
        System.out.println("-----------------------------------");
        companyService.raiseForAllAtPosition("Titkár", 6000);
        companyService.raiseForAllAtCompanyAtPosition(163, "Előadó", 15000);

        employeeServiceAncestor.save(new Employee(0, "Főni", new Position(0,"Főni","semmi"), 50000, LocalDateTime.of(2017, 4, 2, 1, 1)));

    }

}
