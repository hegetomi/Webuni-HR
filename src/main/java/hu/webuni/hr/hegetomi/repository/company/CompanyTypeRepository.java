package hu.webuni.hr.hegetomi.repository.company;

import hu.webuni.hr.hegetomi.model.company.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {

    Optional<CompanyType> findByName(String name);


}
