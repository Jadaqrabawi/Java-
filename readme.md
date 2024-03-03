# Project Description

This Java project consists of three main classes: Address, Customer, and Project, along with a main class containing the main method.

## Class Descriptions:

### Address:
The Address class encapsulates address-related information. It includes the following attributes:
- String street
- String city
- String zip
- String state

This class provides getters for retrieving the address components.

### Customer:
The Customer class represents a customer entity. It contains the following attributes:
- String name
- String phone
- String email
- Address address

This class provides getters for accessing customer information.

### Project:
The Project class models a project within the system. It includes the following attributes:
- String customer
- String projectName
- Address projectAddress
- Address billingAddress
- double amount

This class provides getters for obtaining project details.

## Functionality:

1. #Customer Management:
   - You can create and store multiple customers, saving their information to a text file for future reference.

2. **Adding Projects:**
   - You have the ability to add projects to specific customers. This involves selecting a customer and providing project details such as billing address and amount. Multiple projects can be associated with a single customer.

3. **Viewing Billing Details:**
   - You can view the billing details of a specific customer, allowing you to see the projects associated with them along with their respective billing information.

This project aims to provide a streamlined approach to managing customer information, adding projects, and viewing billing details for effective project management and customer relationship management.