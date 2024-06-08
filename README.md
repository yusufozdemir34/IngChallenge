
# Stock Exchange Management Application

## Overview

The Stock Exchange Management Application is a Java-based backend application using the Spring framework that manages stock exchanges and stocks. The application provides RESTful APIs to create, read, update, and delete stock exchanges and stocks. The application uses an in-memory H2 database for data storage and can handle multiple users simultaneously.

## Features

- List all stocks in a stock exchange
- Create a new stock
- Add a stock to a stock exchange
- Remove a stock from a stock exchange
- Update the price of a stock
- Delete a stock from the system
- Authorization for API endpoints (optional)

## Setup Instructions

### Prerequisites

- Java 17 or higher
- Maven
- Git

### Installation

1. **Clone the Repository**:
   ```sh
   git clone https://github.com/yusufozdemir34/IngChallenge.git
   
   

2. **Build the Project**:
   ```sh
   mvn clean install
   ```

3. **Run the Application**:
   ```sh
   mvn spring-boot:run
   ```

### Configuration

Ensure the following properties are set in `src/main/resources/application.properties`:

```properties
# H2 Console configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

## Accessing the H2 Console

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- User Name: `sa`
- Password: `password`

## API Endpoints

### 1. List all stocks in a stock exchange
- **URL**: `/api/v1/stock-exchange/{name}`
- **Method**: `GET`
- **Description**: Retrieve details of a specific stock exchange including its stocks.
- **Response**:
  ```json
  {
    "id": 1,
    "name": "NYSE",
    "description": "New York Stock Exchange",
    "liveInMarket": true,
    "stocks": [
      {
        "id": 1,
        "name": "Stock1",
        "description": "Description for Stock 1",
        "currentPrice": 101,
        "lastUpdate": "2024-06-06T00:00:00.000+00:00"
      },
      ...
    ]
  }
  ```

### 2. Create a new stock
- **URL**: `/api/v1/stock`
- **Method**: `POST`
- **Description**: Create a new stock.
- **Request Body**:
  ```json
  {
    "name": "Stock6",
    "description": "Description for Stock 6",
    "currentPrice": 106,
    "lastUpdate": "2024-06-06T00:00:00.000+00:00"
  }
  ```
- **Response**:
  ```json
  {
    "id": 6,
    "name": "Stock6",
    "description": "Description for Stock 6",
    "currentPrice": 106,
    "lastUpdate": "2024-06-06T00:00:00.000+00:00"
  }
  ```

### 3. Add a stock to a stock exchange
- **URL**: `/api/v1/stock-exchange/{name}/add-stock`
- **Method**: `POST`
- **Description**: Add an existing stock to a stock exchange.
- **Request Body**:
  ```json
  {
    "id": 1
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "name": "NYSE",
    "description": "New York Stock Exchange",
    "liveInMarket": true,
    "stocks": [
      ...
    ]
  }
  ```

### 4. Remove a stock from a stock exchange
- **URL**: `/api/v1/stock-exchange/{name}/remove-stock`
- **Method**: `DELETE`
- **Description**: Remove a stock from a stock exchange.
- **Request Body**:
  ```json
  {
    "id": 1
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "name": "NYSE",
    "description": "New York Stock Exchange",
    "liveInMarket": false,
    "stocks": [
      ...
    ]
  }
  ```

### 5. Update the price of a stock
- **URL**: `/api/v1/stock/{id}`
- **Method**: `PUT`
- **Description**: Update the price of an existing stock.
- **Request Body**:
  ```json
  {
    "currentPrice": 110
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Stock1",
    "description": "Description for Stock 1",
    "currentPrice": 110,
    "lastUpdate": "2024-06-06T00:00:00.000+00:00"
  }
  ```

### 6. Delete a stock from the system
- **URL**: `/api/v1/stock/{id}`
- **Method**: `DELETE`
- **Description**: Delete an existing stock from the system.
- **Response**: `204 No Content`

## Sample Data

Upon starting the application, the database is pre-populated with the following stock exchanges and stocks:

### Stock Exchanges
- NYSE (New York Stock Exchange)
- NASDAQ (NASDAQ Stock Market)
- LSE (London Stock Exchange)
- JPX (Japan Exchange Group)
- SSE (Shanghai Stock Exchange)

### Stocks
- Stock1
- Stock2
- Stock3
- Stock4
- Stock5

Each stock exchange has all 5 stocks initially.

## Usage Examples

### Create a Stock Exchange

```sh
curl -X POST -H "Content-Type: application/json" -d '{"name":"TSX","description":"Toronto Stock Exchange","liveInMarket":false}' http://localhost:8080/api/v1/stock-exchange
```

### Add a Stock to a Stock Exchange

```sh
curl -X POST -H "Content-Type: application/json" -d '{"id":1}' http://localhost:8080/api/v1/stock-exchange/NYSE/add-stock
```

### Remove a Stock from a Stock Exchange

```sh
curl -X DELETE -H "Content-Type: application/json" -d '{"id":1}' http://localhost:8080/api/v1/stock-exchange/NYSE/remove-stock
```

### Update Stock Price

```sh
curl -X PUT -H "Content-Type: application/json" -d '{"currentPrice":150}' http://localhost:8080/api/v1/stock/1
```

### Delete a Stock

```sh
curl -X DELETE http://localhost:8080/api/v1/stock/1
```

## Conclusion

This documentation provides a comprehensive guide for setting up, running, and using the Stock Exchange Management Application. Ensure to follow the setup instructions and use the provided endpoints to interact with the application. If you have any issues, refer to the console logs for debugging information.
