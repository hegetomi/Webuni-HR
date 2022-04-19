package hu.webuni.hr.hegetomi.dto;

public class PositionDto {


    private long id;
    private String name;
    private String minimumFormation;

    public PositionDto() {
    }

    public PositionDto(long id, String name, String minimumFormation) {
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
