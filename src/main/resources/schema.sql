-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS Abonents;
DROP TABLE IF EXISTS Cdr;

CREATE TABLE Abonents (
    id INT PRIMARY KEY AUTO_INCREMENT,
    telephone VARCHAR(11) NOT NULL
);

CREATE TABLE Cdr (
    id INT PRIMARY KEY AUTO_INCREMENT,
    call_type INT,
    telephone VARCHAR(11),
    start_time INT,
    end_time INT
);
commit;