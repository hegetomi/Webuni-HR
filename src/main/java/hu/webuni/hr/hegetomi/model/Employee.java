package hu.webuni.hr.hegetomi.model;

import java.time.LocalDateTime;

public class Employee {

    private long id;
    private String name;
    private String title;
    private long salary;
    private LocalDateTime empSince;

    public Employee(long id, String name, String title, long salary, LocalDateTime empSince) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.salary = salary;
        this.empSince = empSince;
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
