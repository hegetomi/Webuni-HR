package hu.webuni.hr.hegetomi.repository;

import hu.webuni.hr.hegetomi.model.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {

    Optional<CompanyType> findByType(String type);


}
