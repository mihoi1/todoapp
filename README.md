# ToDoApp

ToDoApp is a simple web application for managing tasks, built with Spring Boot for the backend and HTML/CSS/JavaScript for the frontend. It allows creating categories, adding tasks to them, and marking tasks as Done or Pending.
Tasks are displayed in separate tables per category for a clean and organized view.

## Features

* Create and list categories.

* Add tasks under specific categories.

* Mark tasks as Done or Pending.

* Delete tasks.

* View tasks grouped by categories in tables.

* Simple, responsive web interface.

## Technologies Used

Backend: Spring Boot, Spring Data JPA, H2 Database.

Frontend: HTML, CSS, JavaScript.

## Setup & Run Instructions

### 1. Clone the repository

```
git clone https://github.com/yourusername/todoapp.git
cd todoapp
```

###  2. Build and run with Maven
Windows:

```
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

macOS / Linux:

```
./mvnw clean install
./mvnw spring-boot:run
```

### 3. Access the application

Open your browser at:
http://localhost:8080/index.html


### 4. Optional: H2 Database Console

You can view the in-memory database at:
http://localhost:8080/h2-console
* JDBC URL: jdbc:h2:mem:bankdb
* User: sa
* Password: (leave empty)


>[!NOTE]
>* The project uses an H2 in-memory database, suitable for development and testing but not recommended for production.
>* All task data will be lost when the application stops because the database is in-memory.

