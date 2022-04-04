package zuev.nikita.Structure;

/**
 * Адрес. Часть структуры Organization.
 */
public class Address implements Comparable<Address> {
    /**
     * Поле может быть null
     */
    private String zipCode;

    public Address(String postalAddress) {
        this.zipCode = postalAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public int compareTo(Address o) {
        if (zipCode == null && o.getZipCode() != null) return -1;
        else if (zipCode != null && o.getZipCode() == null) return 1;
        else if (zipCode == null && o.getZipCode() == null) return 0;
        else return zipCode.compareTo(o.getZipCode());
    }

    @Override
    public String toString() {
        return "Address: " + zipCode;
    }
}