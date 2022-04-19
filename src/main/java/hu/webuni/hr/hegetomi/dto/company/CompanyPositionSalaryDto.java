package hu.webuni.hr.hegetomi.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.webuni.hr.hegetomi.model.Position;
import hu.webuni.hr.hegetomi.model.company.Company;

import javax.persistence.*;

public class CompanyPositionSalaryDto {

    long id;


    @JsonIgnore
    Company company;

    Position position;

    long minimumSalary;

    public CompanyPositionSalaryDto() {
    }

    public CompanyPositionSalaryDto(long id, Company company, Position position, long minimumSalary) {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public long getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(long salary) {
        this.minimumSalary = salary;
    }

}
