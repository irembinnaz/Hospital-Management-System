# Hospital Management System

A desktop application for managing hospital patient and doctor records, built with **Java Swing** for the GUI and **MySQL** for data storage. Developed as a project for a Visual Software Design course.

## Features

- **Patient management** — add, list, and delete patient records (ID, name, surname, diagnosis)
- **Doctor management** — add, list, and delete doctor records (ID, name, surname, specialty)
- **Tabbed interface** — separate tabs for Patients and Doctors
- **Live data table** — view all records in a sortable table within the app
- **MySQL integration** — all data is persisted in a MySQL database via JDBC

## Tech Stack

- **Language:** Java
- **GUI Framework:** Java Swing
- **Database:** MySQL
- **Connectivity:** JDBC

## Database Schema

| Table | Columns | Description |
|---|---|---|
| `patient` | `patientId`, `name`, `surname`, `diagnosis` | Stores patient records |
| `doctor` | `doctorId`, `name`, `surname`, `specialty` | Stores doctor records |

The full schema and sample data are available in [`HospitalManagementDB.sql`](./HospitalManagementDB.sql).

## Getting Started

### Prerequisites
- Java JDK 8 or higher
- MySQL Server
- MySQL Connector/J (JDBC driver)

### Setup

1. Clone this repository
   ```bash
   git clone https://github.com/irembinnaz/Hospital-Management-System.git
   ```
2. Create the database by running `HospitalManagementDB.sql` in MySQL
3. Open `HospitalManagement.java` in your IDE (e.g. Eclipse) and update the database credentials:
   ```java
   private static final String DB_URL  = "jdbc:mysql://localhost:3306/hospital";
   private static final String DB_USER = "root";
   private static final String DB_PASS = "your_password_here";
   ```
4. Make sure the MySQL Connector/J `.jar` is added to your project's build path
5. Run `HospitalManagement.java`

## Notes

- Database credentials are not hardcoded with real values in this repository — replace the placeholder password with your own local MySQL password before running.

## License

This project is open source and available for educational purposes.
