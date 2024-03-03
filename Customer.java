
class Customer {
    private String name;
    private String phone;
    private String email;
    private Address address;

    public Customer(String name, String phone, String email, Address address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email + ", Address: " + address;
    }
}
