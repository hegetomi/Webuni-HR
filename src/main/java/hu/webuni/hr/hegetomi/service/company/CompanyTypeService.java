package hu.webuni.hr.hegetomi.service.company;

import hu.webuni.hr.hegetomi.model.company.CompanyType;
import hu.webuni.hr.hegetomi.repository.company.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyTypeService {

    @Autowired
    CompanyTypeRepository companyTypeRepository;


    public CompanyType save(CompanyType type) {
        Optional<CompanyType> existingType = companyTypeRepository.findByName(type.getName());
        if (existingType.isEmpty()) {
            return companyTypeRepository.save(type);
        } else return existingType.get();
    }

    public List<CompanyType> findAll() {
        return companyTypeRepository.findAll();
    }

}
