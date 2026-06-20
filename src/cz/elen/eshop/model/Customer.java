package cz.elen.eshop.model;

public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Customer(Long id,
                    String firstName,
                    String lastName,
                    String email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}