package hu.webuni.hr.hegetomi.dto.company;

import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import hu.webuni.hr.hegetomi.model.company.CompanyPositionSalary;
import hu.webuni.hr.hegetomi.validation.CompanyTypeValidation;

import javax.validation.Valid;
import java.util.List;

public class CompanyDto {

    private long id;
    private long registrationNumber;
    private String name;
    private String address;
    @CompanyTypeValidation
    private String type;
    @Valid
    private List<EmployeeDto> employees;

    private List<CompanyPositionSalaryDto> availablePositions;


    public CompanyDto(long id, long registrationNumber, String name, String address, String type, List<EmployeeDto> employees, List<CompanyPositionSalaryDto> availablePositions) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
        this.type = type;
        this.employees = employees;
        this.availablePositions = availablePositions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CompanyDto() {
    }

    public long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }


    public List<CompanyPositionSalaryDto> getAvailablePositions() {
        return availablePositions;
    }





    public void setAvailablePositions(List<CompanyPositionSalaryDto> availablePositions) {
        this.availablePositions = availablePositions;
    }
}
