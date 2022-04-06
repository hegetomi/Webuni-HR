package hu.webuni.hr.hegetomi.mapper;

import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import hu.webuni.hr.hegetomi.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeDto> employeesToDtos(List<Employee> employees);
    EmployeeDto employeeToDto(Employee employee);

    Employee dtoToEmployee(EmployeeDto dto);
    List<Employee> DtosToEmployees(List<EmployeeDto> dtos);

}
