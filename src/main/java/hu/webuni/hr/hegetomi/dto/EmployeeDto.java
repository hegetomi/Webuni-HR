package hu.webuni.hr.hegetomi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.webuni.hr.hegetomi.dto.company.CompanyDto;
import hu.webuni.hr.hegetomi.model.company.Company;
import hu.webuni.hr.hegetomi.model.Position;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class EmployeeDto {

    private long id;

    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @NotNull(message = "Title cannot be null")
    private PositionDto title;

    @Positive
    private long salary;

    private CompanyDto worksAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Past
    private LocalDateTime empSince;

    public EmployeeDto(long id, String name, PositionDto title, long salary, CompanyDto worksAt, LocalDateTime empSince) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.salary = salary;
        this.worksAt = worksAt;
        this.empSince = empSince;
    }

    public EmployeeDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionDto getTitle() {
        return title;
    }

    public void setTitle(PositionDto title) {
        this.title = title;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public LocalDateTime getEmpSince() {
        return empSince;
    }

    public void setEmpSince(LocalDateTime empSince) {
        this.empSince = empSince;
    }

    public void setWorksAt(CompanyDto worksAt) {
        this.worksAt = worksAt;
    }

    public CompanyDto getWorksAt() {
        return worksAt;
    }
}
