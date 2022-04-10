package hu.webuni.hr.hegetomi.mapper;

import hu.webuni.hr.hegetomi.dto.company.CompanyDto;
import hu.webuni.hr.hegetomi.model.Company;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    List<CompanyDto> companiesToDtos(List<Company> companies);

    CompanyDto companyToDto(Company company);

    Company dtoToCompany(CompanyDto dto);

    @Named("mapWithoutEmployees")
    @Mapping(target = "employees", ignore = true)
    CompanyDto mapWithoutEmployees(Company company);

    @IterableMapping(qualifiedByName = "mapWithoutEmployees")
    List<CompanyDto> companiesToPartialCompanies(List<Company> companies);


}
