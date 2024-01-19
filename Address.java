import java.io.Serializable;

public class Address implements Cloneable, Comparable<Address>, Serializable {
    private int streetNum;
    private String street;
    private String suburb;
    private String city;

    Address(int streetNum, String street, String suburb, String city) {
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void print() {
        System.out.println("Address:" + streetNum + " " + street + ", " + suburb + ", " + city);
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public String getSuburb() {
        return suburb;
    }
    
    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String toString() {
        return "Street Number:" + streetNum + " Street:" + street + " Suburb:" + suburb + " City:" + city;
    }

    //---------------------------------------------Lab_04--------------------------------------------------

    public Address(Address address) {
        this.streetNum = address.streetNum;
        this.street = address.street;
        this.suburb = address.suburb;
        this.city = address.city;
    }

    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

    @Override
    public int compareTo(Address other) {
        if (this.city.equals(other.city)) {
            return 0;
        } else {
            return 1;
        }
    }

//    -------------------------------------------------lab 6 ---------------------------

    public String toDelimitedString() {
        return  streetNum + "," + street + "," + suburb + "," + city;
    }
}
