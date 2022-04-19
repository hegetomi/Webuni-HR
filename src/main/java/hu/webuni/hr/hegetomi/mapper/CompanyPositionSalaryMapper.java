package hu.webuni.hr.hegetomi.mapper;

import hu.webuni.hr.hegetomi.dto.company.CompanyPositionSalaryDto;
import hu.webuni.hr.hegetomi.model.company.CompanyPositionSalary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyPositionSalaryMapper {

    List<CompanyPositionSalaryDto> companyPositionsToDtos(List<CompanyPositionSalary> companies);

    @Mapping(target = "company", ignore = true)
    CompanyPositionSalaryDto companyPositionsToDto(CompanyPositionSalary company);

    CompanyPositionSalary dtoToCompanyPosition(CompanyPositionSalaryDto dto);

}
