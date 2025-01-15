# Spring Boot Learning Repository

This repository contains a collection of Spring Boot projects created during my learning journey. Each folder represents a specific topic, concept, or example implemented using Spring Boot.

---

## Repository Structure

```
Spring-Boot/
├── Demo/
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── CrudOperations/
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── More/
└── README.md
```

### Folder Details

- **Demo**:
  - Contains the introductory concepts of Spring Boot, including syntax, annotations, and basic application structure.

- **CrudOperations**:
  - Demonstrates Create, Read, Update, and Delete (CRUD) operations.
  - Examples include RESTful APIs and integration with databases like H2 or PostgreSQL.

- **DatabaseConnections**:
  - Explores how to connect and interact with various databases (e.g., MySQL, PostgreSQL, H2).

---

## How to Use This Repository

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/<your-username>/Spring-Boot.git
   ```
2. **Navigate to the Desired Project**:
   ```bash
   cd Spring-Boot/<Project-Folder>
   ```
3. **Run the Project**:
   - Use Maven to build and run the project:
     ```bash
     mvn spring-boot:run
     ```

---

## Topics Covered

1. **[Demo](./Demo/README.md)**:
   - Overview of Spring Boot annotations and their purpose.
   - Setting up a basic Spring Boot application.

2. **[CrudOperations](./CrudOperations/README.md)**:
   - Creating RESTful endpoints using `@RestController`.
   - Implementing CRUD functionality with in-memory and persistent databases.

3. **[DatabaseConnections](./DatabaseConnections/README.md)**:
   - Configuring application properties for database connectivity.
   - Using Spring Data JPA to interact with databases.

---

## Contribution Guidelines

- **New Examples**:
  - Add new folders for any additional concepts you learn.
  - Ensure each project contains a `README.md` with details about the implementation.

- **Feedback and Improvements**:
  - Suggestions are welcome! Feel free to create issues for enhancements or corrections.

---

## License

This repository is open for learning and sharing. Use it freely for educational purposes.

---

## Resources and References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Initializr](https://start.spring.io/)
- [Baeldung Spring Boot Tutorials](https://www.baeldung.com/spring-boot)
