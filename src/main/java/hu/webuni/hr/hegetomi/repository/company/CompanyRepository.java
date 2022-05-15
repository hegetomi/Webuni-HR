package hu.webuni.hr.hegetomi.repository.company;

import hu.webuni.hr.hegetomi.dto.ResultPair;
import hu.webuni.hr.hegetomi.model.company.Company;
import org.springframework.data.jpa.repository.*;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {

    @Query("select distinct c from Company c inner join c.employees e where e.salary>:amount")
    List<Company> findByEmployeeSalaryGreaterThan(Long amount);

    @Query("select c from Company c where size(c.employees) > :amount")
    List<Company> findByEmployeesMoreThan(int amount);

    @Query(value = "select new hu.webuni.hr.hegetomi.dto.ResultPair(t.name, avg(e.salary)) from Employee e inner join e.title t group by t")
    List<ResultPair<String,Double>> getTitlesAverageSalary();



    //multiplebags
    @EntityGraph(attributePaths = {"employees","employees.title","type"})
    @Query("SELECT c from Company c left join fetch c.employees e")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<Company> findAllWithEmployees();

    @Query("SELECT distinct c from Company c left join fetch c.availablePositions ap")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<Company> findAllWithPositions();

    //multiplebags
    @EntityGraph(attributePaths = {"employees","employees.title","type"})
    @Query("SELECT distinct c from Company c left join fetch c.employees e where c.id = :id")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    Optional<Company> findByIdForEmployees(long id);

    @Query("SELECT distinct c from Company c left join fetch c.availablePositions ap where c.id = :id")
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
    Optional<Company> findByIdForPositions(long id);


}
