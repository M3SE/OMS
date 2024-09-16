# Order Management System (OMS)

## Overview
The Order Management System (OMS) is a Spring Boot application that manages orders and products. It provides CRUD operations for both orders and products.

## Prerequisites
- Java 11 or higher
- Maven 3.6.0 or higher

## Building the Project
To build the project, navigate to the root directory of the project and run the following command:
```
mvn clean install
```


## Running the Application
To run the application, use the following command:
```mvn spring-boot:run```


## Running the Tests
To run the tests, use the following command:
```sh
mvn test
```

## Test Cases

### OrderServiceTest
- **createOrder**: Tests the creation of an order.
- **getOrderById**: Tests retrieving an order by its ID.
- **getAllOrders**: Tests retrieving all orders.
- **updateOrder**: Tests updating an existing order.
- **deleteOrder**: Tests deleting an order by its ID.

### ProductServiceTest
- **createProduct**: Tests the creation of a product.
- **getProductById**: Tests retrieving a product by its ID.
- **getAllProducts**: Tests retrieving all products.
- **updateProduct**: Tests updating an existing product.
- **deleteProduct**: Tests deleting a product by its ID.

## Test Results
All test cases are executed using JUnit and Mockito. The tests validate the CRUD functionalities of the `OrderService` and `ProductService` classes. The expected results are verified using assertions.

To view the test results, check the output of the `mvn test` command. The results will indicate whether the tests passed or failed, along with any error messages or stack traces for failed tests.
