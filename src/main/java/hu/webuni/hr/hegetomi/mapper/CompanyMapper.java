package hu.webuni.hr.hegetomi.mapper;

import hu.webuni.hr.hegetomi.dto.company.CompanyDto;
import hu.webuni.hr.hegetomi.dto.company.TruncatedCompanyDto;
import hu.webuni.hr.hegetomi.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    List<CompanyDto> companiesToDtos(List<Company> airports);
    CompanyDto companyToDto(Company airport);

    Company dtoToCompany(CompanyDto dto);

    List<TruncatedCompanyDto> companiesToTruncatedDtos(List<Company> airports);



}
