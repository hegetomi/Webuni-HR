package hu.webuni.hr.hegetomi.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", allocationSize = 1)
    private long id;
    private long registrationNumber;
    private String name;
    private String address;
    @OneToMany(mappedBy = "worksAt", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Company(long id, long registrationNumber, String name, String address, List<Employee> employees) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
        this.employees = employees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Company() {
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
        employee.setWorksAt(this);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
        employee.setWorksAt(null);
    }
}