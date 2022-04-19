package hu.webuni.hr.hegetomi.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.webuni.hr.hegetomi.dto.PositionDto;
import hu.webuni.hr.hegetomi.model.Position;
import hu.webuni.hr.hegetomi.model.company.Company;

import javax.persistence.*;

public class CompanyPositionSalaryDto {

    long id;


    @JsonIgnore
    CompanyDto company;

    PositionDto position;

    long minimumSalary;

    public CompanyPositionSalaryDto() {
    }

    public CompanyPositionSalaryDto(long id, CompanyDto company, PositionDto position, long minimumSalary) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.minimumSalary = minimumSalary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public long getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(long salary) {
        this.minimumSalary = salary;
    }

}
