package hu.webuni.hr.hegetomi.repository.company;

import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select distinct c from Company c inner join c.employees e where e.salary>:amount")
    List<Company> findByEmployeeSalaryGreaterThan(Long amount);

    @Query("select c from Company c where size(c.employees) > :amount")
    List<Company> findByEmployeesMoreThan(int amount);

    /* Deprecated
    @Query(value = "select p.name, FLOOR(AVG(e.salary)) as average " +
            "from Company c " +
            "inner join Employee e on e.company_id = c.id " +
            "inner join Position p on p.id = e.position_id " +
            "group by p.name " +
            "order by average desc"
    ,nativeQuery = true)
    List<Object[]> getTitlesAverageSalary2();

 */

    @Query(value = "select t.name as name, avg(e.salary) as salary from Employee e inner join e.title t group by t")
    List<Object[]> getTitlesAverageSalary();
}
