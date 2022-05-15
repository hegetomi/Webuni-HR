package hu.webuni.hr.hegetomi.mapper;

import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import hu.webuni.hr.hegetomi.dto.company.CompanyDto;
import hu.webuni.hr.hegetomi.dto.company.CompanyPositionSalaryDto;
import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.model.company.Company;
import hu.webuni.hr.hegetomi.model.company.CompanyPositionSalary;
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
    //@Mapping(target = "availablePositions", ignore = true)
    CompanyDto mapWithoutEmployees(Company company);


    @IterableMapping(qualifiedByName = "mapWithoutEmployees")
    List<CompanyDto> companiesToPartialCompanies(List<Company> companies);


    //
    @IterableMapping(qualifiedByName = "mapWithoutWorksAt")
    List<EmployeeDto> employeesToDtos(List<Employee> employees);

    @Named("mapWithoutWorksAt")
    @Mapping(target = "worksAt", ignore = true)
    EmployeeDto employeeToDto(Employee employee);

    //
    @IterableMapping(qualifiedByName = "mapWithoutCompany")
    List<CompanyPositionSalaryDto> companyPositionsToDtos(List<CompanyPositionSalary> companies);

    @Named("mapWithoutCompany")
    @Mapping(target = "company", ignore = true)
    CompanyPositionSalaryDto companyPositionsToDto(CompanyPositionSalary company);




}
