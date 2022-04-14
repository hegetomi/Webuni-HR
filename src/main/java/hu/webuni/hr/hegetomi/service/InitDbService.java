package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.model.Company;
import hu.webuni.hr.hegetomi.model.CompanyType;
import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.repository.CompanyRepository;
import hu.webuni.hr.hegetomi.repository.CompanyTypeRepository;
import hu.webuni.hr.hegetomi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitDbService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    public void clearDB() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
        companyTypeRepository.deleteAll();
    }

    public void insertTestData() {

        companyTypeRepository.save(new CompanyType(0,"bt"));
        companyTypeRepository.save(new CompanyType(0,"kft"));
        companyTypeRepository.save(new CompanyType(0,"nyrt"));
        companyTypeRepository.save(new CompanyType(0,"zrt"));



        employeeRepository.save(new Employee(0, "Gyakornok2", "Gyakornok", 1250, LocalDateTime.of(2020, 4, 15, 1, 1)));
        employeeRepository.save(new Employee(0, "Gyakornok3", "Gyakornok", 1300, LocalDateTime.of(2019, 8, 21, 1, 1)));


        Company comp1 = new Company(0, 123456, "Pest m. Kormányhivatal", "1113 Hosszú utca 234","bt", new ArrayList<>());
        //setCompanyType(comp1);
        List<Employee> emp1 = new ArrayList<>(List.of(
                new Employee(0, "Gyakornok4", "Gyakornok", 1200, LocalDateTime.of(2021, 12, 10, 1, 1)),
                new Employee(0, "Gyakornok1", "Gyakornok", 1000, LocalDateTime.of(2020, 5, 4, 1, 1)),
                new Employee(0, "Előadó1", "Előadó", 2000, LocalDateTime.of(2018, 2, 28, 1, 1)),
                new Employee(0, "Előadó2", "Előadó", 2370, LocalDateTime.of(2016, 10, 13, 1, 1)),
                new Employee(0, "Fogalmazó1", "Fogalmazó", 1400, LocalDateTime.of(2016, 9, 12, 1, 1)),
                new Employee(0, "Főelőadó1", "Főelőadó", 2690, LocalDateTime.of(2013, 1, 14, 1, 1))
                ));
        emp1.forEach(e -> e.setWorksAt(comp1));
        employeeRepository.saveAll(emp1);


        Company comp2 = new Company(0, 123456, "Sz-Sz-B m. Földhivatal", "4400 Kossuth utca 1","kft", new ArrayList<>());
        List<Employee> emp2 = new ArrayList<>(List.of(
                new Employee(0, "Titkár2", "Titkár", 1600, LocalDateTime.of(2022, 1, 12, 1, 1)),
                new Employee(0, "Titkár1", "Titkár", 1600, LocalDateTime.of(2019, 9, 12, 1, 1)),
                new Employee(0, "Fogalmazó2", "Fogalmazó", 1460, LocalDateTime.of(2015, 9, 12, 1, 1)),
                new Employee(0, "Főelőadó3", "Főelőadó", 3500, LocalDateTime.of(2007, 9, 12, 1, 1)),
                new Employee(0, "Főelőadó2", "Főelőadó", 3000, LocalDateTime.of(2010, 6, 1, 1, 1)),
                new Employee(0, "Előadó4", "Előadó", 2400, LocalDateTime.of(2016, 4, 20, 1, 1)),
                new Employee(0, "Előadó3", "Előadó", 2220, LocalDateTime.of(2017, 4, 2, 1, 1))));
        emp2.forEach(e -> e.setWorksAt(comp2));
        employeeRepository.saveAll(emp2);
    }

}
