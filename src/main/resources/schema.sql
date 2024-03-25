-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS Abonents;
DROP TABLE IF EXISTS Cdr;

CREATE TABLE Abonents (
    msisdn VARCHAR(11) PRIMARY KEY
);

CREATE TABLE Cdr (
    id INT PRIMARY KEY AUTO_INCREMENT,
    call_type INT,
    msisdn VARCHAR(11),
    start_time INT,
    end_time INT,
    FOREIGN KEY (msisdn) REFERENCES Abonents(msisdn)
);
commit;