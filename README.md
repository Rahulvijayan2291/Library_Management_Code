# Library Management API – Spring Boot

This project is a **REST API service** built using **Spring Boot** for managing a library. The API supports CRUD (Create, Read, Update, Delete) operations to manage book information. This implementation is designed for easy testing and scalability.

---

## Features
- **Add a Book**: Add new book details to the library.
- **Update a Book**: Modify information about an existing book.
- **Retrieve All Books**: Fetch the details of all books in the library.
- **Delete a Book**: Remove a book from the library using its unique ID.

---

## Book Attributes
Each book record includes the following attributes:

| Attribute        | Type    | Description                                                        |
|------------------|---------|--------------------------------------------------------------------|
| `id`             | `int`   | Unique identifier for the book.                                   |
| `ISBN`           | `String`| Unique International Standard Book Number.                        |
| `title`          | `String`| Title of the book.                                                |
| `author`         | `String`| Author(s) of the book.                                            |
| `description`    | `String`| A brief summary of the book.                                      |
| `genre`          | `String`| Category or genre of the book.                                    |
| `publicationYear`| `int`   | The year the book was published.                                  |
| `copiesAvailable`| `int`   | The number of copies of the book available in the library.        |
| `publisher`      | `String`| Publisher of the book.                                            |
| `coverImageUrl`  | `String`| URL to an image of the book's cover.                              |

---

## Sample Book JSON
Here is an example of the JSON representation of a book object:

```json
{
  "ISBN": "9780061120084",
  "title": "To Kill a Mockingbird",
  "author": "Harper Lee",
  "description": "A novel set in the American South during the 1930s, focusing on the trial of a black man accused of raping a white woman.",
  "genre": "Fiction",
  "publicationYear": 1960,
  "copiesAvailable": 5,
  "publisher": "J. B. Lippincott & Co.",
  "coverImageUrl": "https://via.placeholder.com/150"
}
```

---

## API Endpoints

### 1. **Add a New Book**
- **Endpoint**: `POST /book/add`
- **Request Body**: JSON object containing book details.
- **Response**:
  - `201`: Successfully added.
  - `400`: Data validation failed.

### 2. **Update Book Information**
- **Endpoint**: `PUT /book/{id}`
- **Request Body**: JSON object with updated book details.
- **Response**:
  - `200`: Successfully updated.
  - `400`: ID validation failed.

### 3. **Retrieve All Books**
- **Endpoint**: `GET /book/get`
- **Response**:
  - `200`: Successfully retrieved all books.
  - `400`: No books found.

### 4. **Delete a Book by ID**
- **Endpoint**: `DELETE /book/{id}`
- **Response**:
  - `200`: Successfully deleted.
  - `400`: ID validation failed.

---

## Prerequisites
- **Java 11 or higher**
- **Maven**

---

## Getting Started

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/library-management-api.git
   cd library-management-api
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. Test the application:
   ```bash
   mvn clean test
   ```

---

## Database
This application uses an **in-memory H2 database** for testing purposes. No additional setup is required as the database resets between application restarts.

To view the database during runtime:
1. Enable the H2 console in `application.properties`:
   ```properties
   spring.h2.console.enabled=true
   ```
2. Access the console at `http://localhost:8080/h2-console`.
3. Use the following credentials:
   - **URL**: `jdbc:h2:mem:testdb`
   - **User**: `sa`
   - **Password**: (leave blank)

---

## Running Tests
The project includes unit tests for the CRUD operations using Spring's `MockMvc`. The tests are located in the `src/test/java/com/example/library/` directory.

To execute tests:
```bash
mvn clean test
```

---

## Troubleshooting
### Port Already in Use
If you encounter a `port already in use` error, terminate the process using:
```bash
fuser -k 8080/tcp
```
Alternatively, update the port in `application.properties`:
```properties
server.port=8081
```

---

## Technologies Used
- **Spring Boot**: Framework for building REST APIs.
- **H2 Database**: In-memory database for testing.
- **JUnit**: Testing framework.
- **Maven**: Dependency and build management.

---

## Contributions
Contributions are welcome! Please feel free to submit issues or pull requests to improve this project. 

If you'd like to support the development of this project, you can **buy me a coffee**:

- **GPay**: +91-9074023334

Your support is greatly appreciated!

---

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
