package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.model.*;
import hu.webuni.hr.hegetomi.model.company.Company;
import hu.webuni.hr.hegetomi.model.company.CompanyPositionSalary;
import hu.webuni.hr.hegetomi.model.company.CompanyType;
import hu.webuni.hr.hegetomi.repository.*;
import hu.webuni.hr.hegetomi.repository.company.CompanyPositionSalaryRepository;
import hu.webuni.hr.hegetomi.repository.company.CompanyRepository;
import hu.webuni.hr.hegetomi.repository.company.CompanyTypeRepository;
import hu.webuni.hr.hegetomi.service.company.CompanyService;
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

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private CompanyPositionSalaryRepository companyPositionSalaryRepository;

    @Autowired
    private CompanyService companyService;

    public void clearDB() {
        employeeRepository.deleteAll();
        companyPositionSalaryRepository.deleteAll();
        companyRepository.deleteAll();
        companyTypeRepository.deleteAll();
        positionRepository.deleteAll();

    }

    public void insertTestData() {

        //Setup types
        companyTypeRepository.save(new CompanyType(0, "bt"));
        companyTypeRepository.save(new CompanyType(0, "kft"));
        companyTypeRepository.save(new CompanyType(0, "nyrt"));
        companyTypeRepository.save(new CompanyType(0, "zrt"));
        //setup positions
        positionRepository.save(new Position(0, "Gyakornok", null));
        positionRepository.save(new Position(0, "Előadó", "Msc"));
        positionRepository.save(new Position(0, "Fogalmazó", "Bsc"));
        positionRepository.save(new Position(0, "Titkár", "Érettségi"));
        positionRepository.save(new Position(0, "Főelőadó", "Phd"));
        //save dummy companies
        Company comp1 = new Company(0, 123456, "Pest m. Kormányhivatal", "1113 Hosszú utca 234", "bt", new ArrayList<>(),new ArrayList<>());
        Company comp2 = new Company(0, 123456, "Sz-Sz-B m. Földhivatal", "4400 Kossuth utca 1", "kft", new ArrayList<>(), new ArrayList<>());
        comp1.setAvailablePositions(new ArrayList<>());
        comp2.setAvailablePositions(new ArrayList<>());
        companyRepository.save(comp1);
        companyRepository.save(comp2);

        //Setup salary dummies
        Position gyakornok = positionRepository.getByName("Gyakornok").get();
        Position eloado = positionRepository.getByName("Előadó").get();
        Position fogalmazo = positionRepository.getByName("Fogalmazó").get();
        Position titkar = positionRepository.getByName("Titkár").get();
        Position foeloado = positionRepository.getByName("Főelőadó").get();

        CompanyPositionSalary comp1gyakornok = new CompanyPositionSalary(0, comp1, gyakornok, 1050);
        CompanyPositionSalary comp2gyakornok = new CompanyPositionSalary(0, comp2, gyakornok, 1100);
        companyPositionSalaryRepository.save(comp1gyakornok);
        companyPositionSalaryRepository.save(comp2gyakornok);
        CompanyPositionSalary comp1eloado = new CompanyPositionSalary(0, comp1, eloado, 1250);
        CompanyPositionSalary comp2eloado = new CompanyPositionSalary(0, comp2, eloado, 1300);
        companyPositionSalaryRepository.save(comp1eloado);
        companyPositionSalaryRepository.save(comp2eloado);
        CompanyPositionSalary comp1fogalmazo = new CompanyPositionSalary(0, comp1, fogalmazo, 1450);
        CompanyPositionSalary comp2fogalmazo = new CompanyPositionSalary(0, comp2, fogalmazo, 1500);
        companyPositionSalaryRepository.save(comp1fogalmazo);
        companyPositionSalaryRepository.save(comp2fogalmazo);
        CompanyPositionSalary comp1titkar = new CompanyPositionSalary(0, comp1, titkar, 1650);
        CompanyPositionSalary comp2titkar = new CompanyPositionSalary(0, comp2, titkar, 1700);
        companyPositionSalaryRepository.save(comp1titkar);
        companyPositionSalaryRepository.save(comp2titkar);
        CompanyPositionSalary comp1foeloado = new CompanyPositionSalary(0, comp1, foeloado, 1850);
        CompanyPositionSalary comp2foealoado = new CompanyPositionSalary(0, comp2, foeloado, 1900);
        companyPositionSalaryRepository.save(comp1foeloado);
        companyPositionSalaryRepository.save(comp2foealoado);


//Add dummy employees
        employeeRepository.save(new Employee(0, "Gyakornok2", gyakornok, 1250, LocalDateTime.of(2020, 4, 15, 1, 1)));
        employeeRepository.save(new Employee(0, "Gyakornok3", gyakornok, 1300, LocalDateTime.of(2019, 8, 21, 1, 1)));


        List<Employee> emp1 = new ArrayList<>(List.of(
                new Employee(0, "Gyakornok4", gyakornok, 1200, LocalDateTime.of(2021, 12, 10, 1, 1)),
                new Employee(0, "Gyakornok1", gyakornok, 1000, LocalDateTime.of(2020, 5, 4, 1, 1)),
                new Employee(0, "Előadó1", eloado, 2000, LocalDateTime.of(2018, 2, 28, 1, 1)),
                new Employee(0, "Előadó2", eloado, 2370, LocalDateTime.of(2016, 10, 13, 1, 1)),
                new Employee(0, "Fogalmazó1", fogalmazo, 1400, LocalDateTime.of(2016, 9, 12, 1, 1)),
                new Employee(0, "Főelőadó1", fogalmazo, 2690, LocalDateTime.of(2013, 1, 14, 1, 1))
        ));
        emp1.forEach(e -> e.setWorksAt(comp1));
        employeeRepository.saveAll(emp1);


        List<Employee> emp2 = new ArrayList<>(List.of(
                new Employee(0, "Titkár2", titkar, 1600, LocalDateTime.of(2022, 1, 12, 1, 1)),
                new Employee(0, "Titkár1", titkar, 1600, LocalDateTime.of(2019, 9, 12, 1, 1)),
                new Employee(0, "Fogalmazó2", fogalmazo, 1460, LocalDateTime.of(2015, 9, 12, 1, 1)),
                new Employee(0, "Főelőadó3", foeloado, 3500, LocalDateTime.of(2007, 9, 12, 1, 1)),
                new Employee(0, "Főelőadó2", foeloado, 3000, LocalDateTime.of(2010, 6, 1, 1, 1)),
                new Employee(0, "Előadó4", eloado, 2400, LocalDateTime.of(2016, 4, 20, 1, 1)),
                new Employee(0, "Előadó3", eloado, 2220, LocalDateTime.of(2017, 4, 2, 1, 1))));
        emp2.forEach(e -> e.setWorksAt(comp2));
        employeeRepository.saveAll(emp2);


        //How to delete aa company
        /*Optional<Company> c  = companyRepository.findById(155L);
        Company co = c.get();
        List<Employee> employeeList =co.getEmployees();
        employeeList.forEach(e -> e.setWorksAt(null));
        employeeRepository.saveAll(employeeList);
        co.setEmployees(new ArrayList<>());
        co.setAvailablePositions(new ArrayList<>());

        companyPositionSalaryRepository.deleteAllByCompanyId(co.getId());
        companyRepository.save(co);
        companyRepository.delete(comp1);

         */
    }


}
