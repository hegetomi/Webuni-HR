package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.dto.company.CompanyTypeDto;
import hu.webuni.hr.hegetomi.mapper.CompanyTypeMapper;
import hu.webuni.hr.hegetomi.model.company.CompanyType;
import hu.webuni.hr.hegetomi.service.company.CompanyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies/types")
public class CompanyTypeController {


    @Autowired
    CompanyTypeService companyTypeService;

    @Autowired
    CompanyTypeMapper companyTypeMapper;

    @PostMapping("/new")
    public Map<String, String> addNewType(@RequestBody CompanyTypeDto type) {

        CompanyTypeDto savedDto = companyTypeMapper
                .companyToDto(companyTypeService.save(companyTypeMapper.dtoToCompany(type)));
        return Map.of("type", savedDto.getName());
    }

    @GetMapping("/get")
    public List<CompanyType> getTypes() {
        return companyTypeService.findAll();
    }


}
