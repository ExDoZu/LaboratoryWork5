package zuev.nikita;

public class Address implements Comparable<Address>{
    private String zipCode; //Поле может быть null

    Address(String postalAddress){
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
        return zipCode.compareTo(o.getZipCode());
    }
}