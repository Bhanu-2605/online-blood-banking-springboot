Online Blood Banking - Spring Boot (Layman guide)
===============================================

What this is
-------------
A simple, minimal **Online Blood Banking** project built with **Spring Boot (Java)**.
It provides:
- Donor registration
- Simple blood stock management
- Request a blood unit
- Admin console (basic)

Why this is easy to run
------------------------
- Uses H2 in-memory database (so you don't need to install MySQL).
- Single command to run with Maven: `mvn spring-boot:run`
- Includes simple HTML pages (Thymeleaf) and REST endpoints.

What you need (layman)
-----------------------
1. Java 17 installed.
2. Maven installed.
3. A terminal/command prompt.

How to run (step-by-step)
-------------------------
1. Unzip the folder.
2. Open terminal in the project root (where pom.xml is).
3. Run: `mvn spring-boot:run`
4. Open browser:
   - App UI: http://localhost:8080/
   - H2 console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:blooddb, user: sa)
   - Default Spring security dev user: admin / admin123

File overview (important files)
-------------------------------
- src/main/java/com/example/bloodbank : Java source
- src/main/resources/application.properties : config
- src/main/resources/templates : HTML pages
- src/main/resources/static : static CSS/JS
- sql/init-data.sql : sample data (optional)

If you want MySQL instead of H2
-------------------------------
- Change datasource props in application.properties to point to your MySQL server.
- Set `spring.jpa.hibernate.ddl-auto=update` (or `validate`) accordingly.
- Add MySQL driver dependency to pom.xml.

Questions or help
-----------------
If you want expanded features (email/SMS, maps, JWT auth, mobile frontend), tell me and I'll add them.
