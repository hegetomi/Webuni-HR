package hu.webuni.hr.hegetomi.model.company;

import javax.persistence.*;

@Entity
public class CompanyType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comp_type_seq")
    @SequenceGenerator(name = "comp_type_seq", allocationSize = 1)
    private long id;
    private String name;

    public CompanyType() {
    }

    public CompanyType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String type) {
        this.name = type;
    }

    public void setId(long id) {
        this.id = id;
    }



    public long getId() {
        return id;
    }
}
