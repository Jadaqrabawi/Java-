
class Project {
    private String customer;
    private String projectName;
    private Address projectAddress;
    private Address billingAddress;
    private double amount;

    public Project(String customer, String projectName, Address projectAddress, Address billingAddress, double amount) {
        this.projectName = projectName;
        this.projectAddress = projectAddress;
        this.billingAddress = billingAddress;
        this.amount = amount;
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public String getProjectName() {
        return projectName;
    }

    public Address getProjectAddress() {
        return projectAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Project Name: " + projectName + ", Project Address: " + projectAddress + ", Billing Address: " + billingAddress + ", Amount: " + amount;
    }
}