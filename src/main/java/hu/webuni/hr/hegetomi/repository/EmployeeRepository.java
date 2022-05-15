package hu.webuni.hr.hegetomi.repository;

import hu.webuni.hr.hegetomi.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    List<Employee> findBySalaryGreaterThan(Long salary);

    Page<Employee> findByNameContaining(String name, Pageable pageable);

    @Query(value = "SELECT e FROM Employee e JOIN e.title p WHERE p.name like :title")
    List<Employee> findByTitle(String title);

    List<Employee> findByEmpSinceBetween(LocalDateTime d1, LocalDateTime d2);


}
