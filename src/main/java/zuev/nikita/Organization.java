package zuev.nikita;

import java.util.Date;

public class Organization implements Comparable<Organization> {
    Organization(Integer id, String name, Coordinates coordinates, Date creationDate, Double annualTurnover, OrganizationType type, Address postalAddress) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.annualTurnover = annualTurnover;
        this.creationDate = creationDate;
        this.type = type;
        this.postalAddress = postalAddress;
    }

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле не может быть null

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    public Double getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Double annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    @Override
    public int compareTo(Organization o) {
        int compare = id.compareTo(o.getId());
        if (compare == 0) {
            compare = name.compareTo(o.getName());
            if (compare == 0) {
                compare = coordinates.compareTo(o.getCoordinates());
                if (compare == 0) {
                    compare = creationDate.compareTo(o.getCreationDate());
                    if (compare == 0) {
                        compare = annualTurnover.compareTo(o.getAnnualTurnover());
                        if (compare == 0) {
                            compare = postalAddress.compareTo(o.getPostalAddress());
                            if (compare == 0) {
                                compare = type.compareTo(o.getType());
                            }
                        }
                    }
                }
            }
        }
        return compare;
    }
}