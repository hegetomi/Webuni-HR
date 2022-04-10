package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.exception.EmployeeIsEmployedException;
import hu.webuni.hr.hegetomi.model.Company;
import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.repository.CompanyRepository;
import hu.webuni.hr.hegetomi.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Transactional
    public Company save(Company company) {
        //Ensure data integrity - change employees from the respective api
        company.setEmployees(new ArrayList<>());
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
        companyRepository.deleteById(id);
    }

    //Employee validation is lost
    @Transactional
    public Optional<Employee> addEmployeeToCompany(long id, Employee employee) {
        if (!companyRepository.existsById(id)) {
            return Optional.empty();
        } else {
            checkEmployeeIntegrity(id, employee);

            Company company = companyRepository.getById(id);
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

    //Employee validation is lost
    @Transactional
    public Optional<List<Employee>> replaceEmployees(long compId, List<Employee> employees) {

        Logger logger = LoggerFactory.getLogger(CompanyService.class);

        if (!companyRepository.existsById(compId)) {
            return Optional.empty();
        } else {

            List<Employee> currEmployees = companyRepository.findById(compId).get().getEmployees();

            for (Employee employee : employees) {
                checkEmployeeIntegrity(compId, employee);
            }

            for (Employee employee : currEmployees) {
                employee.setWorksAt(null);
                //employeeRepository.save(employee);
            }

            Company company = companyRepository.getById(compId);
            company.setEmployees(employees);
            //companyRepository.save(company);
            return Optional.of(employees);
        }

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

    /**
     * Caution: You are entering spaghetti zone. Abandon all hope, ye who enter here.
     *
     * @param company
     */
   /* private void saveNewEmployees(Company company) {

        Logger logger = LoggerFactory.getLogger(CompanyService.class);
        for (Employee e : company.getEmployees()) {
            logger.warn(String.valueOf(e.getId()));
            if (!employeeRepository.existsById(e.getId())) {
                e.setWorksAt(company);
                employeeRepository.save(e);
            } else {
                List<Company> companies = findAll();
                for (Company c : companies) {
                    Optional<Employee> isEmpEmployed = c.getEmployees()
                            .stream()
                            .filter(employee -> employee.getId() == e.getId())
                            .findFirst();
                    if (isEmpEmployed.isPresent() && c.getId() != company.getId()) {
                        throw new EmployeeIsEmployedException(e.getId());
                    }
                }
            }
        }
    }

    public void trySavingNew(Company company) {
        for (Employee e : company.getEmployees()) {

            Optional<Employee> employeeToSave = employeeRepository.findById(e.getId());
            //1. Employee does not exist - however no validation here, suboptimal
            if (employeeToSave.isEmpty()) {
                e.setWorksAt(company);
                employeeRepository.save(e);
            } else {
                Employee existingEmployee = employeeToSave.get();
                //2. Employee exists, with no company
                if (existingEmployee.getWorksAt() == null) {
                    e.setWorksAt(company);
                    employeeRepository.save(e);
                } else {
                    //3. Employee exists, with other company
                    if (existingEmployee.getWorksAt().getId() != company.getId()) {
                        throw new EmployeeIsEmployedException(e.getId());
                    } else {
                        employeeRepository.save(e);
                    }
                }
            }


        }
    }


    */
}
