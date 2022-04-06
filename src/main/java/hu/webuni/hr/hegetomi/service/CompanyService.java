package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import hu.webuni.hr.hegetomi.dto.company.CompanyDto;
import hu.webuni.hr.hegetomi.model.Company;
import hu.webuni.hr.hegetomi.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CompanyService {

    private final Map<Long, Company> companies = new HashMap<>();

    {
        companies.put(0L, new Company(0L, 123456789L, "Name2", "Address1",
                new ArrayList<>(List.of(
                        new Employee(0, "EmpName1", "Janitor", 500, LocalDateTime.now()),
                        new Employee(1, "EmpName2", "Security guard", 600, LocalDateTime.now())))));
        companies.put(1L, new Company(1L, 987654321L, "Name2", "Address2",
                new ArrayList<>(List.of(
                        new Employee(0, "EmpName3", "Assistant", 700, LocalDateTime.now()),
                        new Employee(1, "EmpName4", "Vice president", 800, LocalDateTime.now())))));
    }

    public Optional<Company> save(Company company){
        if (!companies.containsKey(company.getId())) {
            companies.put(company.getId(), company);
            return Optional.of(company);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Company> edit(Company company, long id){
        if (!companies.containsKey(company.getId())) {
            return Optional.empty();
        } else {
            company.setId(id);
            companies.put(company.getId(), company);
            return Optional.of(company);
        }
    }



    public List<Company> findAll(){
        return new ArrayList<>(companies.values());
    }

    public Optional<Company> findById(long id){
        return Optional.ofNullable(companies.get(id));
    }

    public void delete(long id){
        companies.remove(id);
    }

    public Optional<Employee> addEmployeeToCompany(long id, Employee employee){
        if(!companies.containsKey(id)){
            return Optional.empty();
        } else {
            companies.get(id).getEmployees().add(employee);
            return Optional.of(employee);
        }
    }

    public void deleteEmployeeFromCompany(long compId,long empId){
        companies.get(compId).getEmployees().removeIf(n -> n.getId() == empId);
    }

    public Optional<List<Employee>> replaceEmployees(long compId, List<Employee> employees){

        if (!companies.containsKey(compId)) {
            return Optional.empty();
        } else {
            companies.get(compId).setEmployees(employees);
            return Optional.of(employees);
        }

    }

}
