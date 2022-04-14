package hu.webuni.hr.hegetomi.model;

import javax.persistence.*;

@Entity
public class CompanyType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comp_type_seq")
    @SequenceGenerator(name = "comp_type_seq", allocationSize = 1)
    private long id;
    private String type;

    public CompanyType() {
    }

    public CompanyType(long id, String type) {
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
