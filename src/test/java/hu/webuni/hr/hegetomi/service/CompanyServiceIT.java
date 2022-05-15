package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.model.Position;
import hu.webuni.hr.hegetomi.model.company.Company;
import hu.webuni.hr.hegetomi.model.company.CompanyPositionSalary;
import hu.webuni.hr.hegetomi.repository.EmployeeRepository;
import hu.webuni.hr.hegetomi.repository.PositionRepository;
import hu.webuni.hr.hegetomi.repository.company.CompanyPositionSalaryRepository;
import hu.webuni.hr.hegetomi.repository.company.CompanyRepository;
import hu.webuni.hr.hegetomi.repository.company.CompanyTypeRepository;
import hu.webuni.hr.hegetomi.service.company.CompanyPositionSalaryService;
import hu.webuni.hr.hegetomi.service.company.CompanyService;
import hu.webuni.hr.hegetomi.service.employee.EmployeeServiceAncestor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@AutoConfigureTestDatabase
public class CompanyServiceIT {

    @Autowired
    CompanyService companyService;

    @Autowired
    EmployeeServiceAncestor employeeServiceAncestor;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    CompanyPositionSalaryService companyPositionSalaryService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyPositionSalaryRepository companyPositionSalaryRepository;

    @Autowired
    CompanyTypeRepository companyTypeRepository;

    @BeforeEach
    public void init(){
        employeeRepository.deleteAll();
        companyPositionSalaryRepository.deleteAll();
        companyRepository.deleteAll();
        companyTypeRepository.deleteAll();
        positionRepository.deleteAll();
    }

    @Test
    void testAddEmployee() throws Exception {
        Company newCompany = companyService.createCompany("Name", "Address", 1234, "zrt");
        Position gyakornok = positionRepository.save(new Position(0, "Gyakornok", null));
        CompanyPositionSalary comp1gyakornok = new CompanyPositionSalary(0, newCompany, gyakornok, 1050);
        companyPositionSalaryService.save(comp1gyakornok);

        companyService.addEmployeeToCompany(newCompany.getId(),
                new Employee(0, "Gyakornok4", gyakornok, 1200, LocalDateTime.of(2021, 12, 10, 1, 1)));
        companyService.addEmployeeToCompany(newCompany.getId(),
                new Employee(0, "Gyakornok1", gyakornok, 1000, LocalDateTime.of(2020, 5, 4, 1, 1)));

        List<Employee> employees = employeeRepository.findAll();
        assertThat(employees).isNotEmpty();
        for (Employee e : employees) {
            assertThat(e.getWorksAt().getId()).isEqualTo(newCompany.getId());
        }

    }

    @Test
    void testReplaceEmployees() throws Exception {

        Company newCompany = createCompany("Name", "Address", 1234, "zrt");
        Position gyakornok = positionRepository.save(new Position(0, "Gyakornok", null));
        CompanyPositionSalary comp1gyakornok = new CompanyPositionSalary(0, newCompany, gyakornok, 1050);
        companyPositionSalaryService.save(comp1gyakornok);

        Employee gyakornok4 = new Employee(0, "Gyakornok4", gyakornok, 1200, LocalDateTime.of(2021, 12, 10, 1, 1));
        companyService.addEmployeeToCompany(newCompany.getId(), gyakornok4);
        Employee gyakornok1 = new Employee(0, "Gyakornok1", gyakornok, 1000, LocalDateTime.of(2020, 5, 4, 1, 1));
        companyService.addEmployeeToCompany(newCompany.getId(), gyakornok1);

        List<Employee> emp2 = new ArrayList<>(List.of(
                new Employee(0, "Titkár2", gyakornok, 1600, LocalDateTime.of(2022, 1, 12, 1, 1)),
                new Employee(0, "Titkár1", gyakornok, 1600, LocalDateTime.of(2019, 9, 12, 1, 1)),
                new Employee(0, "Fogalmazó2", gyakornok, 1460, LocalDateTime.of(2015, 9, 12, 1, 1)),
                new Employee(0, "Főelőadó3", gyakornok, 3500, LocalDateTime.of(2007, 9, 12, 1, 1)),
                new Employee(0, "Főelőadó2", gyakornok, 3000, LocalDateTime.of(2010, 6, 1, 1, 1)),
                new Employee(0, "Előadó4", gyakornok, 2400, LocalDateTime.of(2016, 4, 20, 1, 1)),
                new Employee(0, "Előadó3", gyakornok, 2220, LocalDateTime.of(2017, 4, 2, 1, 1))));

        companyService.replaceEmployees(newCompany.getId(), emp2);
        Company company = companyService.findById(newCompany.getId()).get();
        List<Employee> employees = company.getEmployees();

        assertThat(employees).doesNotContain(gyakornok1);
        assertThat(employees).doesNotContain(gyakornok4);
        assertThat(employees).hasSize(7);

    }


