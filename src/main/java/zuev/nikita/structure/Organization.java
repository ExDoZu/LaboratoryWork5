package zuev.nikita.structure;

import java.util.Date;

/**
 * Data storage structure
 */
public class Organization implements Comparable<Organization> {
    public Organization(Integer id, String name, Coordinates coordinates, Date creationDate, Double annualTurnover, OrganizationType type, Address postalAddress) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.annualTurnover = annualTurnover;
        this.creationDate = creationDate;
        this.type = type;
        this.postalAddress = postalAddress;
    }

    /**
     * The field cannot be null, The field value must be greater than 0, The value of this field must be unique, The value of this field must be generated automatically
     */
    private Integer id;
    /**
     * The field cannot be null, String cannot be empty
     */
    private String name;
    /**
     * The field cannot be null
     */
    private Coordinates coordinates;
    /**
     * The field cannot be null, the value of this field must be generated automatically
     */
    private Date creationDate;
    /**
     * The field can be null, the field value must be greater than 0
     */
    private Double annualTurnover;
    /**
     * The field cannot be null
     */
    private OrganizationType type;
    /**
     * The field cannot be null
     */
    private Address postalAddress;

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
        int compare = name.compareTo(o.getName());
        if (compare == 0) {
            compare = coordinates.compareTo(o.getCoordinates());
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
        return compare;
    }

    @Override
    public String toString() {
        return "-- id:\t\t\t" + id + '\n' +
                "-- name:\t\t" + name + '\n' +
                "-- coordinates:\n" +
                "-- -- x:\t\t" + coordinates.getX() + '\n' +
                "-- -- y:\t\t" + coordinates.getY() + '\n' +
                "-- creationDate:\t" + creationDate + '\n' +
                "-- annualTurnover:\t" + annualTurnover + '\n' +
                "-- type:\t\t" + type + '\n' +
                "-- postalAddress:\t" + postalAddress.getZipCode();
    }
}