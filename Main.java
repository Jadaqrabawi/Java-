import java.io.*;
import java.util.*;

public class Main {
    private static final String CUSTOMER_FILE = "customer_info.txt";
    private static final String PROJECT_FILE = "project_details.txt";

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Customer> customers = readCustomerData();
        List<Project> projects = readProjectData();

        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Add new customer");
            System.out.println("2. Add Project");
            System.out.println("3. Bill customer");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewCustomer(customers);
                    break;
                case 2:
                    addProject(customers, projects);
                    break;
                case 3:
                    billCustomer(customers, projects);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static List<Customer> readCustomerData() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Address address = new Address(parts[1], parts[2], parts[3], parts[4]); // Assuming index 0 is customer name
                Customer customer = new Customer(parts[0], parts[5], parts[6], address);
                customers.add(customer);
            }
        } catch (IOException e) {
            System.err.println("Error reading customer data: " + e.getMessage());
        }
        return customers;
    }
    private static List<Project> readProjectData() {
        List<Project> projects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROJECT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Address projectAddress = new Address(parts[2], parts[3], parts[4], parts[5]);
                Address billingAddress = new Address(parts[6], parts[7], parts[8], parts[9]);
                Project project = new Project(parts[0], parts[1], projectAddress, billingAddress, Double.parseDouble(parts[10]));
                projects.add(project);
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error reading project data: " + e.getMessage());
        }
        return projects;
    }
    

    private static void addNewCustomer(List<Customer> customers) {
        System.out.println("Enter customer details:");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Street: ");
        String street = scanner.nextLine().trim();
        System.out.print("City: ");
        String city = scanner.nextLine().trim();
        System.out.print("Zip: ");
        String zip = scanner.nextLine().trim();
        System.out.print("State: ");
        String state = scanner.nextLine().trim();

        Address address = new Address(street, city, zip, state);
        Customer customer = new Customer(name, phone, email, address);
        customers.add(customer);

        writeCustomerData(customers);
    }

    private static void billCustomer(List<Customer> customers, List<Project> projects) {
        System.out.println("Choose a customer:");
        listCustomers(customers);
        System.out.print("Enter customer index: ");
        int customerIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        if (customerIndex >= 0 && customerIndex < customers.size()) {
            Customer customer = customers.get(customerIndex);
            System.out.println("Bill details for customer: " + customer.getName());
            boolean found = false;
            for (Project project : projects) {
                if (project.getCustomer() != null && project.getCustomer().equals(customer.getName())) {
                    System.out.println(project);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No projects found for billing to this customer.");
            }
        } else {
            System.out.println("Invalid customer index.");
        }
    }
    
    

    private static void listCustomers(List<Customer> customers) {
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(i + ". " + customers.get(i).getName());
        }
    }

    private static void writeCustomerData(List<Customer> customers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer customer : customers) {
                writer.println(customer.getName() + "," +
                        customer.getAddress().getStreet() + "," +
                        customer.getAddress().getCity() + "," +
                        customer.getAddress().getZip() + "," +
                        customer.getAddress().getState() + "," +
                        customer.getPhone() + "," +
                        customer.getEmail());
            }
        } catch (IOException e) {
            System.err.println("Error writing customer data: " + e.getMessage());
        }
    }

    private static void addProject(List<Customer> customers, List<Project> projects) {
        System.out.println("Choose a customer:");
        listCustomers(customers);
        System.out.print("Enter customer index: ");
        int customerIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        if (customerIndex >= 0 && customerIndex < customers.size()) {
            Customer customer = customers.get(customerIndex);
            System.out.println("Enter project details:");
    
            // Prompt for project details
            System.out.print("Project Name: ");
            String projectName = scanner.nextLine().trim();
            System.out.print("Project Street: ");
            String projectStreet = scanner.nextLine().trim();
            System.out.print("Project City: ");
            String projectCity = scanner.nextLine().trim();
            System.out.print("Project Zip: ");
            String projectZip = scanner.nextLine().trim();
            System.out.print("Project State: ");
            String projectState = scanner.nextLine().trim();
            System.out.print("Billing Street: ");
            String billingStreet = scanner.nextLine().trim();
            System.out.print("Billing City: ");
            String billingCity = scanner.nextLine().trim();
            System.out.print("Billing Zip: ");
            String billingZip = scanner.nextLine().trim();
            System.out.print("Billing State: ");
            String billingState = scanner.nextLine().trim();
    
            // Input validation for amount
            double amount = 0;
            boolean validAmount = false;
            while (!validAmount) {
                System.out.print("Amount: ");
                String amountInput = scanner.nextLine().trim();
                try {
                    amount = Double.parseDouble(amountInput);
                    validAmount = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount. Please enter a valid number.");
                }
            }
    
            // Create project address and billing address objects
            Address projectAddress = new Address(projectStreet, projectCity, projectZip, projectState);
            Address billingAddress = new Address(billingStreet, billingCity, billingZip, billingState);
    
            // Create and add project to the list
            Project project = new Project(customer.getName(), projectName, projectAddress, billingAddress, amount);
            projects.add(project);
    
            // Write project data to file
            writeProjectData(projects);
        } else {
            System.out.println("Invalid customer index.");
        }
    }
     
    private static void writeProjectData(List<Project> projects) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PROJECT_FILE))) {
            for (Project project : projects) {
                writer.println(project.getCustomer() + "," +
                               project.getProjectName() + "," +
                               project.getProjectAddress().getStreet() + "," +
                               project.getProjectAddress().getCity() + "," +
                               project.getProjectAddress().getZip() + "," +
                               project.getProjectAddress().getState() + "," +
                               project.getBillingAddress().getStreet() + "," +
                               project.getBillingAddress().getCity() + "," +
                               project.getBillingAddress().getZip() + "," +
                               project.getBillingAddress().getState() + "," +
                               project.getAmount());
            }
        } catch (IOException e) {
            System.err.println("Error writing project data: " + e.getMessage());
        }
    }
    
}
