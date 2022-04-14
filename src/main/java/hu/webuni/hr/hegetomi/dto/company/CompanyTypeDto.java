package hu.webuni.hr.hegetomi.dto.company;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class CompanyTypeDto {


    private long id;
    private String type;

    public CompanyTypeDto() {
    }

    public CompanyTypeDto(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
