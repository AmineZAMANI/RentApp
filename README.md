
# Rentals Application

The Rentals Application is a car rental management system designed to:

- Search for available cars.
- Search for customers.
- Rent a car.
- Return a car.


The application is built using modern software engineering principles, such as Domain-Driven Design (DDD) and domain events, and integrates with Kafka for messaging.

---

## Features

### Core Features

1. **Search Available Cars**: Query available cars based on criteria like car model, and rental dates.
2. **Rent a Car**: Book a car for specific dates, with availability intervals updated dynamically.
3. **Return a Car**: Mark a car as returned and update the rental status.

### Additional Features

- **Domain Events**: Events are triggered for significant domain actions, such as renting a car. These events decouple the domain logic from external infrastructure, enabling clean separation of concerns.
- **Kafka Integration**: Domain events, such as `CarRentedEvent`, are published to a Kafka topic to facilitate further processing by downstream systems.
- **API Documentation**: A Swagger UI is available for exploring the APIs.
- **Security**: API access is secured using an API key.

---

## Application Architecture

### DDD Approach
- The application follows a Domain-Driven Design approach, organizing code into clear layers:
    - **Domain Layer**: Contains core business logic and domain models.
    - **Application Layer**: Manages use cases and services.
    - **Web Layer**: Manages controllers to serve web/mobile clients.
    - **Common Layer**: Manages dto, mapper and shared components over layers.
    - **Infrastructure Layer**: Contains implementation details like database repositories and Kafka producers.

### Messaging
- **Kafka Producer**: A Kafka producer is implemented in the infrastructure layer. Domain events trigger messages to be sent to Kafka topics.
- **Example**: A `CarRentedEvent` is published when a car is successfully rented.

---

## Setup

### Prerequisites
- Docker

### Running the Application

1. **Start the Application**:
   Launch the required services using Docker Compose:
   ```sh
   docker-compose up
   ```
   This will start:
    - A PostgreSQL database.
    - Kafka and Zookeeper.
    - Kafka UI for monitoring messages.

2. **Access Swagger UI**:
   Swagger documentation is accessible at:
   ```
   http://localhost:8085/rentals/swagger-ui/index.html
   ```

3. **Stopping and Cleaning Up Docker Containers**:
   To stop running containers:
   ```sh
   docker-compose down
   ```
   To remove all Docker images related to the project:
   ```sh
   docker rmi $(docker images -q)
   ```

---

## Testing

- The application includes comprehensive tests to validate:
    - Searching for available cars.
    - Renting and returning cars.
    - Searching and creating customers.
    - Kafka message publication upon domain events.
- To run the tests:
  ```sh
  mvn test
  ```

---

## Configuration

### Key Properties

- **Database**: PostgreSQL is used as the primary database. Spring initializes test data on startup using data.sql file for development and testing purposes.
- **Kafka**: Kafka topics are configured in the application properties.
- **API Security**: An API key is required to access protected endpoints.

### Example Application Properties
```properties
spring.datasource.url=jdbc:postgresql://db:5432/rentapp
spring.datasource.username=pgadmin
spring.datasource.password=pgadmin
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Kafka Configuration
spring.kafka.bootstrap-servers=kafka:9092
spring.kafka.consumer.group-id=rental-consumer-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer

# Security
api.key=a0f14a9a-9f30-40b7-8b58-620262c04aa1

# Management
management.endpoints.web.exposure.include=*

# Docs
springdoc.swagger-ui.path=/swagger-ui.html

spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always

spring.jpa.show-sql=true

server.port=8080
```

---

## Additional Information

- **Domain Events**: Used to decouple core domain logic from infrastructure concerns. For example, a `CarRentedEvent` triggers both database updates and a Kafka message.
- **Kafka Messages**: Events are published to Kafka topics using a dedicated producer in the infrastructure layer. This enables integration with external systems for further processing.

---

## License

This project is licensed under the MIT License.

---

## Contributors

- Amine ZAMANI

Feel free to contribute by submitting issues or pull requests!

---

## Contact

For questions or support, contact us at [support@carrental.com](mailto:zmi.amn@gmail.com).
