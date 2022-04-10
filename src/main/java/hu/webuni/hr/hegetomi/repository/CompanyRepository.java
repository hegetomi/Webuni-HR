package hu.webuni.hr.hegetomi.repository;

import hu.webuni.hr.hegetomi.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
