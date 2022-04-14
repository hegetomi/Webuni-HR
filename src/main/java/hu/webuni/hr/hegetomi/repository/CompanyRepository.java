package hu.webuni.hr.hegetomi.repository;

import hu.webuni.hr.hegetomi.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select distinct c from Company c inner join c.employees e where e.salary>:amount")
    List<Company> findByEmployeeSalaryGreaterThan(Long amount);

    @Query("select c from Company c inner join c.employees e group by c having count(e.worksAt) > :amount")
    List<Company> findByEmployeesMoreThan(Long amount);

    @Query(value = "select e.title, FLOOR(AVG(e.salary)) as average " +
            "from Company c " +
            "inner join Employee e on e.company_id = c.id " +
            "group by e.title " +
            "order by average desc"
    ,nativeQuery = true)
    List<Object[]> getTitlesAverageSalary();
}
