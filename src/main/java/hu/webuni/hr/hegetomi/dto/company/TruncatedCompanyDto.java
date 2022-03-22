package hu.webuni.hr.hegetomi.dto.company;

import hu.webuni.hr.hegetomi.dto.EmployeeDto;

import java.util.List;

public class TruncatedCompanyDto implements CompanyInterface{

    private long id;
    private long registrationNumber;
    private String name;
    private String address;

    public TruncatedCompanyDto(long id, long registrationNumber, String name, String address) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TruncatedCompanyDto() {
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


}
