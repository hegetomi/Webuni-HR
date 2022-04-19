package hu.webuni.hr.hegetomi.model.company;

import hu.webuni.hr.hegetomi.model.Employee;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @ManyToOne
    private CompanyType type;

    @OneToMany(mappedBy = "worksAt")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Employee> employees;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CompanyPositionSalary> availablePositions;


    public Company(long id, long registrationNumber, String name, String address, CompanyType type, List<Employee> employees, List<CompanyPositionSalary> availablePositions) {
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

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
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


    public List<CompanyPositionSalary> getAvailablePositions() {
        return availablePositions;
    }

    public void setAvailablePositions(List<CompanyPositionSalary> availablePositions) {
        this.availablePositions = availablePositions;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", registrationNumber=" + registrationNumber +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", employees=" + employees +
                '}';
    }
}