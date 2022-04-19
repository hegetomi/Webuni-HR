package hu.webuni.hr.hegetomi.dto.company;

public class CompanyTypeDto {


    private long id;
    private String name;

    public CompanyTypeDto() {
    }

    public CompanyTypeDto(long id, String type) {
        this.id = id;
        this.name = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
