package hu.webuni.hr.hegetomi.repository;

import hu.webuni.hr.hegetomi.model.Company;
import hu.webuni.hr.hegetomi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySalaryGreaterThan(Long salary);

    List<Employee> findByName(String name);

    List<Employee> findByTitle(String title);

    List<Employee> findByEmpSinceBetween(LocalDateTime d1, LocalDateTime d2);


}
