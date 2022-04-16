package hu.webuni.hr.hegetomi.model;

import javax.persistence.*;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_seq")
    @SequenceGenerator(name = "position_seq", allocationSize = 1)
    private long id;
    private String name;
    private String minimumFormation;

    public Position() {
    }

    public Position(long id, String name, String minimumFormation) {
        this.id = id;
        this.name = name;
        this.minimumFormation = minimumFormation;
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

    public String getMinimumFormation() {
        return minimumFormation;
    }

    public void setMinimumFormation(String minimumFormation) {
        this.minimumFormation = minimumFormation;
    }

}
