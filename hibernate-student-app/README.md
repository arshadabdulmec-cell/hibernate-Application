# Hibernate Student Application

A Maven-based Hibernate application that maps a `Student` Java entity to a `student` database table and demonstrates INSERT, READ, UPDATE, and final verification.

## Database used

The supplied database details are configured in:

`src/main/resources/hibernate.cfg.xml`

- Host: `db01.dbhost.dev`
- Port: `5051`
- Database: `db_454va9f2d`
- Username: `user_454va9f2d`
- Password: `p454va9f2d`

### Important
The supplied connection details are configured as a **PostgreSQL** connection (`org.postgresql.Driver` and `PostgreSQLDialect`). Although the assignment wording says “MySQL table”, the provided database endpoint is being treated as PostgreSQL so the project can connect using the supplied credentials.

## Project structure

```text
hibernate-student-app/
├── pom.xml
├── README.md
├── student_verification.sql
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── student/
        │               ├── App.java
        │               ├── HibernateUtil.java
        │               └── Student.java
        └── resources/
            └── hibernate.cfg.xml
```

## Requirements

- JDK 17 or newer
- Maven 3.8+
- Internet access on the first Maven build so dependencies can be downloaded
- Access to the supplied database server

## How to run

Open a terminal in the project directory:

```bash
mvn clean compile
mvn exec:java
```

Or package first:

```bash
mvn clean package
mvn exec:java
```

Hibernate is configured with `hibernate.hbm2ddl.auto=update`, so it creates/updates the `student` table automatically.

## What the program does

1. Creates a `Student` object:
   - ID: `1001`
   - Name: `Meenakshi`
   - Email: `meenakshi1001@example.com`
   - Course: `Electronics and Communication Engineering`
2. Inserts the object into the `student` table.
3. Reads the row back and prints it.
4. Updates the course to `Computer Engineering`.
5. Reads the row again and prints the final database record.

## Expected console output

You should see output similar to:

```text
INSERT SUCCESS: Student{id=1001, name='Meenakshi', email='meenakshi1001@example.com', course='Electronics and Communication Engineering'}
READ SUCCESS: Student{id=1001, name='Meenakshi', email='meenakshi1001@example.com', course='Electronics and Communication Engineering'}
UPDATE SUCCESS: Course changed to Computer Engineering
FINAL DATABASE RECORD: Student{id=1001, name='Meenakshi', email='meenakshi1001@example.com', course='Computer Engineering'}
```

Hibernate SQL statements will also be printed because `hibernate.show_sql=true`.

## Verify in the database

After running the program, execute:

```sql
SELECT id, name, email, course
FROM student
WHERE id = 1001;
```

The final row should contain:

```text
1001 | Meenakshi | meenakshi1001@example.com | Computer Engineering
```

The same query is available in `student_verification.sql`.

## If you run the program again

The demo uses ID `1001` and a unique email. Running it a second time without changing the ID/email will cause a duplicate-key/unique-constraint error because the record already exists.

For another test, change `studentId` and the email in `App.java`, for example:

```java
int studentId = 1002;
```

and use another unique email.
