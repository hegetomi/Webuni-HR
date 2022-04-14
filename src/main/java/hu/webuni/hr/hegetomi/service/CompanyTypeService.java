package hu.webuni.hr.hegetomi.service;

import hu.webuni.hr.hegetomi.mapper.CompanyTypeMapper;
import hu.webuni.hr.hegetomi.model.CompanyType;
import hu.webuni.hr.hegetomi.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyTypeService {

    @Autowired
    CompanyTypeRepository companyTypeRepository;


    public CompanyType save(CompanyType type) {

        Optional<CompanyType> existingType = companyTypeRepository.findByType(type.getType());
        if (existingType.isEmpty()) {
            return companyTypeRepository.save(type);
        } else return existingType.get();
    }

    public List<CompanyType> findAll() {
        return companyTypeRepository.findAll();
    }

}
