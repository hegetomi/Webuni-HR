package hu.webuni.hr.hegetomi.model.company;

import hu.webuni.hr.hegetomi.model.Position;
import hu.webuni.hr.hegetomi.model.company.Company;

import javax.persistence.*;

@Entity
public class CompanyPositionSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_salary_seq")
    @SequenceGenerator(name = "company_salary_seq", allocationSize = 1)
    long id;

    @ManyToOne()
    Company company;
    @ManyToOne()
    Position position;

    long minimumSalary;

    public CompanyPositionSalary() {
    }

    public CompanyPositionSalary(long id, Company company, Position position, long minimumSalary) {
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
