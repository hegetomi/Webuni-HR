package hu.webuni.hr.hegetomi.service.company;

import hu.webuni.hr.hegetomi.model.company.CompanyPositionSalary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyPositionSalaryService {

    @Transactional
    public void save(CompanyPositionSalary companyPositionSalary){}

}
