package hu.webuni.hr.hegetomi.service.company;

import hu.webuni.hr.hegetomi.dto.ResultPair;
import hu.webuni.hr.hegetomi.exception.EmployeeIsEmployedException;
import hu.webuni.hr.hegetomi.model.company.Company;
import hu.webuni.hr.hegetomi.model.company.CompanyPositionSalary;
import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.model.Position;
import hu.webuni.hr.hegetomi.repository.company.CompanyPositionSalaryRepository;
import hu.webuni.hr.hegetomi.repository.company.CompanyRepository;
import hu.webuni.hr.hegetomi.repository.EmployeeRepository;
import hu.webuni.hr.hegetomi.repository.PositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyPositionSalaryRepository companyPositionSalaryRepository;

    @Autowired
    PositionRepository positionRepository;

    private final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Transactional
    public Company save(Company company) {
        //Ensure data integrity - change employees from the respective api
        company.setEmployees(new ArrayList<>());
        company.setAvailablePositions(new ArrayList<>());
        //company.setAvailablePositions();
        return companyRepository.save(company);
    }

    @Transactional
    public Optional<Company> edit(Company company, long id) {
        if (companyRepository.existsById(company.getId())) {
            //Ensure data integrity - change employees from the respective api
            company.setEmployees(companyRepository.getById(company.getId()).getEmployees());
            return Optional.of(companyRepository.save(company));
        }
        return Optional.empty();
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(long id) {
        return companyRepository.findById(id);
    }

    @Transactional
    public void delete(long id) {
        //Orphaning employees
        Optional<Company> selected = findById(id);
        if (selected.isPresent()) {
            for (Employee e : selected.get().getEmployees()) {
                e.setWorksAt(null);
            }
        }
        companyRepository.deleteById(id);
    }

    @Transactional
    public Optional<Employee> addEmployeeToCompany(long id, Employee employee) {
        if (!companyRepository.existsById(id)) {
            return Optional.empty();
        } else {
            checkEmployeeIntegrity(id, employee);

            Company company = companyRepository.getById(id);
            giveSalaryRaiseIfLower(company, employee);
            company.getEmployees().add(employee);
            //companyRepository.save(company);
            return Optional.of(employee);
        }
    }


    @Transactional
    public void deleteEmployeeFromCompany(long compId, long empId) {
        if (companyRepository.existsById(compId)) {
            Company company = companyRepository.getById(compId);
            Optional<Employee> doesExist = company.getEmployees().stream().filter(e -> e.getId() == empId).findFirst();
            if (doesExist.isPresent()) {
                Employee toRemove = doesExist.get();
                toRemove.setWorksAt(null);
                //employeeRepository.save(toRemove);
            }
        }
    }

    @Transactional
    public Optional<List<Employee>> replaceEmployees(long compId, List<Employee> employees) {
        if (!companyRepository.existsById(compId)) {
            return Optional.empty();
        } else {
            Company company = companyRepository.findById(compId).get();
            List<Employee> currEmployees = company.getEmployees();
            for (Employee employee : employees) {
                //logger.warn(company.toString());
                giveSalaryRaiseIfLower(company, employee);
                checkEmployeeIntegrity(compId, employee);
            }
            for (Employee employee : currEmployees) {
                employee.setWorksAt(null);
                //employeeRepository.save(employee);
            }
            //Company company = companyRepository.getById(compId);
            company.setEmployees(employees);
            //companyRepository.save(company);
            return Optional.of(employees);
        }

    }


    public List<Company> findByEmployeeSalaryGreaterThan(long amount) {
        return companyRepository.findByEmployeeSalaryGreaterThan(amount);
    }

    public List<Company> findByEmployeesMoreThan(int amount) {
        return companyRepository.findByEmployeesMoreThan(amount);
    }

    public List<ResultPair<String, Double>> getTitlesAvgSalary() {
        List<ResultPair<String, Double>> returnSet = companyRepository.getTitlesAverageSalary();
        returnSet.sort(Comparator.comparing(ResultPair::getValue, Comparator.reverseOrder()));
        return returnSet;
    }

    public void raiseForAllAtPosition(String position, long newSalary) {
        List<CompanyPositionSalary> companyPosition = companyPositionSalaryRepository.findByPositionName(position);
        for (CompanyPositionSalary e : companyPosition) {
            e.setMinimumSalary(newSalary);
            companyPositionSalaryRepository.save(e);
        }
        List<Employee> employeeList = employeeRepository.findByTitle(position);
        for (Employee e : employeeList) {
            if (e.getSalary() < newSalary) e.setSalary(newSalary);
            employeeRepository.save(e);
        }
    }

    public void raiseForAllAtCompanyAtPosition(long companyId, String position, long newSalary) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            CompanyPositionSalary companyPosition =
                    companyPositionSalaryRepository.findByPositionNameAtCompany(position, companyId);
            companyPosition.setMinimumSalary(newSalary);
            companyPositionSalaryRepository.save(companyPosition);

            List<Employee> employees = company.get().getEmployees();
            for (Employee e : employees) {
                if (e.getTitle().getName().equals(position)) {
                    e.setSalary(newSalary);
                    employeeRepository.save(e);
                }
            }
        }
    }

    private Employee giveSalaryRaiseIfLower(Company company, Employee employee) {
        Optional<CompanyPositionSalary> possiblePosition = company.getAvailablePositions().stream()
                .filter(e -> e.getPosition().getName().equals(employee.getTitle().getName())).findFirst();

        if (possiblePosition.isPresent()) {
            employee.setTitle(possiblePosition.get().getPosition());
            if (employee.getSalary() < possiblePosition.get().getMinimumSalary())
                employee.setSalary(possiblePosition.get().getMinimumSalary());
        } else {
            //Given position does not exist at company- create a separate (checked) exception perhaps?
            Position pos = positionRepository.save(employee.getTitle());
            companyPositionSalaryRepository.save(new CompanyPositionSalary(0, company, pos, 1));
            //throw new RuntimeException();
        }
        return employee;
    }

    private void checkEmployeeIntegrity(long compId, Employee employee) {
        if (!employeeRepository.existsById(employee.getId())) {
            employee.setWorksAt(companyRepository.getById(compId));
            employeeRepository.save(employee);
        }//ha létezik és nem itt akkor throw exception
        else {
            Employee existingEmployee = employeeRepository.getById(employee.getId());
            if (existingEmployee.getWorksAt() == null) {
                existingEmployee.setWorksAt(companyRepository.getById(compId));
            }
            if (existingEmployee.getWorksAt().getId() != compId) {
                throw new EmployeeIsEmployedException(employee.getId());
            }
        }
    }

}
