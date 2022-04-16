package hu.webuni.hr.hegetomi.mapper;

import hu.webuni.hr.hegetomi.dto.company.CompanyTypeDto;
import hu.webuni.hr.hegetomi.model.company.CompanyType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyTypeMapper {

    List<CompanyTypeDto> companiesToDtos(List<CompanyType> companies);

    CompanyTypeDto companyToDto(CompanyType company);

    CompanyType dtoToCompany(CompanyTypeDto dto);

}
