package hu.webuni.hr.hegetomi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class EmployeeDto {

    private long id;

    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @NotEmpty(message = "Title cannot be null or empty")
    private String title;

    @Positive
    private long salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Past
    private LocalDateTime empSince;

    public EmployeeDto(long id, String name, String title, long salary, LocalDateTime empSince) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.salary = salary;
        this.empSince = empSince;
    }
    public EmployeeDto(){

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
}
