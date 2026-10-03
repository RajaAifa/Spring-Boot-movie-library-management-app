# 🎬 Filmotheque

**A Spring Boot movie library management application for managing films, actors and categories, with a MySQL database and a server-rendered Thymeleaf UI.**

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-6DB33F?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-database-4479A1?style=flat-square)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-templates-005F0F?style=flat-square)

---

## 📖 Overview

Filmotheque ("film library") is a CRUD web application for managing a movie catalog. It allows you to:

- Add, update, delete and browse **films**, each with a title, description, release year, poster photo, category and cast
- Manage **actors** and link them to multiple films (many-to-many)
- Manage **categories / genres** and link each film to one category (one-to-many)
- View detailed film pages with cast and category information

---

## 🛠️ Technologies Used

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.4.2 (Spring Web, Spring Data JPA) |
| Templating | Thymeleaf |
| Database | MySQL |
| ORM | Hibernate / JPA |
| Build tool | Maven |
| Other | Lombok |

---

## 🗂️ Data Model

```text
Categorie 1 ────────< Film >──────── * Acteur
 (one-to-many)                     (many-to-many)
```

- A **Category** has many films, and each film belongs to one category
- A **Film** has many actors, and an actor can appear in many films

---

## 📋 Prerequisites

- Java 17 or newer
- Maven (or use the included Maven Wrapper `./mvnw`)
- A MySQL server running locally

---

## 🚀 Run Locally

### 1. Clone the project

```bash
git clone https://github.com/RajaAifa/Spring-Boot-movie-library-management-app.git
cd Spring-Boot-movie-library-management-app
```

### 2. Create the database

The application creates the schema on startup (`createDatabaseIfNotExist=true`), so you only need a running MySQL instance. By default it expects:

- Database: `bd_film`
- Host: `localhost:3306`

### 3. Configure the database connection

Update `src/main/resources/application.properties` with your own MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bd_film?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

> 🔒 Never commit your real MySQL password. Keep placeholder values in the repository, or read the credentials from environment variables.

### 4. Run the application

With the Maven Wrapper:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Or, if Maven is installed globally:

```bash
mvn spring-boot:run
```

The app starts at **http://localhost:8080**.

---

## 💡 How to Use

1. Open the films list to browse the catalog.
2. Add a new **category**, then add **actors**.
3. Add a **film**, assigning it a category, a poster photo and one or more actors.
4. View the film details, or update / delete existing entries.

---

## 📁 Project Structure

```text
filmotheque/
├── src/
│   ├── main/
│   │   ├── java/com/gestion/filmotheque/
│   │   │   ├── controller/     # FilmController, ActeurController, CategorieController
│   │   │   ├── entities/       # Film, Acteur, Categorie (JPA entities)
│   │   │   ├── repository/     # Spring Data JPA repositories
│   │   │   └── service/        # Service layer (interfaces + implementations)
│   │   └── resources/
│   │       ├── templates/      # Thymeleaf HTML views
│   │       ├── static/photos/  # Uploaded film posters
│   │       └── application.properties
│   └── test/                   # Application tests
├── pom.xml
└── README.md
```

---

## 📸 Screenshots

<!-- Add your screenshots in docs/screenshots/ then remove this comment line and the closing one below.

| Films list | Film details |
|---|---|
| ![Films list](docs/screenshots/films.png) | ![Film details](docs/screenshots/film-details.png) |

| Add a film | Actors and categories |
|---|---|
| ![Add a film](docs/screenshots/add-film.png) | ![Actors and categories](docs/screenshots/actors-categories.png) |

-->

---

## 👩‍💻 Author

**Raja Aifa**

🎓 Professional Master's Degree in Web Services and Multimedia, ISITCOM (Higher Institute of Computer Science and Communication Technologies of Hammam Sousse), Tunisia

---

## 📜 License

This project is intended for educational purposes.
