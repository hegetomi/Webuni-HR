package hu.webuni.hr.hegetomi.repository.company;

import hu.webuni.hr.hegetomi.model.company.CompanyPositionSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyPositionSalaryRepository extends JpaRepository<CompanyPositionSalary, Long> {
    @Transactional
    void deleteAllByCompanyId(long id);

    CompanyPositionSalary findByCompanyIdAndPositionId(long companyId, long positionId);

    CompanyPositionSalary findByPositionId(long positionId);

    @Query("SELECT e FROM CompanyPositionSalary e join e.position p WHERE p.name = :name")
    List<CompanyPositionSalary> findByPositionName(String name);

    @Query("SELECT e FROM CompanyPositionSalary e " +
            "join e.position p " +
            "join e.company c " +
            "WHERE p.name = :name AND c.id = :companyId")
    CompanyPositionSalary findByPositionNameAtCompany(String name, long companyId);

}
