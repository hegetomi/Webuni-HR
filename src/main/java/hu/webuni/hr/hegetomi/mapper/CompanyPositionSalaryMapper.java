package hu.webuni.hr.hegetomi.mapper;

import hu.webuni.hr.hegetomi.dto.company.CompanyPositionSalaryDto;
import hu.webuni.hr.hegetomi.model.company.CompanyPositionSalary;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyPositionSalaryMapper {

    //TODO filter only company's employee / other nested objects, not the whole company

    @IterableMapping(qualifiedByName = "mapWithoutCompany")
    List<CompanyPositionSalaryDto> companyPositionsToDtos(List<CompanyPositionSalary> companies);

    @Named("mapWithoutCompany")
    @Mapping(target = "company", ignore = true)
    CompanyPositionSalaryDto companyPositionsToDto(CompanyPositionSalary company);


    CompanyPositionSalary dtoToCompanyPosition(CompanyPositionSalaryDto dto);

}
