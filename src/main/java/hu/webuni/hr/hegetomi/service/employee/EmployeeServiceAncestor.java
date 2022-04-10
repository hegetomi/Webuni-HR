package hu.webuni.hr.hegetomi.service.employee;

import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class EmployeeServiceAncestor {

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Optional<Employee> edit(Employee employee, long id) {
        if (employeeRepository.existsById(employee.getId())) {
            return Optional.of(employeeRepository.save(employee));
        }
        return Optional.empty();
    }


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> greaterSalaryThan(long amount) {
        return employeeRepository.findBySalaryGreaterThan(amount);
    }

    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    public List<Employee> findByTitle(String title) {
        return employeeRepository.findByTitle(title);
    }

    public List<Employee> findByEmpSinceBetween(LocalDateTime d1, LocalDateTime d2) {
        return employeeRepository.findByEmpSinceBetween(d1, d2);
    }

}
