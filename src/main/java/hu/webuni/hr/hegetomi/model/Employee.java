package hu.webuni.hr.hegetomi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.webuni.hr.hegetomi.model.company.Company;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", allocationSize = 1)
    private long id;
    private String name;
    @ManyToOne(targetEntity = Position.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position title;
    private long salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime empSince;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company worksAt;

    public Employee(long id, String name, Position title, long salary, LocalDateTime empSince) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.salary = salary;
        this.empSince = empSince;
    }

    public Employee() {

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

    public Position getTitle() {
        return title;
    }

    public void setTitle(Position title) {
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

    public Company getWorksAt() {
        return worksAt;
    }

    public void setWorksAt(Company worksAt) {
        this.worksAt = worksAt;
    }





    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                ", empSince=" + empSince +
                '}';
    }
}