    @Test
    void testDeleteEmployee() throws Exception {

        Company newCompany = createCompany("Name", "Address", 1234, "zrt");
        Position gyakornok = positionRepository.save(new Position(0, "Gyakornok", null));
        CompanyPositionSalary comp1gyakornok = new CompanyPositionSalary(0, newCompany, gyakornok, 1050);
        companyPositionSalaryService.save(comp1gyakornok);

        companyService.addEmployeeToCompany(newCompany.getId(),
                new Employee(0, "Gyakornok4", gyakornok, 1200, LocalDateTime.of(2021, 12, 10, 1, 1)));
        Employee gyakornok1 = employeeRepository.save(
                new Employee(0, "Gyakornok1", gyakornok, 1000, LocalDateTime.of(2020, 5, 4, 1, 1)));
        companyService.addEmployeeToCompany(newCompany.getId(), gyakornok1);


        companyService.deleteEmployeeFromCompany(newCompany.getId(), gyakornok1.getId());

        Company company = companyRepository.findByIdForEmployees(newCompany.getId()).get();
        List<Employee> employees = company.getEmployees();
        assertThat(employees).hasSize(1);
        assertThat(employees).doesNotContain(gyakornok1);

    }


    @Test
    void testFindEmployeesByExample() throws Exception {
        Company company1Id = createCompany("egy", "cim1", 123, "bt");
        Company company2Id = createCompany("ketto", "cim2", 234, "kft");
        Company company3Id = createCompany("harom", "cim3", 345, "bt");
        Position gyakornok = positionRepository.save(new Position(0, "Gyakornok", null));
        Position takarito = positionRepository.save(new Position(0, "Takarito", null));
        LocalDateTime startWork = LocalDateTime.now();
        Employee employee1Id = createEmployee("Egyes Egy",takarito,500,startWork,company1Id);
        Employee employee2Id = createEmployee("Kettes",gyakornok,600,startWork,company2Id);
        Employee employee3Id = createEmployee("Egyes Ketto",takarito,610,startWork.plusDays(5),company1Id);

        //id
        Employee employee = new Employee();
        employee.setId(employee1Id.getId());
        List<Employee> byExample = employeeServiceAncestor.findByExample(employee);
        assertThat(byExample.stream().map(Employee::getId).collect(Collectors.toList())).containsExactly(employee1Id.getId());

        //name
        employee = new Employee();
        employee.setName("Egyes");
        byExample = employeeServiceAncestor.findByExample(employee);
        assertThat(byExample.stream().map(Employee::getId).collect(Collectors.toList())).containsExactly(employee1Id.getId(),employee3Id.getId());

        //Position
        employee = new Employee();
        employee.setTitle(takarito);
        byExample = employeeServiceAncestor.findByExample(employee);
        assertThat(byExample.stream().map(Employee::getId).collect(Collectors.toList())).containsExactly(employee1Id.getId(),employee3Id.getId());

        //Salary
        employee = new Employee();
        employee.setSalary(600);
        byExample = employeeServiceAncestor.findByExample(employee);
        assertThat(byExample.stream().map(Employee::getId).collect(Collectors.toList())).containsExactly(employee2Id.getId(),employee3Id.getId());

        //Start date
        employee = new Employee();
        employee.setEmpSince(startWork);
        byExample = employeeServiceAncestor.findByExample(employee);
        assertThat(byExample.stream().map(Employee::getId).collect(Collectors.toList())).containsExactly(employee1Id.getId(),employee2Id.getId());


        //Company
        employee = new Employee();
        employee.setWorksAt(company2Id);
        byExample = employeeServiceAncestor.findByExample(employee);
        assertThat(byExample.stream().map(Employee::getId).collect(Collectors.toList())).containsExactly(employee2Id.getId());

    }

    private Company createCompany(String name, String address, int regNum, String type) {
        return companyService.createCompany(name, address, regNum, type);
    }

    private Employee createEmployee(String name, Position title, long salary, LocalDateTime empSince, Company worksAt) {
        return employeeServiceAncestor.createEmployee(name, title, salary, empSince, worksAt);
    }

}
