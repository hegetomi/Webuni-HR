package hu.webuni.hr.hegetomi.service.employee;

import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.model.Employee_;
import hu.webuni.hr.hegetomi.model.Position_;
import hu.webuni.hr.hegetomi.model.company.Company;
import hu.webuni.hr.hegetomi.model.company.Company_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

public class EmployeeSpecification {

    public static Specification<Employee> hasId(long id) {
        return (root, cq, cb) -> cb.equal(root.get(Employee_.id), id);
    }

    public static Specification<Employee> hasName(String name) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Employee_.name)), (name + "%").toLowerCase());
    }

    public static Specification<Employee> hasPosition(String name) {
        return (root, cq, cb) -> cb.equal(root.get(Employee_.title).get(Position_.name), name);
    }

    public static Specification<Employee> hasSalary(long salary) {
        return (root, cq, cb) -> cb.between(root.get(Employee_.salary), (long) (salary * 0.95), (long) (salary * 1.05));
    }

    public static Specification<Employee> hasEmpSince(LocalDateTime localDateTime) {
        LocalDateTime finalLocalDateTime = LocalDateTime.of(localDateTime.toLocalDate(),LocalTime.of(0,0));
        return (root, cq, cb) -> cb.between(root.get(Employee_.empSince),
                finalLocalDateTime,finalLocalDateTime.plusDays(1));
    }

    public static Specification<Employee> hasWorksAt(String worksAt) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Employee_.worksAt).get(Company_.name)),(worksAt + "%").toLowerCase());

    }
}
