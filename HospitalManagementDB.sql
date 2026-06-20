CREATE DATABASE hospital;
USE hospital;

CREATE TABLE patient (
    patientId INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    diagnosis VARCHAR(100)
);

CREATE TABLE doctor (
    doctorId INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    specialty VARCHAR(100)
);

INSERT INTO patient (patientId, name, surname, diagnosis)
VALUES
(1, 'Alice', 'Johnson', 'Hypertension'),
(2, 'Bob', 'Smith', 'Diabetes'),
(3, 'Clara', 'Lee', 'Asthma'),
(4, 'David', 'Brown', 'Migraine'),
(5, 'Emma', 'Wilson', 'Anemia'),
(6, 'Frank', 'Taylor', 'Arthritis');

INSERT INTO doctor (doctorId, name, surname, specialty)
VALUES
(1, 'Emily', 'Carter', 'Cardiology'),
(2, 'James', 'Brown', 'Neurology'),
(3, 'Sara', 'White', 'Pediatrics'),
(4, 'Michael', 'Davis', 'Orthopedics'),
(5, 'Laura', 'Martinez', 'Dermatology'),
(6, 'Robert', 'Garcia', 'Psychiatry');
