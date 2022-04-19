package hu.webuni.hr.hegetomi.mapper;

import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import hu.webuni.hr.hegetomi.model.Employee;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    //TODO: ignore only company's employee attribute, not the whole company

    @IterableMapping(qualifiedByName = "mapWithoutWorksAt")
    List<EmployeeDto> employeesToDtos(List<Employee> employees);

    @Named("mapWithoutWorksAt")
    @Mapping(target = "worksAt", ignore = true)
    EmployeeDto employeeToDto(Employee employee);

    Employee dtoToEmployee(EmployeeDto dto);
    List<Employee> DtosToEmployees(List<EmployeeDto> dtos);

}
